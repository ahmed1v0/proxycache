# 🚀 ProxyCaching – A CLI-Driven Reverse Proxy with Caching

## 📌 Project Description

ProxyCaching is a Spring Boot–based reverse proxy application that forwards incoming requests to configured origin servers while caching responses to improve performance.

It supports:

* **CLI Commands** for configuration: insert new origin servers, list existing ones.
* **Caching Layer** to serve repeated requests faster.
* **Dynamic Port Mapping**: each origin is bound to a unique port.
* **REST Proxying** using Spring’s RestTemplate.
* **Persistent Storage** of origin entries via JPA/H2 (or other databases).

This project demonstrates expertise in Java, Spring Boot, REST APIs, caching, database integration, and CLI tooling inside Spring applications.

## 🛠️ Tech Stack

* Java 17+
* Spring Boot 3
* Spring Data JPA
* H2 Database (in-memory, can be swapped with PostgreSQL/MySQL)
* RestTemplate
* Maven

## ⚙️ Features

* ✅ Insert origin server mapping via CLI:

```bash
java -jar proxycaching.jar insert --port 8080 --origin http://example.com
```

* ✅ List all stored mappings:

```bash
java -jar proxycaching.jar list
```

* ✅ Proxy requests through configured ports:

```bash
curl -i "http://localhost:8080/api/data?port=8080"
```

* ✅ Caching responses with `X-Cache` header (HIT/MISS).
* ✅ Modular design with Command pattern for CLI extensibility.

## 🚀 How to Run

Clone the repo:

```bash
git clone https://github.com/ahmed1v0/proxycache.git
cd proxycache
```

Build the jar:

```bash
mvn clean package
```

Run CLI commands:

```bash
java -jar target/proxycaching.jar insert --port 9090 --origin http://httpbin.org
java -jar target/proxycaching.jar list
```

Start proxying requests:

```bash
curl -i "http://localhost:9090/get?port=9090"
```

## 📂 Example Output

```bash
$ java -jar proxycaching.jar insert --port 9090 --origin http://httpbin.org
Saved entry: http://httpbin.org:9090

$ java -jar proxycaching.jar list
1 - http://httpbin.org:9090

$ curl -i "http://localhost:9090/get?port=9090"
HTTP/1.1 200
X-Cache: MISS
...
```
