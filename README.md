# Point of Sale (in portuguese PDV - Ponto de Venda)

## Prerequisites

- OpenJDK 1.8
- Maven 3.3+

## Building Application

> $ mvn compile -e

## Packaging Application

> $ mvn clean package -e

## Testing Application (using unit test)

> $ mvn test -e

For the unit test, the REST Api Controller will be the unit on this tests

## Runinng Application

> $ mvn spring:run -e

This is a **Spring Boot** application and will start Tomcat as Web Application Server at port **8080**.

REST Api and GraphQL will be avaible at:

> http://localhost:8080

Note, use **/pdv** as context path for access the REST Api. Also, use **/graphiql** for GraphQL IDE. GraphQL IDE will be available 
as an online editor to test the GraphQL Api on this application.

## Checking Application is UP

> http://localhost:8080/actuator/health
