package com.itexpertnepal.simpleinvoice.api.impl;

import com.itexpertnepal.simpleinvoice.api.InvoiceManager;
import com.itexpertnepal.simpleinvoice.api.PaymentManager;
import com.itexpertnepal.simpleinvoice.domain.Invoice;
import com.itexpertnepal.simpleinvoice.domain.Payment;
import com.itexpertnepal.simpleinvoice.domain.Product;
import com.itexpertnepal.simpleinvoice.repository.PaymentRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
class PaymentManagerImpl implements PaymentManager {
    
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private InvoiceManager invoiceManager;
    
    private static final Logger LOG = Logger.getLogger(PaymentManagerImpl.class.getName());
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
    
    @Transactional
    public String bulkPaymentProcess(String data, String userName) {
        String[] csvData = null;
        LOG.info("Data is : " + data);
        Scanner scanner = new Scanner(data);
        StringBuilder errorBuilder = new StringBuilder();
        String lineData = null;
        int index = 1;
        boolean hasError = false;
        while (scanner.hasNextLine()) {
            lineData = scanner.nextLine();
            LOG.info("Line : " + lineData);
            try {
                csvData = lineData.split(",");
                Date activationDate = this.validateCSVAndGetDate(lineData);
                //String invoiceNumber, String customerCode, String billerCode, double amount, Date date, String note, PaymentType paymentType
                Payment payment = new Payment(csvData[0], csvData[1], csvData[2], Double.parseDouble(csvData[3]), activationDate, csvData[5], Payment.PaymentType.Cheque);
                payment.getAuditInfo().setCreatedBy(userName);
                payment.getAuditInfo().setCreatedOn(new Date());
                payment.setPaidMonth(formatter.format(new Date()));
                payment.setInvoiceType(Product.InvoiceType.Reocurrance);
                payment.setActive(true);
                processInvoicePayment(csvData[0], userName, Double.parseDouble(csvData[3]));
                paymentRepository.save(payment);
                
            } catch (Exception e) {
                hasError = true;
                errorBuilder.append("Error at line no ").append(index).append(".").append(e.getMessage());
                errorBuilder.append("\n");
            }
            lineData = null;
            csvData = null;
            index++;
        }
        
        if (hasError) {
            throw new IllegalArgumentException(errorBuilder.toString());
        }
        return null;
    }
    
    private Date validateCSVAndGetDate(String lineData) {
        
        String[] data = lineData.split(",");
        if (data.length != 6) {
            throw new IllegalArgumentException("Invalid data");
        }
        
        try {
            //Validate Purchase date
            Date d = dateFormat.parse(data[4]);
            return d;
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Invalid date format");
        }
        
    }
    
    private void processInvoicePayment(String invoiceNumber, String userName, double paidAmt) {
        Invoice invoice = this.invoiceManager.findByInvoiceNumber(invoiceNumber);
        if (invoice == null) {
            throw new IllegalArgumentException("Following invoice number : " + invoiceNumber + " doesn't exits.");
        }
        if (invoice.getInvoicePayment().equals(Invoice.InvoicePayment.Paid)) {
            throw new IllegalArgumentException("Following invoice number : " + invoiceNumber + " already paid");
        }
        invoice.setInvoicePayment(Invoice.InvoicePayment.Paid);
        invoice.getAuditInfo().setModifiedBy(userName);
        invoice.getAuditInfo().setModifiedOn(new Date());
        invoice.setPaidAmount(paidAmt);
        this.invoiceManager.paymentUpdate(invoice);
        
    }
    
    public List<Object[]> finAll() {
        return this.paymentRepository.findAllPayment();
    }
    
    @Transactional
    public String processInvoicePayment(Payment payment) {
        this.processInvoicePayment(payment.getInvoiceNumber(), payment.getAuditInfo().getCreatedBy(), payment.getAmount());
        payment.setInvoiceType(Product.InvoiceType.General);
        payment = this.paymentRepository.save(payment);
        return String.valueOf(payment.getId());
    }
    
    public List<Payment> findAllByCustCode(String custCode) {
        return this.paymentRepository.findAllByCustCode(custCode);
    }
    
    public Payment findByPaidMonth(String month, String custCode) {
        return this.paymentRepository.findByPaidMonth(month, custCode, Product.InvoiceType.Reocurrance);
    }
    
}
