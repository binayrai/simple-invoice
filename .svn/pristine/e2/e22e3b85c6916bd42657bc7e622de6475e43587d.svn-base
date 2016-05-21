package com.itexpertnepal.simpleinvoice.ui.security;

import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringSessionScoped;
import java.io.Serializable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringSessionScoped
public class SpringSecurityUtility implements Serializable {

    public CustomSpringSecurityUser getPrinciple() {
        CustomSpringSecurityUser user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principle = auth.getPrincipal();
            if (principle instanceof User) {
                user = (CustomSpringSecurityUser) principle;
            }
        } else {
            System.out.println("Authentication is null");
            user = new CustomSpringSecurityUser(null, null, true, true, true, true, null, null, null, null);
        }
        return user;
    }
}
