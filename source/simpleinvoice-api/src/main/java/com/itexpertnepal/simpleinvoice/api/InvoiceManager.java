package com.itexpertnepal.simpleinvoice.api;

import com.itexpertnepal.simpleinvoice.api.common.CrudService;
import com.itexpertnepal.simpleinvoice.domain.CustomerService;
import com.itexpertnepal.simpleinvoice.domain.Invoice;
import com.itexpertnepal.simpleinvoice.domain.InvoiceDetails;
import java.util.List;

/**
 *
 * @author binay
 */
public interface InvoiceManager extends CrudService<Invoice, String> {

    public Invoice findByInvoiceNumber(String invoiceNumber);

    public void reoccuranceTrigger(CustomerService.FrequencyType frequencyType);

    public List<InvoiceDetails> findAllDetails(Long id);

    public List<Object[]> findAllInvoices();

    public List<Object[]> findAllInvoiceDetails(Long invoiceId);

    public List<Invoice> findAllByMonthWise();

    public void paymentUpdate(Invoice invoice);

    public List<Object[]> findAllByCustCodeNull();

}
