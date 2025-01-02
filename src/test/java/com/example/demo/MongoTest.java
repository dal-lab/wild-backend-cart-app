package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MongoTest {
    @Test
    void readProducts() {
        // 1. connect DB server

        String mongoURL = "mongodb://localhost:27017";
        String mongoDatabase = "demo";

        MongoClient client = MongoClients.create(mongoURL);

        // 2. Choose Database -> demo

        MongoDatabase database = client.getDatabase(mongoDatabase);

        // 3. Choose Collection -> list


        MongoCollection<Document> collection =
                database.getCollection("products");
        List<Document> documents = new ArrayList<>();

        collection.find().into(documents);

        documents.forEach(document -> {
            System.out.println(document.getString("name"));
            System.out.println(document.getInteger("price"));
        });

    }
}
