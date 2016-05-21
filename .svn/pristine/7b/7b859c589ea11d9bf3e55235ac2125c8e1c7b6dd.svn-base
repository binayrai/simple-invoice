package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author binay
 */
public interface CustomerRepository extends JpaRepository<Customer, String> {

    public Customer findByCode(String code);

    @Query("select c from Customer c where 1=1 order by c.auditInfo.createdOn desc ")
    public List<Customer> findAllDesc();

}
