package com.itexpertnepal.simpleinvoice.api.impl;

import com.itexpertnepal.simpleinvoice.api.CustomerManager;
import com.itexpertnepal.simpleinvoice.api.ProductManager;
import com.itexpertnepal.simpleinvoice.api.UploadManager;
import com.itexpertnepal.simpleinvoice.domain.Customer;
import com.itexpertnepal.simpleinvoice.domain.Product;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
class UploadManagerImpl implements UploadManager {

    @Autowired
    private CustomerManager customerManager;
    @Autowired
    private ProductManager productManager;
    private static final Logger LOG = Logger.getLogger(UploadManagerImpl.class.getName());
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public void bulkCustomerUpload(String data, String userName) {
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
                // String code, String name, String accountNumber, String streetAddress, String city, String state, String zipCode, String country, Date creditCardExpiryDate, String companyCode,double balanceForward
                Customer customer = new Customer(csvData[0], csvData[1], csvData[2], csvData[3], csvData[4], csvData[5], csvData[6], csvData[7], activationDate, csvData[9], Double.parseDouble(csvData[10]));
                customer.getAuditInfo().setCreatedBy(userName);
                customer.getAuditInfo().setCreatedOn(new Date());
                customer.setActive(true);
                customerManager.addNew(customer);
            } catch (Exception e) {
                hasError = true;
                errorBuilder.append("Error at line no ").append(index).append(".").append(e.getMessage());
                errorBuilder.append("\n");
                e.printStackTrace();
            }
            lineData = null;
            csvData = null;
            index++;
        }

        if (hasError) {
            throw new IllegalArgumentException(errorBuilder.toString());
        }

    }

    @Transactional
    public void bulkProductUpload(String data, String userName) {
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
                // String code, String name, Double unitPrice, Long defaultTax
                Product product = new Product(csvData[0], csvData[1], Double.parseDouble(csvData[2]), Long.parseLong(csvData[3]));
                product.setInvoiceType(Product.InvoiceType.Reocurrance);
                product.getAuditInfo().setCreatedBy(userName);
                product.getAuditInfo().setCreatedOn(new Date());
                product.setActive(true);
                productManager.addNew(product);
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
    }

    private Date validateCSVAndGetDate(String lineData) {

        String[] data = lineData.split(",");
        if (data.length != 11) {
            LOG.info("data length:" + data.length);
            throw new IllegalArgumentException("Invalid data");
        }

        try {
            //Validate Purchase date
            Date d = dateFormat.parse(data[8]);
            return d;
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Invalid date format");
        }

    }
}
