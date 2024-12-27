package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MongoClient mongoClient(
            @Value("${mongodb.url}") String url
    ) {
        return MongoClients.create(url);
    }

    @Bean
    public MongoDatabase mongoDatabase(
            MongoClient mongoClient,
            @Value("${mongodb.database}") String database
    ) {
        return mongoClient.getDatabase(database);
    }
}
