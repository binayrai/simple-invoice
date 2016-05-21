package com.itexpertnepal.simpleinvoice.ui.controller.role;

import com.itexpertnepal.simpleinvoice.api.common.RoleManager;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringRequestScoped
public class RoleListController {

    @Autowired
    private RoleManager roleManager;
    @Log
    private Logger logger;

    @Autowired
    private SpringSecurityUtility securityUtility;
    private List<Role> roles;

    public List<Role> getRoles() {
        if (this.roles == null) {
            logger.debug("Role Type :" + securityUtility.getPrinciple().getRoleType());
            if (securityUtility.getPrinciple().getRoleType().equals(Role.RoleType.COMPANY_ADMIN)) {
                this.roles = this.roleManager.findAllClientRole();

            } else if (securityUtility.getPrinciple().getRoleType().equals(Role.RoleType.CLIENT)) {
                this.roles = this.roleManager.findAllClientUserRole();
            } else {
                this.roles = Collections.EMPTY_LIST;
            }
            logger.debug("role size:" + this.roles.size());
        }

        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
