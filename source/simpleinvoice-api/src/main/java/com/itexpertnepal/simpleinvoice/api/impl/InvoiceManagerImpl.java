package com.itexpertnepal.simpleinvoice.api.impl;

import com.itexpertnepal.simpleinvoice.api.BillerManager;
import com.itexpertnepal.simpleinvoice.api.CustomerManager;
import com.itexpertnepal.simpleinvoice.api.InvoiceManager;
import com.itexpertnepal.simpleinvoice.api.PaymentManager;
import com.itexpertnepal.simpleinvoice.api.ProductManager;
import com.itexpertnepal.simpleinvoice.domain.Biller;
import com.itexpertnepal.simpleinvoice.domain.Customer;
import com.itexpertnepal.simpleinvoice.domain.CustomerAccount;
import com.itexpertnepal.simpleinvoice.domain.CustomerService;
import com.itexpertnepal.simpleinvoice.domain.Invoice;
import com.itexpertnepal.simpleinvoice.domain.InvoiceDetails;
import com.itexpertnepal.simpleinvoice.domain.Payment;
import com.itexpertnepal.simpleinvoice.domain.Product;
import com.itexpertnepal.simpleinvoice.repository.CustomerAccountRepository;
import com.itexpertnepal.simpleinvoice.repository.CustomerServiceRepository;
import com.itexpertnepal.simpleinvoice.repository.InvoiceDetailsRepository;
import com.itexpertnepal.simpleinvoice.repository.InvoiceRepository;
import com.itexpertnepal.simpleinvoice.utilities.StringUtls;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author binay
 */
@Service
@Transactional(readOnly = true)
class InvoiceManagerImpl implements InvoiceManager {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    private static final Logger LOG = Logger.getLogger(InvoiceManagerImpl.class.getName());
    @Autowired
    private CustomerManager customerManager;
    @Autowired
    private BillerManager billerManager;
    @Autowired
    private ProductManager productManager;
    @Autowired
    private CustomerServiceRepository customerServiceRepository;
    @Autowired
    private PaymentManager paymentManager;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM");

    @Transactional
    public Invoice addNew(Invoice t) {
        double totalChargeAmt = 0.0;
        if (t.getInvoiceDetailses().isEmpty()) {
            throw new IllegalArgumentException("Customer service or product cannot be empty");
        }
        Invoice invoice = this.invoiceRepository.findByInvoiceNumber(t.getInvoiceNumber());
        if (invoice != null) {
            throw new IllegalArgumentException("Invoice already exist.");
        }

        for (InvoiceDetails details : t.getInvoiceDetailses()) {
            details.setAuditInfo(t.getAuditInfo());
            totalChargeAmt += details.getQuantity() * details.getUnitPrice();

        }
        String newInvoiceNo = StringUtls.randomNumber(6);
        t.setInvoiceNumber(newInvoiceNo);
        t.setFrequencyType(CustomerService.FrequencyType.General);
        t.setInvoicePayment(Invoice.InvoicePayment.Unpaid);
        t.setInvoiceType(Product.InvoiceType.General);
        t.setInvoiceGeneratedDate(formatter.format(new Date()));
        t.setTotalGenChargeAmt(totalChargeAmt);
        t.setActive(true);
        return this.invoiceRepository.save(t);

    }

    private double calculateTax(InvoiceDetails invoiceDetails) {
        Product product = this.productManager.findByCode(invoiceDetails.getProductCode());
        double totalTax = (invoiceDetails.getQuantity() * invoiceDetails.getUnitPrice()) * invoiceDetails.getDefaultTax();
        return totalTax;

    }

