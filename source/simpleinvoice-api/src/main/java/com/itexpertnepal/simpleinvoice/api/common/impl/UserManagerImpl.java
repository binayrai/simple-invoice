package com.itexpertnepal.simpleinvoice.api.common.impl;

import com.itexpertnepal.simpleinvoice.api.common.UserManager;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.domain.common.User;
import com.itexpertnepal.simpleinvoice.repository.RoleRepository;
import com.itexpertnepal.simpleinvoice.repository.UserRepository;

import java.util.List;
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
class UserManagerImpl implements UserManager {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    private static final Logger LOG = Logger.getLogger(UserManagerImpl.class.getName());
    
    @Transactional
    public User addNew(User t) {
        String hasPass = "";
        if (t.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User must have role");
        }
        User user = this.userRepository.findByUserName(t.getUserName());
        if (user != null) {
            throw new IllegalArgumentException("User name already exists");
        }
        if (t.getRoles().size() > 0) {
            boolean notDefaultRole = false;
            for (Role r : t.getRoles()) {
                if (r.getRoleName().equalsIgnoreCase(Role.DEFAULT_ROLE_NAME)) {
                    notDefaultRole = true;
                }
            }
            if (!notDefaultRole) {
                Role role = this.roleRepository.findByRoleName(Role.DEFAULT_ROLE_NAME);
                t.addRole(role);
            }
        }
        PasswordEncoder encoder = new Md5PasswordEncoder();
        hasPass = encoder.encodePassword(t.getPassword(), null);
        t.setPassword(hasPass);
        t.setRootUser(true);
        t.setActive(true);
        
        return this.userRepository.save(t);
    }
    
    @Transactional
    public void removeUser(User user) {
        user.setRootUser(false);
        this.userRepository.save(user);
    }
    
    @Transactional
    public void addAll(List<User> list) {
        this.userRepository.save(list);
    }
    
    @Transactional
    public void update(User t) {
        LOG.info("user id:" + t.getUserName());
        this.userRepository.save(t);
    }
    
    public void remove(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void removeBy(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public User find(Long id) {
        return this.userRepository.findOne(id);
    }
    
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
    
    public List<User> findWithPaging(int pageSize, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public User findByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }
    
    @Transactional
    public void changePassword(String userName, String password) {
        User user = this.userRepository.findByUserName(userName);
        if (user == null) {
            throw new IllegalArgumentException("Username does not exists");
        }
        PasswordEncoder encoder = new Md5PasswordEncoder();
        user.setPassword(encoder.encodePassword(password, null));
        this.userRepository.save(user);
    }
    
    public List<User> findAllClient() {
        return this.userRepository.findByRoleType(Role.RoleType.CLIENT);
    }
    
    public List<User> findAllClientUsers() {
        return this.userRepository.findByRoleType(Role.RoleType.CLIENT_USER);
    }
    
}
