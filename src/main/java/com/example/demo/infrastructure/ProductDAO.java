package com.example.demo.infrastructure;

import com.example.demo.models.Product;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO {
    private final MongoDatabase mongoDatabase;

    public ProductDAO(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    public Product find(String productId) {
        MongoCollection<Document> productCollection = mongoDatabase.getCollection("products");
        Document productDocument = productCollection.find(new Document("_id", productCollection)).first();
        return new Product(
                productDocument.getObjectId("_id").toString(),
                productDocument.getString("name"),
                productDocument.getInteger("price")
        );


    }
}
