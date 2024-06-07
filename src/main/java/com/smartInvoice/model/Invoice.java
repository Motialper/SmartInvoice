package com.smartInvoice.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * This class represents an invoice entity for the database.
 */
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the invoice.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the vendor who issued the invoice.
     */
    @NotEmpty
    @Length(max = 100)
    private String vendor;

    /**
     * The category of the invoice, e.g., services, products.
     */
    @NotEmpty
    @Length(max = 50)
    private String category;

    /**
     * The amount of the invoice.
     */
    @NotNull
    private Double amount;

    /**
     * The date when the invoice was issued.
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    /**
     * The due date for the invoice payment.
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    /**
     * Whether the invoice has been paid or not.
     */
    @NotNull
    private Boolean paid = false;

    /**
     * The date when the invoice was paid.
     * This can be null if the invoice is not yet paid.
     */
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    /**
     * Additional description or details about the invoice.
     */
    @Length(max = 500)
    private String description;

    /**
     * The unique number of the invoice.
     */
    @NotEmpty
    @Length(max = 50)
    private String invoiceNumber;

    /**
     * The currency type of the invoice amount, e.g., USD, EUR.
     */
    @NotEmpty
    @Length(max = 10)
    private String currency;

    /**
     * The name of the customer associated with the invoice.
     */
    @NotEmpty
    @Length(max = 100)
    private String customerName;

    /**
     * The email of the customer associated with the invoice.
     */
    @NotEmpty
    @Email
    private String customerEmail;

    /**
     * The address of the customer associated with the invoice.
     */
    @Length(max = 200)
    private String customerAddress;

    // Getters and Setters for each field

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

}
