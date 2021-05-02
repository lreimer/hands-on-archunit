# Weather Service

This example implements a simple weather REST service using Spring Boot (JAR).

- https://spring.io
- https://start.spring.io

## Build and run with JDK8+

```bash
$ ./gradlew :weather-service:build
$ ./gradlew :weather-service:bootRun
```

## Run with Docker

```bash
$ docker build -t weather-service:1.0.0 .
$ docker run -it --rm --cpus 2 --memory 512m -p 8080:8080 weather-service:1.0.0
```

## Exercise the application

```bash
$ curl -X GET http://localhost:8080/api/weather\?city\=Rosenheim
{"city":"Rosenheim","weather":"Sunshine"}

$ curl -X GET http://localhost:8080/

$ curl -X GET http://localhost:8080/metrics
$ curl -X GET http://localhost:8080/health
```
