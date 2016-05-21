package com.itexpertnepal.simpleinvoice.ui.controller.taxrate;

import com.itexpertnepal.simpleinvoice.api.TaxRateManager;
import com.itexpertnepal.simpleinvoice.domain.TaxRate;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;                         
import org.springframework.stereotype.Component;

/**
 *
 * @author itexpertdell
 */

@Component
@SpringRequestScoped
public class TaxRateListController {

    @Autowired
    private TaxRateManager taxRateManager;

    private List<TaxRate> taxRates;

    public List<TaxRate> getTaxRates() {
        if (taxRates == null) {
            this.taxRates = this.taxRateManager.findAll();
        }
        return taxRates;
    }

    public void setTaxRates(List<TaxRate> taxRates) {
        this.taxRates = taxRates;
    }

}
