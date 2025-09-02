package com.ahmed1v0.proxycache.service;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class cacheService {
    private final ConcurrentHashMap<String,ResponseEntity<?>>cache = new ConcurrentHashMap<>();

    public ResponseEntity<?> get(String key) {
        return cache.get(key);
    }

    public ResponseEntity<?> put(String key,ResponseEntity<?> value) {
        return cache.put(key,value);
    }
    public ResponseEntity<?> remove(String key) {
        return cache.remove(key);
    }
    public void clear() {
        cache.clear();
    }


}
