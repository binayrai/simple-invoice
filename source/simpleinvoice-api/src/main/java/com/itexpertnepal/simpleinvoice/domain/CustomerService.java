package com.itexpertnepal.simpleinvoice.domain;

import com.itexpertnepal.simpleinvoice.domain.common.AbstractEntity;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author binay
 */
@Entity
@Table(name = "customer_services")
public class CustomerService extends AbstractEntity implements Serializable {

    private String productCode;
    private String productName;
    private FrequencyType frequency;
    private Customer customer;
    private double quantity;

    public CustomerService() {
    }

    public CustomerService(String productCode, FrequencyType frequency, Customer customer) {
        this.productCode = productCode;
        this.frequency = frequency;
        this.customer = customer;
    }

    @Column(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency_type")
    public FrequencyType getFrequencyType() {
        return frequency;
    }

    public void setFrequencyType(FrequencyType frequency) {
        this.frequency = frequency;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "quantity")
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.productCode != null ? this.productCode.hashCode() : 0);
        hash = 47 * hash + (this.frequency != null ? this.frequency.hashCode() : 0);
        hash = 47 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        hash = 47 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
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
        final CustomerService other = (CustomerService) obj;
        if ((this.productCode == null) ? (other.productCode != null) : !this.productCode.equals(other.productCode)) {
            return false;
        }
        if ((this.frequency == null) ? (other.frequency != null) : !this.frequency.equals(other.frequency)) {
            return false;
        }
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        if (this.getId() != other.getId() && (this.getId() == null || !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    public enum FrequencyType {

        Monthly, Quaterly, General
    }

}
