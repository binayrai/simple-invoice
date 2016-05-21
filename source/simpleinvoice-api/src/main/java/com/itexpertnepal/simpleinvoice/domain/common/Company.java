package com.itexpertnepal.simpleinvoice.domain.common;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author binay
 */
@Entity
@Table(name = "companies")
public class Company extends AbstractEntity implements Serializable {

    private String code;
    private String name;
    private String address;
    private String phoneNo;
    private User user;
    private String adminMobileNo;
    private String adminUserName;
    private String adminName;

    public Company() {
        this.user = new User();

    }

    public Company(String code, String name, User user, String adminUserName) {
        this.code = code;
        this.name = name;
        this.user = user;
        this.adminUserName = adminUserName;
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

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "admin_mobile_no")
    public String getAdminMobileNo() {
        return adminMobileNo;
    }

    public void setAdminMobileNo(String adminMobileNo) {
        this.adminMobileNo = adminMobileNo;
    }

    @Column(name = "admin_user_name", unique = true, updatable = false)
    @Email(message = "User name must be Email format.")
    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    @Column(name = "admin_name")
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

}
