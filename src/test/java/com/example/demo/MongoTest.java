package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

//학습테스트 작성

@SpringBootTest
public class MongoTest {
    @Autowired
    MongoClient client;

    @Test
    void readProducts() {
        // 1. 데이터베이스 연결
        // 2. 내부 DB 선택
        // 3. collection에서 목록 얻기 -> products
        String mongoDatabase = "demo";

        MongoDatabase database = client.getDatabase(mongoDatabase);
        MongoCollection<Document> collection = database.getCollection("products");
        List<Document> documents = new ArrayList<>();
        collection.find().into(documents);

        documents.forEach(document -> {
            System.out.println(document.getString("name"));
            System.out.println(document.getInteger("price"));
//
        });


    }

}
