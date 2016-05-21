package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.common.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author binay
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

    public Company findByCode(String code);

}
