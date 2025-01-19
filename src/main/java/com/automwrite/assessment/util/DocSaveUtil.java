package com.automwrite.assessment.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class DocSaveUtil {

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
     * Parses the content of a .txt file uploaded as a MultipartFile.
     *
     * @param file The uploaded .txt file
     * @return The content of the file as a String
     * @throws IOException If an error occurs while reading the file
     */
    public static String parseTxtFile(MultipartFile file) throws IOException {
        return new String(file.getBytes(), StandardCharsets.UTF_8);
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

    /**
     * Inserts content into the placeholder section of a template document.
     *
     * @param document The XWPFDocument to modify
     * @param content  The content to insert into the template
     */
    public void insertContentIntoTemplate(XWPFDocument document, String content) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            if (paragraph.getText().contains("{placeholder}")) {
                paragraph.getRuns().clear();
                paragraph.createRun().setText(content);
                break;
            }
        }
    }
}
