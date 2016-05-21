/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itexpertnepal.simpleinvoice.ui.controller.paymentType;

import com.itexpertnepal.simpleinvoice.api.PaymentTypesManager;
import com.itexpertnepal.simpleinvoice.domain.PaymentTypes;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author itexpertdell
 */
@Component
@SpringRequestScoped 
public class PaymentTypesListController {
    
    @Autowired
    private PaymentTypesManager paymentTypeManager;
    
    private List<PaymentTypes> paymentTypes;
    

    public List<PaymentTypes> getPaymentTypes() {
        if(paymentTypes == null){
        this.paymentTypes = this.paymentTypeManager.findAll();
        }
        return paymentTypes;
    }

    public void setPaymentTypes(List<PaymentTypes> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }
    
  

    
}
