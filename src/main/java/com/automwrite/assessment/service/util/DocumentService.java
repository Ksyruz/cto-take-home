package com.automwrite.assessment.service.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DocumentService {

    /**
     * Saves the provided XWPFDocument to a specified file name.
     *
     * @param document The XWPFDocument to be saved
     * @throws IOException If an error occurs during the file writing process
     */
    public void saveDocument(XWPFDocument document) throws IOException {
        try (FileOutputStream out = new FileOutputStream("recommendation.docx")) {
            document.write(out);
        }
    }

    /**
     * Loads a predefined template from the resources folder.
     *
     * @return The loaded XWPFDocument template
     * @throws IOException If the template file is not found or cannot be loaded
     */
    public XWPFDocument loadTemplate() throws IOException {
        try (InputStream templateStream = getClass().getResourceAsStream("/templates/recommendation-template.docx")) {
            if (templateStream == null) {
                throw new IOException("Template file not found");
            }
            return new XWPFDocument(templateStream);
        }
    }
}
