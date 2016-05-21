package com.itexpertnepal.simpleinvoice.ui.controller.company;

import com.itexpertnepal.simpleinvoice.api.common.CompanyManager;
import com.itexpertnepal.simpleinvoice.domain.common.Company;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringRequestScoped
public class CompanyListController {

    @Autowired
    private CompanyManager companyManager;
    private List<Company> companys;

    public List<Company> getCompanys() {
        if (this.companys == null) {
            this.companys = this.companyManager.findAll();
        }
        return companys;
    }

    public void setCompanys(List<Company> companys) {
        this.companys = companys;
    }

}
