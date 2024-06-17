package com.smartInvoice.model;

import java.io.Serializable;
public class InvoiceIn implements Serializable {

    private String vendor;
    private String category;
    private Double amount;

    // Getters and setters

    public Invoice toInvoice() {
        Invoice invoice = new Invoice();
        invoice.setVendor(this.vendor);
        invoice.setCategory(this.category);
        invoice.setAmount(this.amount);
        return invoice;
    }

    public void updateInvoice(Invoice invoice) {
        invoice.setVendor(this.vendor);
        invoice.setCategory(this.category);
        invoice.setAmount(this.amount);
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
