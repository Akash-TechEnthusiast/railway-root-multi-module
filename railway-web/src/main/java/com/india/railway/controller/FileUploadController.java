package com.india.railway.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // Save file to the desired location or process it as needed
        // Example: file.transferTo(new File("path/to/save/" + file.getOriginalFilename()));
        return "File uploaded successfully: " + file.getOriginalFilename();
    }
}