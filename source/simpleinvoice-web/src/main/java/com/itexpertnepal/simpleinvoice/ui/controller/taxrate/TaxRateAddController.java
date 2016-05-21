package com.itexpertnepal.simpleinvoice.ui.controller.taxrate;

import com.itexpertnepal.simpleinvoice.api.TaxRateManager;
import com.itexpertnepal.simpleinvoice.domain.TaxRate;
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
 * @author itexpertdell
 */
@Component
@SpringRequestScoped
public class TaxRateAddController {

    private TaxRate taxRate;

    @Autowired
    private TaxRateManager taxRateManager;
    @Autowired
    private SpringSecurityUtility securityUtility;
    @Log
    private Logger logger;

    @PostConstruct
    public void init() {
        this.taxRate = new TaxRate();

    }

    @CatchException
    public String onSave() {
        taxRate.getAuditInfo().setCreatedBy(securityUtility.getPrinciple().getUsername());
        logger.debug("before service call");
        this.taxRateManager.addNew(taxRate);
        Messages.addInfo(null, "Tax rate  Succesfully" + " added");
        return "pretty:tax_rates";

    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
    }
    
    

}
