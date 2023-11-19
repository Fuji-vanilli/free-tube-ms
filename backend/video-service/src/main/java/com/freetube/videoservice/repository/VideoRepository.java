package com.freetube.videoservice.repository;

import com.freetube.videoservice.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {

    Page<Video> findAll(Pageable pageable);
}
