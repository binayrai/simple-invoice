package com.itexpertnepal.simpleinvoice.ui.security;

import com.itexpertnepal.simpleinvoice.domain.common.Role.RoleType;
import java.io.Serializable;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author binay
 */
public class CustomSpringSecurityUser extends User implements Serializable {

    private RoleType roleType;
    private String companyCode = null;
    private String customerCode = null;

    public CustomSpringSecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public CustomSpringSecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, RoleType roleType) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.roleType = roleType;
    }

    public CustomSpringSecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, RoleType roleType, String companyCode, String customerCode) {
        this(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, roleType);
        this.companyCode = companyCode;
        this.customerCode = customerCode;

    }

    public RoleType getRoleType() {
        return roleType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

}
