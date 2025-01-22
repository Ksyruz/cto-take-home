package com.example.ctotakehome.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class IntentController {

    @PostMapping("/process-intent")
    public String processIntent(@RequestParam("file") MultipartFile file) {
        // ...existing code...
        try {
            String userIntent = new String(file.getBytes());
            // Process the user intent and generate the .docx file
            // ...existing code...
            return "File processed successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing file";
        }
    }
}
