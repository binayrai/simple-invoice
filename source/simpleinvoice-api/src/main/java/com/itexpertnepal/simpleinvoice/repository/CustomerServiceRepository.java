package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.CustomerService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author binay
 */
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {

    @Query("select cs from CustomerService cs where cs.customer.id =?1 ")
    public List<CustomerService> findByCustomerId(Long id);
    
    @Modifying
    @Query("delete CustomerService c where c.id = ?1")
    public void deleteService(Long id);
    
    public CustomerService findByProductCode(String productCode);

}
