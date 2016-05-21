/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itexpertnepal.simpleinvoice.ui.controller.products;

import com.itexpertnepal.simpleinvoice.api.ProductManager;
import com.itexpertnepal.simpleinvoice.domain.Product;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringViewScoped;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author itexpertdell
 */
@Component
@SpringViewScoped
public class ProductListController {
    
    @Autowired
    private ProductManager productManager;
    
    @Autowired
    private SpringSecurityUtility securityUtility;
    private List<Product> product;
    


    public List<Product> getProduct() {
          if(this.product == null){
    this.product = this.productManager.findAll();
    }
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
    
    
    
}
