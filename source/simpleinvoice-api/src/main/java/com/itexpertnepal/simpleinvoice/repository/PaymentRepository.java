package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.Payment;
import com.itexpertnepal.simpleinvoice.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author binay
 */
@Transactional(readOnly = true)
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p,c,b from Payment p,Customer c,Biller b where p.customerCode = c.code and p.billerCode = b.code and 1=1 order by p.auditInfo.createdOn desc")
    public List<Object[]> findAllPayment();

    @Query("select p from Payment p where p.customerCode = ?1")
    public List<Payment> findAllByCustCode(String custCode);

    @Query("select p from Payment p where p.paidMonth = ?1 and customerCode = ?2 and p.invoiceType = ?3")
    public Payment findByPaidMonth(String month, String custCode, Product.InvoiceType invoiceType);

}