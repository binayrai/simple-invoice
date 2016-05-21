/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itexpertnepal.simpleinvoice.domain;

import com.itexpertnepal.simpleinvoice.domain.common.AbstractEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author itexpertdell\ \
 *
 *
 */
@Entity
@Table(name = "biller")
public class Biller extends AbstractEntity implements Serializable {

    private String code;
    private String name;
    private String phone;
    private String mobile;
    private Long fax;
    private String email;
    private String streetAddress;
    private String streetAddress2;
    private String city;
    private String country;
    private String paypalName;
    private String paypalNotifyUrl;
    private String paypalReturnUrl;
    private String ewawId;
    private String customField;
    private String invoiceFooter;
    private String note;

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "Moblie")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getFax() {
        return fax;
    }

    public void setFax(Long fax) {
        this.fax = fax;
    }

    @Column(name = "Email")
    @Email(message = "Not a valid email address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "StreetAddress")
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Column(name = "StreetAddress2")
    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "PayPalName")
    public String getPaypalName() {
        return paypalName;
    }

    public void setPaypalName(String paypalName) {
        this.paypalName = paypalName;
    }

    @Column(name = "PayPalNotifyUrl")
    public String getPaypalNotifyUrl() {
        return paypalNotifyUrl;
    }

    public void setPaypalNotifyUrl(String paypalNotifyUrl) {
        this.paypalNotifyUrl = paypalNotifyUrl;
    }

    @Column(name = "PayPalReturnUrl")
    public String getPaypalReturnUrl() {
        return paypalReturnUrl;
    }

    public void setPaypalReturnUrl(String paypalReturnUrl) {
        this.paypalReturnUrl = paypalReturnUrl;
    }

    @Column(name = "EwaWId")
    public String getEwawId() {
        return ewawId;
    }

    public void setEwawId(String ewawId) {
        this.ewawId = ewawId;
    }

    @Column(name = "CustomField")
    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }

    @Column(name = "InvoiceFooter")
    public String getInvoiceFooter() {
        return invoiceFooter;
    }

    public void setInvoiceFooter(String invoiceFooter) {
        this.invoiceFooter = invoiceFooter;
    }

    @Column(name = "Note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "Code")
    @Pattern(regexp = "[a-zA-Z0-9[-]]*", message = "Is alfa-numeric and Must not contain any special characters except [-]")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
