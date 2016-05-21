package com.itexpertnepal.simpleinvoice.ui.controller.company;

import com.itexpertnepal.simpleinvoice.api.common.CompanyManager;
import com.itexpertnepal.simpleinvoice.domain.common.Company;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringViewScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import java.util.Date;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringViewScoped
public class CompanyEditController {

    private Company company;
    private String selectedCode;
    @Autowired
    private CompanyManager companyManager;
    @Autowired
    private SpringSecurityUtility securityUtility;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getSelectedCode() {
        return selectedCode;
    }

    public void setSelectedCode(String selectedCode) {
        this.selectedCode = selectedCode;
    }

    public void loadCompanies() {
        if (selectedCode != null) {
            this.company = this.companyManager.findByCode(selectedCode);
        }
    }

    @CatchException
    public String onUpdate() {
        this.company.getAuditInfo().setModifiedBy(securityUtility.getPrinciple().getUsername());
        company.getUser().setUserName(company.getAdminName());
        this.company.getAuditInfo().setModifiedOn(new Date());
        this.companyManager.update(company);
        Messages.addInfo(null, "Bank Edit Succesfully");
        return "pretty:companies";

    }

}
