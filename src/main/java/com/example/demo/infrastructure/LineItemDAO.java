package com.example.demo.infrastructure;

import com.example.demo.models.LineItem;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LineItemDAO {
    private final MongoDatabase mongoDatabase;
    private final MongoCollection<Document> collection;

    public LineItemDAO(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
        this.collection = mongoDatabase.getCollection("line_items");
    }

    public List<LineItem> findAll() {

        List<Document> documents = new ArrayList<>();
        collection.find().into(documents);
        return documents.stream().map(this::mapToLineItem).toList();
    }


    private LineItem mapToLineItem(Document document) {
        return new LineItem(
                document.getObjectId("_id").toString(),
                document.getString("product_id"),
                document.getInteger("quantity")
        );
    }

    public void add(LineItem lineItem) {
        Document document = new Document()
                .append("product_id", lineItem.getProductId())
                .append("quantity", lineItem.getQuantity());
        collection.insertOne(document);
    }

    public void update(LineItem lineItem) {
        collection.updateOne(
                Filters.eq("_id", new ObjectId(lineItem.getId())),
                Updates.combine(
                        Updates.set("product_id", lineItem.getProductId()),
                        Updates.set("quantity", lineItem.getQuantity())
                ));
    }
}
