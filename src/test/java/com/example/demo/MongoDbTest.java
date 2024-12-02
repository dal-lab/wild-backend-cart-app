package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import static org.assertj.core.api.Assertions.assertThat;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MongoDbTest {

    @Autowired
    private MongoClient mongoClient;

    @Test
    public void testMongoClientBean() {
        // MongoClient 빈이 주입되었는지 확인
        assertThat(mongoClient).isNotNull();
    }
    @Test
    void readProducts() {
        // 1. DB 서버 접속 간단확인
        String mongoDatabase = "demo";

        // 2. 데이터 베이스 선택 -> demo
        MongoDatabase database = mongoClient.getDatabase(mongoDatabase);

        // 3. collection에서 목록 열기 -> products
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
