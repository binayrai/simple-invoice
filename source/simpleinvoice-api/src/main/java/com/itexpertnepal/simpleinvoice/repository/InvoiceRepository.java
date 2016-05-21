package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.Invoice;
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
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    public Invoice findByInvoiceNumber(String code);

    @Query("select i,c,b,ca from Invoice i, Customer c,Biller b,CustomerAccount ca where i.customerCode = c.code and i.billerCode = b.code and i.customerCode =ca.customerCode  ORDER BY i.auditInfo.createdOn desc")
    public List<Object[]> findAllInvoice();

    @Query("select i from Invoice i where i.invoiceGeneratedDate = ?1 and i.invoiceType = ?2")
    public List<Invoice> findAllByMonthWise(String date, Product.InvoiceType invoiceType);

    @Query("select i from Invoice i where i.date  = (select max(i.date) from i where i.customerCode =?1 and i.invoiceType =?2)")
    public Invoice findByCustomerCodeWithMaxDate(String customerCode, Product.InvoiceType invoiceType);

    @Query("select i from Invoice i where i.invoiceGeneratedDate =?1")
    public Invoice findByInvoiceGeneratedDate(String genDate);

    public List<Invoice> findByCustomerCode(String custCode);

    @Query("select i,b from Invoice i,Biller b where  i.billerCode = b.code  and i.customerCode is null  ORDER BY i.auditInfo.createdOn desc")
    public List<Object[]> findAllInvoiceWithCustCodeNull();

}
