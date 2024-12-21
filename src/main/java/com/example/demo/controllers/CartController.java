package com.example.demo.controllers;

import com.example.demo.controllers.dtos.CartDto;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final MongoDatabase mongoDatabase;

    public CartController(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    @GetMapping
    CartDto detail() {
        MongoCollection<Document> collection =
                mongoDatabase.getCollection("lineItems");


        List<Document> documents = new ArrayList<>();
        collection.find().into(documents);

        return new CartDto(
                documents.stream().map(
                        document -> {
                            MongoCollection<Document> productCollection =
                                    mongoDatabase.getCollection("products");
                            String productId = document.getString("product_Id");

                            Document productDocument = productCollection.find(
                                    Filters.eq("_id", new ObjectId(productId))
                            ).first();

                            //TODO: need to handle execption if productDocument is null


                            int unitPrice = productDocument.getInteger("price");
                            int quantity = document.getInteger("quantity");
                            return new CartDto.LineItemDto(
                                    document.getObjectId("_id").toString(),
                                    document.getString("product_Id"),
                                    productDocument.getString("name"),
                                    unitPrice,
                                    quantity,
                                    unitPrice * quantity
                            );

                        }).toList(),
                0
        );
    }
}
