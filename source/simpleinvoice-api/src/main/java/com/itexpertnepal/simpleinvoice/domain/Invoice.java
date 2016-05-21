package com.itexpertnepal.simpleinvoice.domain;

import com.itexpertnepal.simpleinvoice.domain.common.AbstractEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author binay
 */
@Entity
@Table(name = "invoices")
public class Invoice extends AbstractEntity implements Serializable {

    private String invoiceNumber;
    private String customerCode;
    private String billerCode;
    private Date date;
    private Set<InvoiceDetails> invoiceDetailses;
    private CustomerService.FrequencyType frequencyType;
    private String customerName;
    private String billerName;
    private InvoicePayment invoicePayment;
    private Product.InvoiceType invoiceType;
    private String invoiceGeneratedDate;
    private double previousMonthBalForward;
    private double paidAmount;
    private double totalGenChargeAmt;

    public Invoice() {
        this.invoiceDetailses = new HashSet<InvoiceDetails>();
    }

    public Invoice(String customerCode, String billerCode) {
        this();
        this.customerCode = customerCode;
        this.billerCode = billerCode;
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

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "date")
    public Date getDate() {
        if (this.date == null) {
            this.date = new Date();
        }
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "invoice")
    public Set<InvoiceDetails> getInvoiceDetailses() {
        return invoiceDetailses;
    }

    public void setInvoiceDetailses(Set<InvoiceDetails> invoiceDetailses) {
        this.invoiceDetailses = invoiceDetailses;
    }

    public void addInvoiceDetails(InvoiceDetails invoiceDetails) {
        System.out.println("inside invoid details add");
        if (invoiceDetailses == null) {
            this.invoiceDetailses = new HashSet<InvoiceDetails>();
        }
        if (this.invoiceDetailses.contains(invoiceDetails)) {
            return;
        }
        invoiceDetails.setInvoice(this);
        this.invoiceDetailses.add(invoiceDetails);

    }

    public void addInvoiceDetails(Collection<InvoiceDetails> invoiceDetails) {
        this.invoiceDetailses.addAll(invoiceDetails);
    }

    public void removeInvoiceDetails(InvoiceDetails invoiceDetails) {
        if (this.invoiceDetailses.contains(invoiceDetails)) {
            this.invoiceDetailses.remove(invoiceDetails);
        }

    }

    @Column(name = "previous_month_bal_forward")
    public double getPreviousMonthBalForward() {
        return previousMonthBalForward;
    }

    public void setPreviousMonthBalForward(double previousMonthBalForward) {
        this.previousMonthBalForward = previousMonthBalForward;
    }

    @Column(name = "paid_amount")
    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency_type")
    public CustomerService.FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(CustomerService.FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    @Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Transient
    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_payment")
    public InvoicePayment getInvoicePayment() {
        return invoicePayment;
    }

    public void setInvoicePayment(InvoicePayment invoicePayment) {
        this.invoicePayment = invoicePayment;
    }

    public enum InvoicePayment {

        Paid, Unpaid, Partial
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_type")
    public Product.InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Product.InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    @Column(name = "invoice_generated_date")
    public String getInvoiceGeneratedDate() {
        return invoiceGeneratedDate;
    }

    public void setInvoiceGeneratedDate(String invoiceGeneratedDate) {
        this.invoiceGeneratedDate = invoiceGeneratedDate;
    }

    @Column(name = "total_gen_charge_amt")
    public double getTotalGenChargeAmt() {
        return totalGenChargeAmt;
    }

    public void setTotalGenChargeAmt(double totalGenChargeAmt) {
        this.totalGenChargeAmt = totalGenChargeAmt;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Invoice other = (Invoice) obj;
        if (this.getId() != other.getId() && (this.getId() == null || !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

}
