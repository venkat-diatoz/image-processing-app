package com.diatoz.image.process.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Image")
public class Image {

	@Id
	private String id;
	private String imageUrl;
	private String imageName;
}
