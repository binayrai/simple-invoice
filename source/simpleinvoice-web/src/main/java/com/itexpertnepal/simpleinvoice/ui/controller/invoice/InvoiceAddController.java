package com.itexpertnepal.simpleinvoice.ui.controller.invoice;

import com.itexpertnepal.simpleinvoice.api.CustomerManager;
import com.itexpertnepal.simpleinvoice.api.InvoiceManager;
import com.itexpertnepal.simpleinvoice.api.ProductManager;
import com.itexpertnepal.simpleinvoice.api.TaxRateManager;
import com.itexpertnepal.simpleinvoice.domain.Invoice;
import com.itexpertnepal.simpleinvoice.domain.InvoiceDetails;
import com.itexpertnepal.simpleinvoice.domain.Product;
import com.itexpertnepal.simpleinvoice.domain.TaxRate;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringViewScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringViewScoped
public class InvoiceAddController {

    private Invoice invoice;
    private InvoiceDetails invoiceDetails;
    private List<InvoiceDetails> listInvoiceDetailses;
    private List<TaxRate> listTaxRates;
    private List<Product> listProducts;

    @Log
    private Logger logger;
    @Autowired
    private InvoiceManager invoiceManager;
    @Autowired
    private SpringSecurityUtility securityUtility;
    @Autowired
    private TaxRateManager taxRateManager;
    @Autowired
    private ProductManager productManager;
    @Autowired
    private CustomerManager customerManager;
    private String selectedCustomer;
    Map<String, String> map = null;

    @PostConstruct
    public void init() {
        this.invoice = new Invoice();
        this.invoiceDetails = new InvoiceDetails();
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public InvoiceDetails getInvoiceDetails() {
        return invoiceDetails;
    }

    public void addInvoiceDetails(ActionEvent event) {
        logger.debug("inside invoice details");
        this.invoice.addInvoiceDetails(invoiceDetails);
        logger.debug("Details size:" + listInvoiceDetailses.size());
        this.invoiceDetails = new InvoiceDetails();
    }

    public void removeInvoiceDetails(InvoiceDetails invoiceDetails) {
        this.invoice.removeInvoiceDetails(invoiceDetails);
    }

    public void setInvoiceDetails(InvoiceDetails invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public List<InvoiceDetails> getListInvoiceDetailses() {
        if (this.invoice != null) {
            this.listInvoiceDetailses = new ArrayList<InvoiceDetails>(this.invoice.getInvoiceDetailses());
        }
        return listInvoiceDetailses;
    }

    public void setListInvoiceDetailses(List<InvoiceDetails> listInvoiceDetailses) {
        this.listInvoiceDetailses = listInvoiceDetailses;
    }

    public List<TaxRate> getListTaxRates() {
        return listTaxRates;
    }

    public void setListTaxRates(List<TaxRate> listTaxRates) {
        this.listTaxRates = listTaxRates;
    }

    public List<Product> getListProducts() {
        if (this.listProducts == null) {
            this.listProducts = this.productManager.findAllByGeneralInvoiceType();
        }
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public void onChangeProduct() {
        logger.debug("inside change product");
        if (this.invoiceDetails.getProductCode() != null) {
            Product product = this.productManager.findByCode(this.invoiceDetails.getProductCode());
            if (product != null) {
                this.listTaxRates = new ArrayList<TaxRate>();
                logger.debug("inside product not null");
                TaxRate taxRate = this.taxRateManager.find(product.getDefaultTax());
                this.listTaxRates.add(taxRate);
                this.invoiceDetails.setTaxCode(taxRate.getCode());
                this.invoiceDetails.setUnitPrice(product.getUnitPrice());

            } else {
                Messages.addInfo(null, "cannot read product ");
            }
        }

    }

    public String getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(String selectedCustomer) {
        if (selectedCustomer != null && selectedCustomer.contains("#")) {
            String[] memberSet = selectedCustomer.split("#");
            this.invoice.setCustomerCode(memberSet[1]);
            this.invoice.setCustomerName(memberSet[0]);
        } else {
            this.invoice.setCustomerName(selectedCustomer);
        }
        this.selectedCustomer = selectedCustomer;
    }

    @CatchException
    public List<String> customerAutoComplete(String query) {
        logger.debug("inside customer auto complete");
        List<String> results = new ArrayList<String>();
        map = this.customerManager.findAllCustomerNameStartWith(query);
        if (map != null) {
            for (String key : map.keySet()) {
                String value = map.get(key);
                results.add(value + '#' + key);
            }
        }
        return results;
    }

    @CatchException
    public String onSave() {
        logger.debug("inside on save");
        this.invoice.getAuditInfo().setCreatedBy(this.securityUtility.getPrinciple().getUsername());
        this.invoice.getAuditInfo().setCreatedOn(new Date());
        this.invoiceManager.addNew(invoice);
        Messages.addInfo(null, "Invoice generated successfully.");
        return "pretty:invoice";
    }

}
