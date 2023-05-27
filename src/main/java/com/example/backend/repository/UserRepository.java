package com.example.backend.repository;

import com.example.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User,Long> {
    boolean existsByUsername(String username);

    Optional<Object> findByUsername(String username);
}
