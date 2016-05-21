package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.CustomerAccount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author binay
 */
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

    public List<CustomerAccount> findByCustomerCode(String customerCode);

    @Query("select c from CustomerAccount c where c.customerCode =?1 ")
    public CustomerAccount findByCode(String customerCode);

}
