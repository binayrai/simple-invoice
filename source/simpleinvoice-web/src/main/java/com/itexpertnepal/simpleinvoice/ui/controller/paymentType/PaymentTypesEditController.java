/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itexpertnepal.simpleinvoice.ui.controller.paymentType;

import com.itexpertnepal.simpleinvoice.api.PaymentTypesManager;
import com.itexpertnepal.simpleinvoice.domain.PaymentTypes;
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
public class PaymentTypesEditController {

    private PaymentTypes paymentTypes;
    private Long selectedId;

    @Autowired
    private PaymentTypesManager paymentTypesManager;

    @Autowired
    private SpringSecurityUtility securityUtility;

    public PaymentTypes getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(PaymentTypes paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public void loadPaymentTypes() {
        if (selectedId != null) {
            this.paymentTypes = this.paymentTypesManager.find(selectedId);
        }
    }

    @CatchException
    public String onUpdate() {
        this.paymentTypes.getAuditInfo().setModifiedBy(securityUtility.getPrinciple().getUsername());
        this.paymentTypes.getAuditInfo().setModifiedOn(new Date());
        this.paymentTypesManager.update(paymentTypes);
        Messages.addInfo(null, "Tax Rates Edit Succesfully");
        return "pretty:payment_types";

    }
}
