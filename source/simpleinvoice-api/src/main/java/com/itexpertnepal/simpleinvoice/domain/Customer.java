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
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author binay
 */
@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity implements Serializable {

    @NotNull
    private String code;
    private String name;
    private String accountNumber;
    private String contact;
    private String streetAddress;
    private String streetAddressTwo;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phoneNo;
    private String mobileNo;
    private Long fax;
    private String emial;
    private String creditCardHolderName;
    private String creditCardNo;
    private Date creditCardExpiryDate;
    private String customFieldOne;
    private String customFieldTwo;
    private String note;
    private Set<CustomerService> customerServices;
    private double balanceForward;
    private String companyCode;

    public Customer() {
        this.customerServices = new HashSet<CustomerService>();
    }

    public Customer(String code, String name, String accountNumber, String contact, String streetAddress) {
        this();
        this.code = code;
        this.name = name;
        this.accountNumber = accountNumber;
        this.contact = contact;
        this.streetAddress = streetAddress;
    }

    public Customer(String code, String name, String accountNumber, String streetAddress, String city, String state, String zipCode, String country, Date creditCardExpiryDate, String companyCode, double balanceForward) {
        this.code = code;
        this.name = name;
        this.accountNumber = accountNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.creditCardExpiryDate = creditCardExpiryDate;
        this.companyCode = companyCode;
        this.balanceForward = balanceForward;
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
   // @Size(min = 2, max = 225, message = "Length must be between 2 and 255.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "account_number")
    @Size(min = 1, max = 225, message = "Length must be between 1 and 255.")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "street_address")
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Column(name = "street_address_two")
    public String getStreetAddressTwo() {
        return streetAddressTwo;
    }

    public void setStreetAddressTwo(String streetAddressTwo) {
        this.streetAddressTwo = streetAddressTwo;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "zip_code")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "phone_no")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Phone no. must be numeric and 10 digit only")    
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "mobile_no")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile no. must be numeric and 10 digit only")    
     public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Column(name = "fax")
    public Long getFax() {
        return fax;
    }

    public void setFax(Long fax) {
        this.fax = fax;
    }

    @Column(name = "email")
    @Email(message = "Not a valid email address")
    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    @Column(name = "credit_card_holder_name")
    public String getCreditCardHolderName() {
        return creditCardHolderName;
    }

    public void setCreditCardHolderName(String creditCardHolderName) {
        this.creditCardHolderName = creditCardHolderName;
    }

    @Column(name = "credit_card_no")
    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "credit_card_expiry_date")
    public Date getCreditCardExpiryDate() {
        return creditCardExpiryDate;
    }

    public void setCreditCardExpiryDate(Date creditCardExpiryDate) {
        this.creditCardExpiryDate = creditCardExpiryDate;
    }

    @Column(name = "custom_field_one")
    public String getCustomFieldOne() {
        return customFieldOne;
    }

    public void setCustomFieldOne(String customFieldOne) {
        this.customFieldOne = customFieldOne;
    }

    @Column(name = "custom_field_two")
    public String getCustomFieldTwo() {
        return customFieldTwo;
    }

    public void setCustomFieldTwo(String customFieldTwo) {
        this.customFieldTwo = customFieldTwo;
    }

    @Column(name = "note", length = 1024)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    @Cascade({org.hibernate.annotations.CascadeType.ALL,org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public Set<CustomerService> getCustomerServices() {
        return customerServices;
    }

    public void setCustomerServices(Set<CustomerService> customerServices) {
        this.customerServices = customerServices;
    }

    public void addCustomerService(CustomerService customerService) {
        if (this.customerServices == null) {
            this.customerServices = new HashSet<CustomerService>();
        }
        if (this.customerServices.contains(customerService)) {
            return;
        }
        customerService.setCustomer(this);
        this.customerServices.add(customerService);

    }

    public void addCustomerService(Collection<CustomerService> customerService) {
        this.customerServices.addAll(customerService);

    }

    public void removeCustomerService(CustomerService customerService) {
        if (this.customerServices.contains(customerService)) {
            this.customerServices.remove(customerService);
        }

    }

    @Column(name = "company_code")
    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
    @Transient
    public double getBalanceForward() {
        return balanceForward;
    }

    public void setBalanceForward(double balanceForward) {
        this.balanceForward = balanceForward;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.code != null ? this.code.hashCode() : 0);
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
        final Customer other = (Customer) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return code;
    }

}
