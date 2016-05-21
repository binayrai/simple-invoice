package com.itexpertnepal.simpleinvoice.api.impl;

import com.itexpertnepal.simpleinvoice.api.CustomerManager;
import com.itexpertnepal.simpleinvoice.domain.Customer;
import com.itexpertnepal.simpleinvoice.domain.CustomerAccount;
import com.itexpertnepal.simpleinvoice.domain.CustomerService;
import com.itexpertnepal.simpleinvoice.repository.CustomerAccountRepository;
import com.itexpertnepal.simpleinvoice.repository.CustomerRepository;
import com.itexpertnepal.simpleinvoice.repository.CustomerServiceRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author binay
 */
@Service
@Transactional(readOnly = true)
class CustomerManagerImpl implements CustomerManager {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private CustomerServiceRepository customerServiceRepository;
    @Autowired
    private DataSource dataSource;
    private static final Logger LOG = Logger.getLogger(CustomerManagerImpl.class.getName());

    private Connection getDBConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Transactional
    public Customer addNew(Customer t) {
        Customer customer = this.customerRepository.findByCode(t.getCode());
        if (customer != null) {
            throw new IllegalArgumentException("Customer code already exist.");
        }
        CustomerAccount tempCustAcc = this.customerAccountRepository.findByCode(t.getCode());
        if (tempCustAcc != null) {
            throw new IllegalArgumentException("Customer code already exist.");

        }
        CustomerAccount customerAccount = new CustomerAccount(t.getCode(), t.getBalanceForward());
        customerAccount.setAuditInfo(t.getAuditInfo());
        customerAccount.setIsBalanceForward(true);
        this.customerAccountRepository.save(customerAccount);
        //  this.addClient(t);
        t.setActive(true);
        return this.customerRepository.save(t);
    }

    public void addAll(List<Customer> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void update(Customer t) {
        if (t.getCustomerServices().isEmpty()) {
            throw new IllegalArgumentException("Customer service or product cannot be empty");
            
        }
        
        System.out.println("Bal Forward before:"+ t.getBalanceForward());
        
        CustomerAccount customerAccount = this.customerAccountRepository.findByCode(t.getCode());
        
        if(customerAccount != null){
            System.out.println("Cust Bal Forward:"+ customerAccount.getBalanceForwared());
            customerAccount.setBalanceForwared(t.getBalanceForward());
            this.customerAccountRepository.save(customerAccount); 
        }
       
        this.customerRepository.save(t);
    }

    public void remove(Customer t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeBy(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Customer find(String id) {
        return this.customerRepository.findOne(id);
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    public List<Customer> findWithPaging(int pageSize, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Customer findByCode(String code) {
        return this.customerRepository.findByCode(code);
    }

    public void enableordisableCustomerService(CustomerService customerService) {
        this.customerServiceRepository.save(customerService);
    }

    public List<CustomerAccount> findByCustCode(String customerCode) {
        return this.customerAccountRepository.findByCustomerCode(customerCode);
    }

    public CustomerAccount findByCustomerCode(String custCode) {
        return this.customerAccountRepository.findByCode(custCode);
    }

    public List<Customer> findAllDesc() {
        return this.customerRepository.findAllDesc();
    }

    public Map findAllCustomerNameStartWith(String expression) {
        Map results = new HashMap<String, String>();
        ResultSet rs = null;
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String sql = "select code,name from customers  where upper(name) like upper(?) and 1=1 and is_active= 'true'";
        try {
            connect = this.getDBConnection();
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, expression + "%");
            // preparedStatement.setString(3, branchCode);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                results.put(rs.getString("code"), rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                preparedStatement.close();
                connect.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        LOG.info("Result size:" + results.size());
        return results;
    }

    @Transactional
    public void deleteCustomer(String customerCode) {
       Customer customer = customerRepository.findByCode(customerCode);
       if(customer != null){
         
           this.customerRepository.delete(customer);
       }
        
    }

    @Transactional
    public void deleteCustomerService(CustomerService cs) {
        this.customerServiceRepository.deleteService(cs.getId());
    }
    
    


}
