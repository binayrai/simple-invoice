package com.itexpertnepal.simpleinvoice.ui.controller.taxrate;

import com.itexpertnepal.simpleinvoice.api.TaxRateManager;
import com.itexpertnepal.simpleinvoice.domain.TaxRate;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringViewScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import java.util.Date;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author itexpertdell
 */
@Component
@SpringViewScoped
public class TaxRateEditController {

    private TaxRate taxRate;
    private Long selectedId;

    @Autowired
    private TaxRateManager taxRateManager;

    @Autowired
    private SpringSecurityUtility securityUtility;

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
    }

    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public void loadTaxRates() {
        if (selectedId != null) {
            this.taxRate = this.taxRateManager.find(selectedId);
        }
    }

    @CatchException
    public String onUpdate() {
        this.taxRate.getAuditInfo().setModifiedBy(securityUtility.getPrinciple().getUsername());
        this.taxRate.getAuditInfo().setModifiedOn(new Date());
        this.taxRateManager.update(taxRate);
        Messages.addInfo(null, "Tax Rates Edited Succesfully");
        return "pretty:tax_rates";

    }

}
