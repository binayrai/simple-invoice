package com.itexpertnepal.simpleinvoice.api.common.impl.test;

import com.itexpertnepal.simpleinvoice.api.CustomerManager;
import com.itexpertnepal.simpleinvoice.domain.Customer;
import com.itexpertnepal.simpleinvoice.domain.CustomerService;
import com.itexpertnepal.simpleinvoice.test.BaseTest;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author binay
 */
public class CustomerManagerImplTest extends BaseTest {

    @Autowired
    private CustomerManager customerManager;
    private static final String CUSTOMER_CODE = "ABC09";

    @Test
    public void addNewTest() {
        Customer customer = new Customer();
        customer.getAuditInfo().setCreatedBy("binay");
        customer.getAuditInfo().setCreatedOn(new Date());
        customer.setName("Achut");
        customer.setCode(CUSTOMER_CODE);
        customer.setAccountNumber("BJDLFJDJF09");
        customer.setPhoneNo("9999999999");
        customer.setFax(9999999999L);
        customer.setMobileNo("8888888888");
        customer.addCustomerService(new CustomerService("MNO09", CustomerService.FrequencyType.Monthly, customer));
        customer.addCustomerService(new CustomerService("MNO0934", CustomerService.FrequencyType.Quaterly, customer));
        customer = this.customerManager.addNew(customer);
        assertEquals(customer.getCustomerServices().size(), 2);

    }

}