package com.smartInvoice.controler;

import com.smartInvoice.model.Invoice;
import com.smartInvoice.service.InvoiceService;
import com.smartInvoice.model.InvoiceIn ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/upload")
    public Invoice uploadInvoice(@RequestParam("file") MultipartFile file) {
        Invoice invoice = invoiceService.processDocument(file);
        return invoiceService.saveInvoice(invoice);
    }

    @PostMapping
    public Invoice createInvoice(@RequestBody InvoiceIn invoiceIn) {
        Invoice invoice = invoiceIn.toInvoice();
        return invoiceService.saveInvoice(invoice);
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
}
