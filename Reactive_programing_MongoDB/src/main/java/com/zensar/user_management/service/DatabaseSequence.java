package com.zensar.user_management.service;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "counters") // stores sequences here
public class DatabaseSequence {

    @Id
    private String id;
    private int seq;
}
