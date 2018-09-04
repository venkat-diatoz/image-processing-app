package com.diatoz.image.process.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.diatoz.image.process.document.Image;

public interface ImageRepository extends MongoRepository<Image, String>{

}
