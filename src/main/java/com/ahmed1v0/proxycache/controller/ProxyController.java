package com.ahmed1v0.proxycache.controller;

import com.ahmed1v0.proxycache.entity.ProxyEntry;
import com.ahmed1v0.proxycache.repository.ProxyEntryRepository;
import com.ahmed1v0.proxycache.service.cacheService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProxyController {

    private final ProxyEntryRepository repo;
    private final cacheService cacheService;
    private final RestTemplate restTemplate = new RestTemplate();

    public ProxyController(ProxyEntryRepository repo, cacheService cacheService) {
        this.repo = repo;
        this.cacheService = cacheService;
    }

    @RequestMapping("/**")
    public ResponseEntity<?> forward(HttpServletRequest request) {
        int port;

        String portParam = request.getParameter("port");
        if (portParam != null) {
            try {
                port = Integer.parseInt(portParam);
            } catch (NumberFormatException e) {
                return ResponseEntity
                        .badRequest()
                        .body("Invalid port parameter: must be a number");
            }
        } else {
            port = request.getLocalPort();
        }

        String path = request.getRequestURI() +
                (request.getQueryString() != null ? "?" + request.getQueryString() : "");

        ProxyEntry entry = repo.findByPort(port).orElse(null);
        if (entry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No origin configured for port " + port);
        }

        String targetUrl = entry.getOrigin() + path;
        String cacheKey = port + ":" + path;

        ResponseEntity<?> cachedResponse = cacheService.get(cacheKey);
        HttpHeaders headers = new HttpHeaders();
        if (cachedResponse != null) {
            headers.addAll(cachedResponse.getHeaders());
            headers.add("X-Cache", "HIT");
            return new ResponseEntity<>(cachedResponse.getBody(), headers, HttpStatus.OK);
        }

        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(targetUrl, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Failed to fetch from origin: " + e.getMessage());
        }

        cacheService.put(cacheKey, response);

        headers.addAll(response.getHeaders());
        headers.add("X-Cache", "MISS");

        return new ResponseEntity<>(response.getBody(), headers, response.getStatusCode());
    }
}
