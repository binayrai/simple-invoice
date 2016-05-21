package com.itexpertnepal.simpleinvoice.ui.security;

import com.itexpertnepal.simpleinvoice.api.common.UserManager;
import com.itexpertnepal.simpleinvoice.domain.common.User;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component("userDetailsService")
@Scope("singleton")
public class UserVerificationService implements UserDetailsService, Serializable {

    @Autowired
    private Assembler assembler;
    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        user = this.userManager.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User name or password invalid");
        }
        return this.assembler.buildUserFromUserEntity(user);

    }
}
