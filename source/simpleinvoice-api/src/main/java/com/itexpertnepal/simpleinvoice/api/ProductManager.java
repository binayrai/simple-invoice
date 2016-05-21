package com.itexpertnepal.simpleinvoice.api;

import com.itexpertnepal.simpleinvoice.api.common.CrudService;
import com.itexpertnepal.simpleinvoice.domain.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author binay
 */
public interface ProductManager extends CrudService<Product, String> {

    /**
     *
     * @param code
     * @return
     */
    public Product findByCode(String code);

    /**
     *
     * @param expression
     * @return
     */
    public Map findAllProductNameStartWith(String expression);

    /**
     *Find all product by Invoice type of General for non-recorrance 
     * @param invoiceType
     * @return
     */
    public List<Product> findAllByGeneralInvoiceType();

}
