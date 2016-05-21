package com.itexpertnepal.simpleinvoice.api.common.impl.test;

import com.itexpertnepal.simpleinvoice.api.InvoiceManager;
import com.itexpertnepal.simpleinvoice.domain.Invoice;
import com.itexpertnepal.simpleinvoice.domain.InvoiceDetails;
import com.itexpertnepal.simpleinvoice.test.BaseTest;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author binay
 */
public class InvoiceManagerImplTest extends BaseTest {

    @Autowired
    private InvoiceManager invoiceManager;
    private static final String INVOICE_NUMER = "ABC11109";
    private static final String CUSTOMER_CODE = "ABC09";

    @Test(dependsOnMethods = {"com.itexpertnepal.simpleinvoice.api.common.impl.test.CustomerManagerImplTest.addNewTest"})
    public void addNewTest() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(INVOICE_NUMER);
        invoice.setCustomerCode(CUSTOMER_CODE);
        invoice.setBillerCode("BI09");
        invoice.setDate(new Date());
        invoice.addInvoiceDetails(new InvoiceDetails(19, "99899uu", 1098.09, invoice));
        invoice.addInvoiceDetails(new InvoiceDetails(10, "99899uu", 108.09, invoice));
        invoice = this.invoiceManager.addNew(invoice);
        assertEquals(invoice.getInvoiceDetailses().size(), 2);

    }

}