    public void addAll(List<Invoice> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void update(Invoice t) {
        double totalChargeAmt = 0.0;
        for (InvoiceDetails details : t.getInvoiceDetailses()) {
            details.setAuditInfo(t.getAuditInfo());
            totalChargeAmt += details.getQuantity() * details.getUnitPrice();

        }
        t.setTotalGenChargeAmt(totalChargeAmt);
        this.invoiceRepository.save(t);
    }

    public void remove(Invoice t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeBy(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Invoice find(String id) {
        return this.invoiceRepository.findOne(id);
    }

    public List<Invoice> findAll() {
        return this.invoiceRepository.findAll();
    }

    public List<Invoice> findWithPaging(int pageSize, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Invoice findByInvoiceNumber(String invoiceNumber) {
        return this.invoiceRepository.findByInvoiceNumber(invoiceNumber);
    }

    @Transactional
    public void reoccuranceTrigger(CustomerService.FrequencyType frequencyType) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            Date date = new Date();
            Long quarterly = Long.parseLong(sdf.format(date));
            String billerCode = "";

            LOG.info("inside reocurrance trigger.");
            List<Customer> listCustomer = this.customerManager.findAll();

            Invoice invoice = new Invoice();

            List<Biller> billers = this.billerManager.findAll();
            for (Biller b : billers) {
                billerCode = b.getCode();
            }

            if (!listCustomer.isEmpty()) {
                LOG.info("inside customer not empty");
                LOG.info("customer list size:" + listCustomer.size());

                for (Customer customer : listCustomer) {
                    LOG.info("inside customer loop.");
                    //invoice 
                    String newInvoiceNo = StringUtls.randomNumber(6);
                    invoice.setCustomerCode(customer.getCode());
                    invoice.setInvoiceNumber(newInvoiceNo);
                    invoice.setCustomerName(customer.getName());
                    invoice.setDate(addOneDay(new Date()));//statement date
                    invoice.getAuditInfo().setCreatedBy("System");
                    invoice.getAuditInfo().setCreatedOn(new Date());
                    invoice.setInvoicePayment(Invoice.InvoicePayment.Unpaid);
                    invoice.setBillerCode(billerCode);
                    invoice.setInvoiceType(Product.InvoiceType.Reocurrance);
                    invoice.setInvoiceGeneratedDate(formatter.format(new Date()));
                    //for invoice details

                    List<CustomerService> customerService = this.customerServiceRepository.findByCustomerId(customer.getId());
                    if (customerService != null) {
                        for (CustomerService service : customerService) {
                            if (service.getFrequencyType().equals(CustomerService.FrequencyType.Monthly)) {
                                LOG.info("customer id:" + customer.getId());
                                LOG.info("customer service size:" + customerService.size());
                                invoice.addInvoiceDetails(addInvoiceDetails(service, invoice));
                                invoice.setFrequencyType(frequencyType);
                            }

                            if (quarterly % 3 == 0) {
                                LOG.info("inside quaterly");
                                if (service.getFrequencyType().equals(CustomerService.FrequencyType.Quaterly)) {
                                    invoice.addInvoiceDetails(addInvoiceDetails(service, invoice));
                                    invoice.setFrequencyType(frequencyType);
                                }

                            }

                        }

                    }
                    System.out.println("customer Code:" + customer.getCode());
                    Invoice tempInvoice = this.invoiceRepository.findByCustomerCodeWithMaxDate(customer.getCode(), Product.InvoiceType.Reocurrance);
                    System.out.println("temp invoice null");

                    if (tempInvoice != null) {
                        System.out.println("inside temp invoice not null.");
                        updateCustomerAccountFromTrigger(invoice);
                    }
                    invoice.setPreviousMonthBalForward(calculatePreviousBalForward(customer.getCode()));
                    this.invoiceRepository.save(invoice);
                    invoice = new Invoice();
                    invoice.getInvoiceDetailses().clear();
                    LOG.info("Invoice Generated successfully.");

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void updateCustomerAccountFromTrigger(Invoice invoice) {
        LOG.info("inside customer account update");
        CustomerAccount customerAccount = this.customerAccountRepository.findByCode(invoice.getCustomerCode());
        double currenttotalCharge = 0.0;
        double totalPayment = 0.0;
        double taxAmtPerProduct = 0.0;
        if (customerAccount != null) {
            LOG.info("inside customer account not null");
            /// calculete total charge
            for (InvoiceDetails details : invoice.getInvoiceDetailses()) {
                currenttotalCharge += details.getQuantity() * details.getUnitPrice();
                //taxAmtPerProduct += calculateTax(details);
            }
            Payment p = this.paymentManager.findByPaidMonth(parser.format(new Date()), invoice.getCustomerCode());
            if (p != null) {
                LOG.info("inside payment  not null");
                totalPayment = p.getAmount();
                LOG.info("payment amount:" + p.getAmount());
            }

            customerAccount.setCurrentAmount(currenttotalCharge);
            customerAccount.setTotalTaxAmt(taxAmtPerProduct);
            customerAccount.setBalanceForwared(currenttotalCharge + customerAccount.getBalanceForwared() - totalPayment);
            customerAccount.setTotalAmount(customerAccount.getBalanceForwared());
            customerAccount.setAuditInfo(invoice.getAuditInfo());
            this.customerAccountRepository.save(customerAccount);
            currenttotalCharge = 0.0;
            totalPayment = 0.0;
        }
    }

    private double calculatePreviousBalForward(String customerCode) {
        CustomerAccount account = this.customerAccountRepository.findByCode(customerCode);
        if (account != null) {
            return account.getBalanceForwared();
        }
        return 0.00;
    }

    private InvoiceDetails addInvoiceDetails(CustomerService service, Invoice invoice) {
        InvoiceDetails invoiceDetails = new InvoiceDetails();
        System.out.println("Product code from service:" + service.getProductCode());
        Product product = this.productManager.findByCode(service.getProductCode());
        if (product != null) {
            System.out.println("inside product not null");
            System.out.println("Product code:" + product.getCode());
            // invoice details
            invoiceDetails.setActive(true);
            invoiceDetails.setDefaultTax(product.getDefaultTax());
            invoiceDetails.setQuantity(service.getQuantity());
            invoiceDetails.setUnitPrice(product.getUnitPrice());
            invoiceDetails.setProductCode(product.getCode());
            invoiceDetails.getAuditInfo().setCreatedBy("System");
            invoiceDetails.getAuditInfo().setCreatedOn(new Date());
            invoiceDetails.setInvoice(invoice);
        }

        return invoiceDetails;
    }

    public Date addOneDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1); // add 10 days  

        date = cal.getTime();
        return date;

    }

    public List<InvoiceDetails> findAllDetails(Long id) {
        return this.invoiceDetailsRepository.findAllDetails(id);
    }

    public List<Object[]> findAllInvoices() {
        return this.invoiceRepository.findAllInvoice();
    }

    public List<Object[]> findAllInvoiceDetails(Long invoiceId) {
        return invoiceDetailsRepository.findAllInvoiceDetails(invoiceId);
    }

    public List<Invoice> findAllByMonthWise() {
        return this.invoiceRepository.findAllByMonthWise(formatter.format(new Date()), Product.InvoiceType.Reocurrance);
    }

    @Transactional
    public void paymentUpdate(Invoice invoice) {
        this.invoiceRepository.save(invoice);
    }

    public List<Object[]> findAllByCustCodeNull() {
        return this.invoiceRepository.findAllInvoiceWithCustCodeNull();
    }

}
