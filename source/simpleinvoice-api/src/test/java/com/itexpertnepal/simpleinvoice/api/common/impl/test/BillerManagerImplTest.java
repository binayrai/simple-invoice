/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itexpertnepal.simpleinvoice.api.common.impl.test;

import com.itexpertnepal.simpleinvoice.api.BillerManager;
import com.itexpertnepal.simpleinvoice.domain.Biller;
import com.itexpertnepal.simpleinvoice.test.BaseTest;
import static org.junit.Assert.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 *
 * @author itexpertdell
 */
public class BillerManagerImplTest extends BaseTest {

    @Autowired
    private BillerManager billerManager;

    @Test
    public void addNewTest() {
        Biller biller = new Biller();
        biller.setName("Emershon H Lee");
        biller.setCode("GC-89");
        biller.setCity("LA");
        biller.setCountry("USA");
        biller = billerManager.addNew(biller);
        assertTrue(biller.getId() > 0);

    }

}
