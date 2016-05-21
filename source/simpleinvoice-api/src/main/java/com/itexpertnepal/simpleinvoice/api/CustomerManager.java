package com.itexpertnepal.simpleinvoice.api;

import com.itexpertnepal.simpleinvoice.api.common.CrudService;
import com.itexpertnepal.simpleinvoice.domain.Customer;
import com.itexpertnepal.simpleinvoice.domain.CustomerAccount;
import com.itexpertnepal.simpleinvoice.domain.CustomerService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author binay
 */
public interface CustomerManager extends CrudService<Customer, String> {

    public Customer findByCode(String code);

    public void enableordisableCustomerService(CustomerService customerService);

    public List<CustomerAccount> findByCustCode(String customerCode);

    public CustomerAccount findByCustomerCode(String custCode);

    public List<Customer> findAllDesc();

    public Map findAllCustomerNameStartWith(String expression);
    
    public void deleteCustomer(String customerCode);
    
    public void deleteCustomerService(CustomerService cs);
    


}
