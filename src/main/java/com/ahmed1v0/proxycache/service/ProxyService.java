package com.ahmed1v0.proxycache.service;

import com.ahmed1v0.proxycache.entity.ProxyEntry;
import com.ahmed1v0.proxycache.repository.ProxyEntryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProxyService {

    private final ProxyEntryRepository repo;

    public ProxyService(ProxyEntryRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public ProxyEntry saveProxy(Integer port, String host) {
        if (host == null || host.isBlank()) {
            throw new IllegalArgumentException("Origin (host) is required");
        }
        if (port == null || port < 1000 || port > 9999) {
            throw new IllegalArgumentException("Port is required and must contain 4 digits");
        }

        if (repo.existsProxyEntryByPort(port)) {
            throw new IllegalArgumentException("Proxy Port " + port + " already exists");
        }

        ProxyEntry entry = new ProxyEntry();
        entry.setPort(port);
        entry.setOrigin(host);

        return repo.save(entry);
    }
}
