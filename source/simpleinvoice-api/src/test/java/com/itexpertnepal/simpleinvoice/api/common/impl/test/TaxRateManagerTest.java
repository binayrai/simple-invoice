package com.itexpertnepal.simpleinvoice.api.common.impl.test;

import com.itexpertnepal.simpleinvoice.api.TaxRateManager;
import com.itexpertnepal.simpleinvoice.domain.TaxRate;
import com.itexpertnepal.simpleinvoice.test.BaseTest;
import static org.junit.Assert.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author itexpertdell
 */
public class TaxRateManagerTest extends BaseTest {
    
    @Autowired
    private TaxRateManager taxRateManager;
    
    @Test
    public void addNewTest(){
        TaxRate taxRate = new TaxRate();
        taxRate.setDescription("Emershon H Lee");
        taxRate.setRate(50.012);
        taxRate.setCode("23EE");
        taxRate = taxRateManager.addNew(taxRate);
        
       assertTrue(taxRate.getId()>0);
        
    }
    
}
