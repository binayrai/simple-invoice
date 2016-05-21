package com.itexpertnepal.simpleinvoice.domain;

import com.itexpertnepal.simpleinvoice.domain.common.AbstractEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 *
 * @author binay
 */
@Entity
@Table(name = "products")
public class Product extends AbstractEntity implements Serializable {

    private String code;
    private String name;
    private Double unitPrice;
    private Long defaultTax;
    private String customField;
    private String notes;
    private InvoiceType invoiceType;

    public Product() {
    }

    public Product(String code, String name, Double unitPrice, Long defaultTax) {
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.defaultTax = defaultTax;
    }

    @Column(name = "code")
    @Pattern(regexp = "[a-zA-Z0-9[-]]*", message = "Is alfa-numeric and Must not contain any special characters except [-]")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "unit_price")
    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Column(name = "default_tax")
    public Long getDefaultTax() {
        return defaultTax;
    }

    public void setDefaultTax(Long defaultTax) {
        this.defaultTax = defaultTax;
    }

    @Column(name = "custom_field")
    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }

    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "invoice_type")
    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public enum InvoiceType {

        General, Reocurrance

    }

}