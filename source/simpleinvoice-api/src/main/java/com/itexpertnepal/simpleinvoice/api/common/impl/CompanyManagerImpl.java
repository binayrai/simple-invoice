package com.itexpertnepal.simpleinvoice.api.common.impl;

import com.itexpertnepal.simpleinvoice.api.common.CompanyManager;
import com.itexpertnepal.simpleinvoice.api.common.RoleManager;
import com.itexpertnepal.simpleinvoice.domain.common.Company;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.domain.common.User;
import com.itexpertnepal.simpleinvoice.repository.CompanyRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author binay
 */
@Service
@Transactional(readOnly = true)
class CompanyManagerImpl implements CompanyManager {
    
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RoleManager roleManager;
    private static final Logger LOG = Logger.getLogger(CompanyManagerImpl.class.getName());
    
    @Transactional
    public Company addNew(Company t) {
        Company company = this.findByCode(t.getCode());
        if (company != null) {
            throw new IllegalArgumentException("Bank code already exist");
        }
        User user = new User();
        Set<Role> roles = new HashSet<Role>();
        if (t.getId() == null) {
            LOG.info("inside role not null");
            Role role = this.roleManager.findByRoleName(Role.DEFAULT_ROLE_NAME);
            roles.add(role);
            roles.add(this.roleManager.findByRoleName(Role.COMPANY_ADMIN_ROLE_NAME));
        }
        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hasPass = encoder.encodePassword(t.getUser().getPassword(), null);
        user.setUserName(t.getUser().getUserName());
        System.out.println("Cooperative User Name:" + user.getUserName());
        user.setUserName(t.getUser().getUserName());
        user.setCompanyCode(t.getCode());
        user.setRoles(roles);
        user.setRoleType(Role.RoleType.COMPANY_ADMIN);
        user.setActive(true);
        user.setPassword(hasPass);
        
        t.setUser(user);
        return this.companyRepository.save(t);
    }
    
    public void addAll(List<Company> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Transactional
    public void update(Company t) {
        this.companyRepository.save(t);
    }
    
    public void remove(Company t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void removeBy(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Company find(Long id) {
        return this.companyRepository.findOne(id);
    }
    
    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }
    
    public List<Company> findWithPaging(int pageSize, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Company findByCode(String code) {
        return this.companyRepository.findByCode(code);
    }
    
}
