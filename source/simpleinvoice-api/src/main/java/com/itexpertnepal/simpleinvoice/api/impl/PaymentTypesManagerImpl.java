package com.itexpertnepal.simpleinvoice.api.impl;

import com.itexpertnepal.simpleinvoice.api.PaymentTypesManager;
import com.itexpertnepal.simpleinvoice.domain.PaymentTypes;
import com.itexpertnepal.simpleinvoice.repository.PaymentTypesReository;
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
class PaymentTypesManagerImpl implements PaymentTypesManager {

    @Autowired
    private PaymentTypesReository paymentTypesRepository;

    @Transactional
    public PaymentTypes addNew(PaymentTypes t) {
        t.setActive(true);
        return this.paymentTypesRepository.save(t);
    }

    public void addAll(List<PaymentTypes> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void update(PaymentTypes t) {
        this.paymentTypesRepository.save(t);
    }

    public void remove(PaymentTypes t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeBy(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PaymentTypes find(Long id) {
        return this.paymentTypesRepository.findOne(id);
    }

    @Transactional
    public List<PaymentTypes> findAll() {
        return this.paymentTypesRepository.findAll();
    }

    public List<PaymentTypes> findWithPaging(int pageSize, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
