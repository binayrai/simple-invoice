package com.itexpertnepal.simpleinvoice.api.common;

import com.itexpertnepal.simpleinvoice.domain.common.Company;

/**
 *
 * @author binay
 */
public interface CompanyManager extends CrudService<Company, Long> {

    public Company findByCode(String code);

}
