package com.ahmed1v0.proxycache.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proxy_entry", uniqueConstraints = {
        @UniqueConstraint(columnNames = "port")
})
public class ProxyEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)

    private int port;

    @Column(nullable = false)
    private String origin;

    @Override
    public String toString() {
        return "ProxyEntry{" +
                "port=" + port +
                ", origin='" + origin + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
