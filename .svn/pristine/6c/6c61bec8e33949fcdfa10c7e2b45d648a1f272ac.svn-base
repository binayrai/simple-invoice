package com.itexpertnepal.simpleinvoice.ui.security;

import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringSessionScoped;
import java.io.Serializable;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringSessionScoped
public class MenuChecker implements Serializable {

    @Autowired
    private SpringSecurityUtility securityUtility;

    public boolean hasRight(String menuCode) {
        User user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principle = auth.getPrincipal();
            if (principle instanceof User) {
                user = (User) principle;
                Collection<GrantedAuthority> authoritys = user.getAuthorities();
                for (GrantedAuthority ga : authoritys) {
                    CustomGrantedAuthorityImpl c = (CustomGrantedAuthorityImpl) ga;
                    if (c.getPermission().contains(menuCode)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean hasGroupRight(String menus) {
        String[] menusArray = StringUtils.split(menus, ",");
        boolean result = false;
        for (String s : menusArray) {
            result = this.hasRight(s);
            if (result) {
                break;
            }
        }
        return result;
    }

    public boolean isAdmin() {
        if (securityUtility.getPrinciple().getRoleType() == Role.RoleType.ADMIN) {
            return true;
        }
        return false;
    }
}
