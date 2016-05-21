/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.PaymentTypes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author itexpertdell
 */
public interface PaymentTypesReository extends JpaRepository<PaymentTypes,Long>{
    
}
