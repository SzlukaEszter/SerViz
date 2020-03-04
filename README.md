# SerViz
SerViz is a classroom project for Codecool. It simulates a microservice-based webshop of mineral waters.
The project consist of five microservices built with Spring Boot, such as water service, rating service, user service, cart service, checkout service. Each service has its own H2 DB.
For persistence and ORM we use JPA with Hibernate.
The services are registered by an Eureka server and a Zuul server is the api gateway answering frontend requests with JASON data. 
