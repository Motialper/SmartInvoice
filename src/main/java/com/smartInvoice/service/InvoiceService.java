package com.smartInvoice.service;

import com.smartInvoice.model.Invoice;
import com.smartInvoice.repository.InvoiceRepository;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ResourceLoader resourceLoader;
    private final ITesseract tesseract;
    private final Path uploadDir;

    @Value("${openai.api.key}")
    private String API_KEY;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ResourceLoader resourceLoader) {
        this.invoiceRepository = invoiceRepository;
        this.resourceLoader = resourceLoader;
        this.tesseract = new Tesseract();
        this.uploadDir = Paths.get("/root/Documents/invoices");

        // Create the directory if it doesn't exist
        try {
            Files.createDirectories(uploadDir);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }

        // Set the Tesseract data path to the tessdata folder
        tesseract.setDatapath("/root/Documents/invoce");
        // Optionally, set the language(s)
        tesseract.setLanguage("eng");
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    public Invoice processDocument(MultipartFile file) {
        try {
            // Save the uploaded file to the specified directory
            Path filePath = uploadDir.resolve(file.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(filePath)) {
                os.write(file.getBytes());
            }

            // Print the path where the file is saved
            System.out.println("File saved to: " + filePath);

            // Perform OCR on the saved file
            String text = tesseract.doOCR(filePath.toFile());

            // Extract data from the text
            Invoice invoice = extractInvoiceData(text);

            return invoice;
        } catch (IOException | TesseractException e) {
            throw new RuntimeException("Error processing document", e);
        }
    }

    private Invoice extractInvoiceData(String text) {
        // Implement logic to extract invoice data from text
        Invoice invoice = new Invoice();
        // Example extraction
        invoice.setVendor("Sample Vendor");
        invoice.setCategory("Sample Category");
        invoice.setAmount(100.0);
        return invoice;
    }
}
