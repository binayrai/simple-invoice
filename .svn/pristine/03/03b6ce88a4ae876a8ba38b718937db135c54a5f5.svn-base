package com.itexpertnepal.simpleinvoice.ui.controller.user;

import com.itexpertnepal.simpleinvoice.api.common.UserManager;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.domain.common.User;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringRequestScoped
public class UserListController {

    private List<User> users;
    @Autowired
    private SpringSecurityUtility securityUtility;
    @Autowired
    private UserManager userManager;

    public List<User> getUsers() {
        if (this.users == null) {
            if (securityUtility.getPrinciple().getRoleType().equals(Role.RoleType.COMPANY_ADMIN)) {
                this.users = this.userManager.findAllClient();

            } else if (securityUtility.getPrinciple().getRoleType().equals(Role.RoleType.CLIENT)) {
                this.users = this.userManager.findAllClientUsers();
            } else {
                this.users = Collections.EMPTY_LIST;
            }
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
