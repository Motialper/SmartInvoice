package com.smartInvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartInvoice.model.Invoice;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByCategory(String category);
}
