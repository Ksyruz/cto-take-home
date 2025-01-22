package com.example.ctotakehome.service;

import com.example.ctotakehome.model.Client;
import com.example.ctotakehome.model.Organization;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class IntentProcessingService {

    public void processIntent(String userIntent, Client client, Organization organization) throws IOException {
        // Use the LLM service to process the user intent
        // String processedIntent = callLLMService(userIntent);

        try (// Create a new .docx file
        XWPFDocument document = new XWPFDocument()) {
            // Insert the processed user intent and details from the JSON objects
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("User Intent: " + userIntent);
            run.addBreak();
            run.setText("Client Name: " + client.getName());
            run.addBreak();
            run.setText("Client Plan: " + client.getPlan());
            run.addBreak();
            run.setText("Organization Name: " + organization.getName());
            run.addBreak();
            run.setText("Organization Plan: " + organization.getPlan());

            // Save the document
            try (FileOutputStream out = new FileOutputStream("output.docx")) {
                document.write(out);
            }
        }
    }

    // private String callLLMService(String userIntent) {
    //     // Call the LLM service and return the processed intent
    //     // ...existing code...
    // }
}
