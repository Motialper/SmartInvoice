package com.smartInvoice.controler;

import com.smartInvoice.model.Invoice;
import com.smartInvoice.service.InvoiceService;
import com.smartInvoice.model.InvoiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadInvoice(@RequestParam("file") MultipartFile file) {
        try {
            // קריאה לשירות InvoiceService לניתוח הקובץ
            Invoice invoice = invoiceService.processDocument(file);

            // שמירת החשבונית ב-DB
            return new ResponseEntity<>(invoiceService.saveInvoice(invoice), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload invoice: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // יצירת חשבונית חדשה באמצעות גוף בקשה
    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceIn invoiceIn) {
        try {
            // המרת InvoiceIn לאובייקט Invoice
            Invoice invoice = invoiceIn.toInvoice();

            // שמירת החשבונית ב-DB
            return new ResponseEntity<>(invoiceService.saveInvoice(invoice), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create invoice: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // קבלת כל החשבוניות
    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        try {
            // קריאה לשירות InvoiceService לקבלת רשימת כל החשבוניות
            return new ResponseEntity<>(invoiceService.getAllInvoices(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // קבלת חשבוניות לפי קטגוריה
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Invoice>> getInvoicesByCategory(@PathVariable String category) {
        try {
            return new ResponseEntity<>(invoiceService.getInvoicesByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // עדכון חשבונית קיימת
    public void updateInvoice(Invoice invoice, InvoiceIn invoiceIn) {
        try {
            // עדכון אובייקט החשבונית עם הערכים מ-InvoiceIn
            invoiceIn.updateInvoice(invoice);

            // שמירת החשבונית המעודכנת ב-DB
            invoiceService.saveInvoice(invoice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update invoice", e);
        }
    }
}
