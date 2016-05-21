package com.itexpertnepal.simpleinvoice.ui.controller.user;

import com.itexpertnepal.simpleinvoice.api.common.UserManager;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.domain.common.User;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringViewScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import java.util.List;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringViewScoped
public class UserEditController {

    private User user;
    private String selectedUserName;
    @Autowired
    private UserManager userManager;
    private List<Role> roles;
    @Autowired
    private SpringSecurityUtility securityUtility;

    public void loadUsers() {
        if (selectedUserName != null) {
            this.user = this.userManager.findByUserName(selectedUserName);

        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSelectedUserName() {
        return selectedUserName;
    }

    public void setSelectedUserName(String selectedUserName) {
        this.selectedUserName = selectedUserName;
    }

    public List<Role> getRoles() {
        if (this.user != null) {
            this.setRoles(this.user.roleAsList());
        }
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @CatchException
    public String onUpdate() {
        this.user.addRole(roles);
        this.user.setCompanyCode(securityUtility.getPrinciple().getCompanyCode());
        this.user.setRoleType(Role.RoleType.CLIENT);
        if (securityUtility.getPrinciple().getRoleType().equals(Role.RoleType.CLIENT)) {
            this.user.setRoleType(Role.RoleType.CLIENT_USER);
        }
        this.userManager.update(user);
        Messages.addInfo(null, "User Edited Sucessfully ");
        return "pretty:users";
    }

    public String onRemove() {
        this.user.setActive(false);
        this.userManager.removeUser(user);
        Messages.addInfo(null, "User Deleted Sucessfully ");
        return "pretty:users";
    }

}
