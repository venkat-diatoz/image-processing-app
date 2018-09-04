package com.diatoz.image.process.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diatoz.image.process.document.Image;
import com.diatoz.image.process.repository.ImageRepository;

@RestController
@RequestMapping("/image")
public class ImageController {

	private ImageRepository imageRepository;
	
	public ImageController(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	
	@GetMapping("/all")
	public List<Image> getAll(){
		return imageRepository.findAll();
	}
}
