package com.itexpertnepal.simpleinvoice.api.common.impl.test;

import com.itexpertnepal.simpleinvoice.api.common.CompanyManager;
import com.itexpertnepal.simpleinvoice.domain.common.Company;
import com.itexpertnepal.simpleinvoice.test.BaseTest;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 *
 * @author binay
 */
public class CompanyManagerImplTest extends BaseTest {

    @Autowired
    private CompanyManager companyManager;

    @Test
    public void addNewTest() {
        Company company = new Company();
        company.getUser().setUserName("ram@gmail.com");
        company.getUser().setPassword("123445");
        company.setCode("abc09");
        company.setAdminUserName("binay@gmail.com.np");
        company = companyManager.addNew(company);

        assertTrue(company.getId() > 0);

    }
}
