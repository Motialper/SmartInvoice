package com.smartInvoice.service;
import com.smartInvoice.util.InvoiceCategorizer;
import com.smartInvoice.repository.InvoiceRepository;
import com.smartInvoice.model.Invoice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceCategorizer invoiceCategorizer;

    public Invoice saveInvoice(Invoice invoice) {
        invoice.setCategory(invoiceCategorizer.categorize(invoice));
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
