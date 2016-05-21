/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itexpertnepal.simpleinvoice.ui.controller.paymentType;

import com.itexpertnepal.simpleinvoice.api.PaymentTypesManager;
import com.itexpertnepal.simpleinvoice.domain.PaymentTypes;
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
public class PaymentTypesAddController {
    
    private PaymentTypes paymentTypes;
    
    @Autowired
    private PaymentTypesManager paymentTypesManager;
    
    @Autowired
    private SpringSecurityUtility securityUtility;
    
    @Log
    private Logger logger;
    
    @PostConstruct
    public void init(){
    this.paymentTypes = new PaymentTypes();
    }

     @CatchException
    public String onSave() {
        paymentTypes.getAuditInfo().setCreatedBy(securityUtility.getPrinciple().getUsername());
        logger.debug("before service call");
        this.paymentTypesManager.addNew(paymentTypes);
        Messages.addInfo(null, "Payment Types  Succesfully" + " added");
        return "pretty:payment_types";
    }

    public PaymentTypes getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(PaymentTypes paymentTypes) {
        this.paymentTypes = paymentTypes;
    }
    
    
    
}
