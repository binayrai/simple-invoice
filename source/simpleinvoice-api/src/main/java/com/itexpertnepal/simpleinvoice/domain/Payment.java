package com.itexpertnepal.simpleinvoice.domain;

import com.itexpertnepal.simpleinvoice.domain.common.AbstractEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author binay
 */
@Entity
@Table(name = "payments")
public class Payment extends AbstractEntity implements Serializable {

    private String invoiceNumber;
    private String customerCode;
    private String billerCode;
    private double amount;
    private String note;
    private Date date;
    private PaymentType paymentType;
    private String paidMonth;
    private Product.InvoiceType invoiceType;

    public Payment() {
    }

    public Payment(String invoiceNumber, String customerCode, String billerCode, double amount, Date date, String note, PaymentType paymentType) {
        this.invoiceNumber = invoiceNumber;
        this.customerCode = customerCode;
        this.billerCode = billerCode;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.paymentType = paymentType;
    }

    @Column(name = "invoice_number")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Column(name = "customer_code")
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Column(name = "biller_code")
    public String getBillerCode() {
        return billerCode;
    }

    public void setBillerCode(String billerCode) {
        this.billerCode = billerCode;
    }

    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    public PaymentType getPaymentType() {
        return paymentType;
    }

    @Column(name = "paid_month")
    public String getPaidMonth() {
        return paidMonth;
    }

    public void setPaidMonth(String paidMonth) {
        this.paidMonth = paidMonth;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_type")
    public Product.InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Product.InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public enum PaymentType {

        Cash, Cheque, Credit_Card
    }

}
