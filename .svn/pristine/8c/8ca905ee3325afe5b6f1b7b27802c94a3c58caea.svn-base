/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itexpertnepal.simpleinvoice.api.common.impl.test;

import com.itexpertnepal.simpleinvoice.api.ProductManager;
import com.itexpertnepal.simpleinvoice.domain.Product;
import com.itexpertnepal.simpleinvoice.test.BaseTest;
import static org.junit.Assert.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 *
 * @author itexpertdell
 */
public class ProductManagerImpTest extends BaseTest {

    @Autowired
    private ProductManager productManager;

    @Test
    public void addNewTest() {
        Product product = new Product();
        product.setCode("100");
        product.setCustomField("Man Power");
        product.setUnitPrice(12.20);
        product.setName("Dental Product");
        product = this.productManager.addNew(product);
        assertTrue(product.getId() > 0);

    }

}
