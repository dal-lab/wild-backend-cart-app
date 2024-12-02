package com.example.demo.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record GenerateId(
        String id
) {

    public GenerateId() {
        this(new IdGenerator().generateId());
    }
}

class IdGenerator {

    public String generateId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String dateTime = now.format(formatter);
        String shortUUID = UUID.randomUUID().toString().substring(0, 4);

        return dateTime + shortUUID;
    }
}

