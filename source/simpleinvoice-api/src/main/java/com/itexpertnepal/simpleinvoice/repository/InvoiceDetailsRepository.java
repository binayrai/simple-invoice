package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.InvoiceDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author binay
 */
@Transactional(readOnly = true)
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Long> {

    @Query("select i from InvoiceDetails i where i.invoice.id = ?1")
    public List<InvoiceDetails> findAllDetails(Long id);

    @Query("select i,p from InvoiceDetails i ,Product p where i.productCode = p.code and i.invoice.id = ?1")
    public List<Object[]> findAllInvoiceDetails(Long id);

}
