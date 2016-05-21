package com.itexpertnepal.simpleinvoice.ui.controller.user;

import com.itexpertnepal.simpleinvoice.api.common.UserManager;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.domain.common.User;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringRequestScoped
public class UserAddController {

    private User user;
    private List<Role> roles;
    @Autowired
    private UserManager userManager;
    @Autowired
    private SpringSecurityUtility securityUtility;
    private String confirmPassword;
    private String newPassword;

    @PostConstruct
    public void init() {
        this.user = new User();
        this.roles = new ArrayList<Role>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @CatchException
    public String onSave() {
        if (!this.newPassword.equals(this.confirmPassword)) {
            Messages.addError(null, "New password and confirm password doesn't match");
            return null;
        }
        this.user.setRoleType(Role.RoleType.CLIENT);
        if (securityUtility.getPrinciple().getRoleType().equals(Role.RoleType.CLIENT)) {
            this.user.setRoleType(Role.RoleType.CLIENT_USER);
        }
        this.user.addRole(roles);
        this.user.setCompanyCode(securityUtility.getPrinciple().getCompanyCode());

        this.user.setPassword(newPassword);
        this.userManager.addNew(user);
        Messages.addInfo(null, "User Sucessfully " + " added");
        return "pretty:users";
    }
}
