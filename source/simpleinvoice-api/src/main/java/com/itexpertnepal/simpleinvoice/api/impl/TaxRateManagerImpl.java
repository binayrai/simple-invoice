/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itexpertnepal.simpleinvoice.api.impl;

import com.itexpertnepal.simpleinvoice.api.TaxRateManager;
import com.itexpertnepal.simpleinvoice.domain.TaxRate;
import com.itexpertnepal.simpleinvoice.repository.TaxRateRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author itexpertdell
 */
@Service
@Transactional(readOnly = true)
class TaxRateManagerImpl implements TaxRateManager {

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Transactional
    public TaxRate addNew(TaxRate t) {
        TaxRate taxRate = this.taxRateRepository.findByCode(t.getCode());
        if (taxRate != null) {
            throw new IllegalArgumentException("Tax Rate code already exist.");
        }
        t.setActive(true);
        return this.taxRateRepository.save(t);

    }

    public void addAll(List<TaxRate> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void update(TaxRate t) {
        this.taxRateRepository.save(t);

    }

    public void remove(TaxRate t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeBy(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TaxRate find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public List<TaxRate> findAll() {
        return this.taxRateRepository.findAll();
    }

    public List<TaxRate> findWithPaging(int pageSize, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeBy(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TaxRate find(Long id) {
        return this.taxRateRepository.findOne(id);
    }

}