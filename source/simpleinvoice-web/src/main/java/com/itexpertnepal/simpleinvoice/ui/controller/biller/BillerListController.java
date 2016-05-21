/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itexpertnepal.simpleinvoice.ui.controller.biller;

import com.itexpertnepal.simpleinvoice.api.BillerManager;
import com.itexpertnepal.simpleinvoice.domain.Biller;
import com.itexpertnepal.simpleinvoice.domain.common.Permission;
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
public class BillerListController {
    
    @Autowired
    private BillerManager billerManager;
    
    private String displayBillerAdd;
    
    @Autowired
      private SpringSecurityUtility securityUtility;

    private List<Biller> biller;
    
    public List<Biller> getBillers(){
          if(this.biller == null){
    this.biller = this.billerManager.findAll();
    }
        return biller;
    
    }

  
    public void setBiller(List<Biller> biller) {
        this.biller = biller;
    }

    public String getDisplayBillerAdd() {
        if(this.biller == null){
              this.biller = this.billerManager.findAll();
              if(this.biller.isEmpty()){
                  this.displayBillerAdd = Permission.BILLER_ADD.toString(); 
              }
              else{
                  this.displayBillerAdd = null;
              }
           
        }
        return displayBillerAdd;
    }

    public void setDisplayBillerAdd(String displayBillerAdd) {
        this.displayBillerAdd = displayBillerAdd;
    }
    
    
    
}
