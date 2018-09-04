package com.diatoz.image.process.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.transloadit.sdk.Assembly;
import com.transloadit.sdk.Transloadit;
import com.transloadit.sdk.exceptions.LocalOperationException;
import com.transloadit.sdk.exceptions.RequestException;
import com.transloadit.sdk.response.AssemblyResponse;

public class UploadImage {
    public static void main(String[] args) {
        Transloadit transloadit = new Transloadit("YOUR_TRANSLOADIT_KEY", "YOUR_TRANSLOADIT_SECRET");
        Assembly assembly = transloadit.newAssembly();
        // Add instructions, e.g. resize image, or encode video
        Map<String,Object> thumbStepOptions = new HashMap<>();
        thumbStepOptions.put("use", ":original");
        thumbStepOptions.put("width", 75);
        thumbStepOptions.put("height", 75);
        thumbStepOptions.put("resize_strategy", "fit");
        assembly.addStep("thumb", "/image/resize", thumbStepOptions);
      
        // Add files to upload
        assembly.addFile(new File("./chameleon.jpg"));
        
        try {
            AssemblyResponse response = assembly.save();

            // wait for assembly to finish executing.
            while (!response.isFinished()) {
                response = transloadit.getAssemblyByUrl(response.getSslUrl());
            }

            System.out.println(response.getId());
            System.out.println(response.getUrl());
           // System.out.println(response.json());
        } catch (RequestException | LocalOperationException e) {
            // handle exception here
        }
    }
}