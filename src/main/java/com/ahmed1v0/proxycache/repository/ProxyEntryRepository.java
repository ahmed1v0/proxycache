package com.ahmed1v0.proxycache.repository;

import com.ahmed1v0.proxycache.entity.ProxyEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProxyEntryRepository extends JpaRepository<ProxyEntry, Long> {
    boolean existsProxyEntryByPort(int port);
    Optional<ProxyEntry> findByPort(int port);

}
