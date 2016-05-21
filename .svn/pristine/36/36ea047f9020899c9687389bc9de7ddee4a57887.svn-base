package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author binay
 */
public interface ProductRepository extends JpaRepository<Product, String> {

    public Product findByCode(String code);

    public List<Product> findByInvoiceType(Product.InvoiceType invoiceType);
}
