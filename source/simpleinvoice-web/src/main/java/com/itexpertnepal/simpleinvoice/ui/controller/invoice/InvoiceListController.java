package com.itexpertnepal.simpleinvoice.ui.controller.invoice;

import com.itexpertnepal.simpleinvoice.api.InvoiceManager;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringRequestScoped
public class InvoiceListController {

    private List<Object[]> listInvoices;
    private List<Object[]> listOfCustNullInvoice;
    @Autowired
    private InvoiceManager invoiceManager;
    @Log
    private Logger logger;

    public List<Object[]> getListInvoices() {
        if (this.listInvoices == null) {
            this.listInvoices = invoiceManager.findAllInvoices();
            this.listOfCustNullInvoice = invoiceManager.findAllByCustCodeNull();

            logger.debug("List before of invoice size:" + this.listInvoices.size());
            if (!this.listOfCustNullInvoice.isEmpty()) {
                for (Object[] objects : listOfCustNullInvoice) {
                    logger.debug("inside customer code null");
                    logger.debug("list of cust size:" + this.listOfCustNullInvoice.size());
                    listInvoices.add(objects);
                    logger.debug("List after of invoice size:" + this.listInvoices.size());
                }
            }
        }
        return listInvoices;
    }

    public void setListInvoices(List<Object[]> listInvoices) {
        this.listInvoices = listInvoices;
    }

}
