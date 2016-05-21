/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itexpertnepal.simpleinvoice.ui.controller.products;

import com.itexpertnepal.simpleinvoice.api.ProductManager;
import com.itexpertnepal.simpleinvoice.domain.Product;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import javax.annotation.PostConstruct;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author itexpertdell
 */
@Component
@SpringRequestScoped
public class ProductAddController {
    private Product product;
    
        @Autowired
    private ProductManager productManager;
    @Autowired
    private SpringSecurityUtility securityUtility;
    @Log
    private Logger logger;

    @PostConstruct
    public void init() {
        this.product = new Product();

    }

    @CatchException
    public String onSave() {
        product.getAuditInfo().setCreatedBy(securityUtility.getPrinciple().getUsername());
        logger.debug("before service call");
        this.productManager.addNew(product);
        Messages.addInfo(null, "Product  Succesfully" + " added");
        return "pretty:products";

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    

    
}