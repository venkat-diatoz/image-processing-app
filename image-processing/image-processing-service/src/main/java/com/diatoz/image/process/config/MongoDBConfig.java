package com.diatoz.image.process.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.diatoz.image.process.document.Image;
import com.diatoz.image.process.repository.ImageRepository;

@EnableMongoRepositories(basePackageClasses = ImageRepository.class)
@Configuration
public class MongoDBConfig {

	@Bean
	CommandLineRunner commandLineRunner(ImageRepository imageRepository) {
		return strings -> {
			imageRepository.save(new Image("1", "http://google.com", "Google"));
			imageRepository.save(new Image("2", "http://diatoz.com", "Diatoz"));
			imageRepository.save(new Image("3", "http://galaxesollutions.com", "GalaxE"));
			imageRepository.save(new Image("4", "http://ibm.com", "IBM"));
			imageRepository.save(new Image("5", "http://mongodb.com", "MongoDB"));
		};
	}
}
