package com.freetube.userservice.repositories;

import com.freetube.userservice.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
