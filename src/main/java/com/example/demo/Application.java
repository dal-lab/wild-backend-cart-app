package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MongoClient mongoClient() {

        String mongoURI = "mongodb://192.168.0.17:27017";

        return MongoClients.create(mongoURI);
    }

}
