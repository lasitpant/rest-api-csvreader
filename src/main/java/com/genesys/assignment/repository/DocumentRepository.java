package com.genesys.assignment.repository;

import com.genesys.assignment.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<Document, String> {
}
