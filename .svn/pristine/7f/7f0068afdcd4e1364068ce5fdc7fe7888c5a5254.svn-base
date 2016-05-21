/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itexpertnepal.simpleinvoice.ui.controller.biller;

import com.itexpertnepal.simpleinvoice.api.BillerManager;
import com.itexpertnepal.simpleinvoice.domain.Biller;
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
public class BillerEditController {

    private Biller biller;
    private String selectedCode;

    @Autowired
    private BillerManager billerManager;

    @Autowired
    private SpringSecurityUtility securityUtility;

    public void loadBillers() {
        if (selectedCode != null) {
            this.biller = this.billerManager.findByCode(selectedCode);
        }
    }

    @CatchException
    public String onUpdate() {
        this.biller.getAuditInfo().setModifiedBy(securityUtility.getPrinciple().getUsername());
        this.biller.getAuditInfo().setModifiedOn(new Date());
        this.billerManager.update(biller);
        Messages.addInfo(null, "Products Edit Succesfully");
        return "pretty:biller";

    }

    public Biller getBiller() {
        return biller;
    }

    public void setBiller(Biller biller) {
        this.biller = biller;
    }

    public String getSelectedCode() {
        return selectedCode;
    }

    public void setSelectedCode(String code) {
        this.selectedCode = code;
    }

}
