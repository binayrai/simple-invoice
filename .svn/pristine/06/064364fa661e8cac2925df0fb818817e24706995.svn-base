/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itexpertnepal.simpleinvoice.api.common.impl.test;

import com.itexpertnepal.simpleinvoice.api.PaymentTypesManager;
import com.itexpertnepal.simpleinvoice.domain.PaymentTypes;
import com.itexpertnepal.simpleinvoice.test.BaseTest;
import static org.junit.Assert.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 *
 * @author itexpertdell
 */
public class PaymentTypesManagerTest extends BaseTest {
    
    @Autowired
    private PaymentTypesManager paymentTypesManager;
    
    @Test
    public void addNewTest(){
    PaymentTypes paymentTypes = new PaymentTypes();
    paymentTypes.setDescription("Dental Service");
    paymentTypes = paymentTypesManager.addNew(paymentTypes);
    
    assertTrue(paymentTypes.getId()>0);}
    
    
    
}
