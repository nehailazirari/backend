package com.example.backend.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
@Setter
@Getter
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;

    //getters and setters omitted
}