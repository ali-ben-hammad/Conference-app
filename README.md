# Conference Management Application

## Realized by : Ali ben-hammad | GLSID 3

## 1. Technical Architecture

The conference management application is built using a microservices architecture. The following microservices are implemented:

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/0f51083e-e805-4aa3-a977-ea31d5792547/image.png)

1. **Keynote Service**: Manages the keynote speakers, including their id, name, email, and function.
2. **Conference Service**: Manages the conferences, including their id, title, type (academic or commercial), date, duration, number of attendees, and score. It also handles the reviews for each conference, including the review id, date, text, and rating (1-5 stars).
3. **Gateway Service**: Spring Cloud Gateway that serves as the entry point for the application, handling routing, security, and load balancing.
4. **Discovery Service**: Eureka Server or Consul Discovery that enables service discovery and registration.
5. **Config Service**: Spring Cloud Config or Consul Config that manages the centralized configuration for the microservices.
6. **Angular Front-end App**: The web-based user interface built using the Angular framework.

The application also utilizes the following technologies:

- **Security**: OAuth2 and OIDC with Keycloak as the Identity Provider.
- **Fault Tolerance**: Resilience4J for circuit breakers.
- **Documentation**: OpenAPI (Swagger) for the REST API documentation.

---

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/ebbcd363-07be-4e13-8a54-34eae192761e/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/aaf6c324-f113-4ec5-8f3c-aead55134165/image.png)

## 2. Project Setup

The project is set up as a Maven multi-module project with the following modules:

- `keynote-service`
- `conference-service`
- `gateway-service`
- `discovery-service`
- `config-service`
- `angular-front-app`

## 3. Implementing the Discovery and Gateway Services

The `discovery-service` is implemented using  Consul Discovery, which enables service discovery and registration.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/b267f005-57e7-49e2-bcd3-517b5404b9bc/image.png)

The `gateway-service` is implemented using Spring Cloud Gateway, which handles routing, security, and load balancing for the application.

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: keynote-service
          uri: lb://keynote-service
          predicates:
            - Path=/keynotes/**
          filters:
            - RewritePath=/wallets/(?<remaining>.*), /${remaining}
        - id: conference-service
          uri: lb://conference-service
          predicates:
            - Path=/conferences/**
          filters:
            - RewritePath=/keynotes/(?<remaining>.*), /${remaining}

```

## 4. Implementing the Keynote Service

The `keynote-service` is responsible for managing the keynote speakers. It includes the following components:

- **Entities**: Keynote
- **DAO (Data Access Object)**: KeynoteRepository
- **Service**: KeynoteService
- **DTO (Data Transfer Object)**: KeynoteDTO
- **Mapper**: KeynoteMapper
- **REST Controller**: KeynoteController

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/b7cc2416-c1e6-4c50-ba70-913cf69dcc5d/image.png)

## 5. Implementing the Conference Service

The `conference-service` is responsible for managing the conferences and their reviews. It includes the following components:

- **Entities**: Conference, Review
- **DAO (Data Access Object)**: ConferenceRepository, ReviewRepository
- **Service**: ConferenceService, ReviewService
- **DTO (Data Transfer Object)**: ConferenceDTO, ReviewDTO
- **Mapper**: ConferenceMapper, ReviewMapper
- **REST Controller**: ConferenceController
- **Open Feign Client**: KeynoteClient

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/acb40162-b1db-4156-9689-bb27e538840a/image.png)

## 6. Developing the Angular Front-end App

The `angular-front-app` is the web-based user interface for the conference management application. It consumes the REST APIs provided by the `keynote-service` and `conference-service`.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/a8ee34af-2473-4e19-a065-99ce79dd9968/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/f5f786a2-7bb5-4fbc-a2e4-d8570fe8aa2a/image.png)

## 7. Securing the Application with Keycloak

The application is secured using OAuth2 and OIDC with Keycloak as the Identity Provider. Users can log in to the application using the Keycloak login form.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/7efb5cee-835a-46c2-a61f-58ed4cbe0c9c/40b30c29-e780-4170-8294-369095bb036a/image.png)
