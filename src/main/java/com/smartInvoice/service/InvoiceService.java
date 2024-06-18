package com.smartInvoice.service;

import com.smartInvoice.model.Invoice;
import com.smartInvoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OpenAiServiceWrapper openAiServiceWrapper;

    public Invoice processDocument(MultipartFile file) {
        // יש להוסיף כאן את הקוד לפענוח הטקסט מתוך הקובץ
        String invoiceText = extractTextFromFile(file);

        // ניתוח הטקסט באמצעות OpenAI
        String analyzedText = openAiServiceWrapper.analyzeInvoice(invoiceText);

        // יצירת אובייקט Invoice מתוך הטקסט המנותח
        Invoice invoice = createInvoiceFromAnalyzedText(analyzedText);

        return invoice;
    }

    private String extractTextFromFile(MultipartFile file) {
        // יש להוסיף כאן את הקוד לקריאת הטקסט מתוך הקובץ
        return "Example extracted text from file";
    }

    private Invoice createInvoiceFromAnalyzedText(String analyzedText) {
        // יש להוסיף כאן את הקוד ליצירת אובייקט Invoice מתוך הטקסט המנותח
        return new Invoice();
    }
    public List<Invoice> getInvoicesByCategory(String category) {
        return invoiceRepository.findByCategory(category);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public InvoiceRepository getInvoiceRepository() {
        return invoiceRepository;
    }

    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
}
