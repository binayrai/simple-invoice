package com.itexpertnepal.simpleinvoice.api.impl;

import com.itexpertnepal.simpleinvoice.domain.Invoice;
import com.itexpertnepal.simpleinvoice.repository.InvoiceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
public class Facade {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

}
