to run the application:

1. Start your MongoDB instance. If you're using Docker, you can use:
   ```
   docker run -d -p 27017:27017 --name mongodb mongo:latest
   ```

2. Start Kafka and Zookeeper. If you're using Docker, you can use the docker-compose file we created earlier:
   ```
   docker-compose up -d zookeeper kafka
   ```

3. Build the project:
   ```
   mvn clean package
   ```

4. Start each service in a separate terminal window:

   API Gateway:
   ```
   java -jar api-gateway/target/api-gateway-1.0.0.jar
   ```

   Order Service:
   ```
   java -jar order-service/target/order-service-1.0.0.jar
   ```

   User Service:
   ```
   java -jar user-service/target/user-service-1.0.0.jar
   ```

   Notification Service:
   ```
   java -jar notification-service/target/notification-service-1.0.0.jar
   ```

5. The services should now be running and registered with Eureka (if you've set up Eureka as the service registry).

6. You can access the services through the API Gateway, which should be running on `http://localhost:8080` by default.

To test the application:

1. Create an order:
   ```
   curl -X POST http://localhost:8080/orders -H "Content-Type: application/json" -d '{"userId":"user1","productId":"product1","quantity":2,"totalPrice":100.00}'
   ```

2. Get all orders:
   ```
   curl http://localhost:8080/orders
   ```

3. Check the console output of the Notification Service to see if it received the OrderCreatedEvent and "sent" an email.

Remember to add appropriate configuration files (application.yml or application.properties) for each service to set up database connections, Kafka configuration, and other necessary settings.

This setup provides a basic working structure for your microservices. You can expand on this by adding more functionality to each service, implementing proper error handling, adding security, and more.