/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itexpertnepal.simpleinvoice.api;

import com.itexpertnepal.simpleinvoice.api.common.CrudService;
import com.itexpertnepal.simpleinvoice.domain.Biller;

/**
 *
 * @author itexpertdell
 */
public interface BillerManager extends CrudService<Biller, String> {
   public Biller findByCode(String code); 
   

}
