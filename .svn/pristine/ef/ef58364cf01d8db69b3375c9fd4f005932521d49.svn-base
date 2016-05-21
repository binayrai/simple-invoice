package com.itexpertnepal.simpleinvoice.ui.security;

import java.util.List;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *
 * @author binay
 */
public class CustomGrantedAuthorityImpl extends GrantedAuthorityImpl {

    private List<String> permission;

    public CustomGrantedAuthorityImpl(String role) {
        super(role);
    }

    public CustomGrantedAuthorityImpl(List<String> permission, String role) {
        super(role);
        this.permission = permission;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }
}
