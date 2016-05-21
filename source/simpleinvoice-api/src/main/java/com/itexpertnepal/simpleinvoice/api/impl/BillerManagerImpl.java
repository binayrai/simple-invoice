package com.itexpertnepal.simpleinvoice.api.impl;

import com.itexpertnepal.simpleinvoice.api.BillerManager;
import com.itexpertnepal.simpleinvoice.domain.Biller;
import com.itexpertnepal.simpleinvoice.repository.BillerRepository;
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
class BillerManagerImpl implements BillerManager {

    @Autowired
    private BillerRepository billerRepository;

    public Biller findByCode(String code) {
        return this.billerRepository.findByCode(code);
    }

    @Transactional
    public Biller addNew(Biller t) {
        Biller biller = this.findByCode(t.getCode());
        if (biller != null) {
            throw new IllegalArgumentException("Biller code already exist.");

        }
        t.setActive(true);
        return this.billerRepository.save(t);
    }

    public void addAll(List<Biller> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void update(Biller t) {
        this.billerRepository.save(t);
    }

    public void remove(Biller t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeBy(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Biller find(String id) {
        return this.billerRepository.findOne(id);
    }

    @Transactional
    public List<Biller> findAll() {
        return this.billerRepository.findAll();
    }

    public List<Biller> findWithPaging(int pageSize, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}