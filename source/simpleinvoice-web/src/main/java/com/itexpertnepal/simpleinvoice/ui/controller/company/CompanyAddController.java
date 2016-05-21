package com.itexpertnepal.simpleinvoice.ui.controller.company;

import com.itexpertnepal.simpleinvoice.api.common.CompanyManager;
import com.itexpertnepal.simpleinvoice.domain.common.Company;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import javax.annotation.PostConstruct;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringRequestScoped
public class CompanyAddController {

    private Company company;

    @Autowired
    private CompanyManager companyManager;
    @Autowired
    private SpringSecurityUtility securityUtility;
    
    @Log
    private Logger logger;

    private String confirmPassword;
    private String password;

    @PostConstruct
    public void init() {
        this.company = new Company();

    }

    @CatchException
    public String onSave() {
        logger.debug("inside on save.");
        if (!this.password.equals(this.confirmPassword)) {
            Messages.addError(null, "New password and confirm passowrd doesn't match");
            return null;
        }
        System.out.println("user name: " + securityUtility.getPrinciple().getUsername());
        company.getAuditInfo().setCreatedBy(securityUtility.getPrinciple().getUsername());
        company.getUser().setPassword(this.confirmPassword);
        company.getUser().setUserName(company.getAdminUserName());
        logger.debug("before service call");
        this.companyManager.addNew(company);

        Messages.addInfo(null, "User Succesfully" + "added");
        return "pretty:companies";
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
