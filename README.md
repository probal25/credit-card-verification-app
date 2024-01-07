
# Create a credit card through a streaming service

This service is responsible for verifing and filter out valid credit card requests. It will act as a processor.


## Service Responsibilities

- After getting a new credit card request credit-card-service will accept the request (It's the producer service)
- Then producer will publish the message to the queue. credit-card-verification service will act as a processor. It will process and verify the stream of credit card requests and filter the verified requests. Then it will pass the stream data to the consumer.
- credit-card-generator service will act as a consumer. It will consume the upcoming
  streams and create credit cards for each



## Tech Stack

**Server:** Java 17, Spring boot 3.2.0, RabbitMQ, Spring Cloud Stream


