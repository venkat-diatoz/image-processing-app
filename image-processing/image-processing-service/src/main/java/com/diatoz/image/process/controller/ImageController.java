package com.diatoz.image.process.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.transloadit.sdk.Assembly;
import com.transloadit.sdk.Transloadit;
import com.transloadit.sdk.exceptions.LocalOperationException;
import com.transloadit.sdk.exceptions.RequestException;
import com.transloadit.sdk.response.AssemblyResponse;

@RestController
@RequestMapping("/image")
public class ImageController {

	//private ImageRepository imageRepository;
	
	/*public ImageController(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<String> getAll(){
		return new ResponseEntity<String>("Get All Images invoked successfully" , HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
        Transloadit transloadit = new Transloadit("YOUR_TRANSLOADIT_KEY", "YOUR_TRANSLOADIT_SECRET");
        Assembly assembly = transloadit.newAssembly();
        AssemblyResponse response = null;
        // Add instructions, e.g. resize image, or encode video
        Map<String,Object> thumbStepOptions = new HashMap<>();
        thumbStepOptions.put("use", ":original");
        thumbStepOptions.put("width", 75);
        thumbStepOptions.put("height", 75);
        thumbStepOptions.put("resize_strategy", "fit");
        assembly.addStep("thumb", "/image/resize", thumbStepOptions);
      
        try {
            // Add files to upload
            assembly.addFile(getFile(file));
            response = assembly.save();

            // wait for assembly to finish executing.
            while (!response.isFinished()) {
                response = transloadit.getAssemblyByUrl(response.getSslUrl());
            }

            System.out.println(response.getId());
            System.out.println(response.getUrl());
           // System.out.println(response.json());
        } catch (IOException | RequestException | LocalOperationException e) {
            // handle exception here
        }
        return new ResponseEntity<String>(response.getId() + " : " + response.getUrl(), HttpStatus.OK);
	}
	
	private File getFile(MultipartFile multiPartFile) throws IOException {
		File newFile = new File(multiPartFile.getOriginalFilename());
		multiPartFile.transferTo(newFile);
		return newFile;
	}

}
