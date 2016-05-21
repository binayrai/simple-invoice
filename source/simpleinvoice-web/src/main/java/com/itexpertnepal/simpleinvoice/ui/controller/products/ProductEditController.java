/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itexpertnepal.simpleinvoice.ui.controller.products;

import com.itexpertnepal.simpleinvoice.api.ProductManager;
import com.itexpertnepal.simpleinvoice.domain.Product;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringViewScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import java.util.Date;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author itexpertdell
 */
@Component
@SpringViewScoped
public class ProductEditController {

    private Product product;
    private String code;

    @Autowired
    private ProductManager productManger;

    @Autowired
    private SpringSecurityUtility securityUtility;

    public void loadProducts() {
        if (code != null) {
            this.product = this.productManger.findByCode(code);
        }
    }

    @CatchException
    public String onUpdate() {
        this.product.getAuditInfo().setModifiedBy(securityUtility.getPrinciple().getUsername());
        this.product.getAuditInfo().setModifiedOn(new Date());
        this.productManger.update(product);
        Messages.addInfo(null, "Products Edit Succesfully");
        return "pretty:products";

    }

    @CatchException
    public String onDelete() {      
        this.productManger.remove(product);
        Messages.addInfo(null, "Product Deleted Succesfully");
        return "pretty:products";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}