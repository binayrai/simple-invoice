package com.itexpertnepal.simpleinvoice.ui.controller;

import com.itexpertnepal.simpleinvoice.api.common.UserManager;
import com.itexpertnepal.simpleinvoice.domain.common.User;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringViewScoped
public class PreferenceController {

    private boolean showPassForm = false;
    private User currentUser;
    private String confirmPassword;
    private String currentPassword;
    private String reConfirmPassword;
    @Autowired
    private UserManager userManager;


    public PreferenceController() {
        this.currentUser = new User();
        this.currentUser.setUserName(HttpUtils.getUserPrinciple());
    }

    public boolean isShowPassForm() {
        return showPassForm;
    }

    public void setShowPassForm(boolean showPassForm) {
        this.showPassForm = showPassForm;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getReConfirmPassword() {
        return reConfirmPassword;
    }

    public void setReConfirmPassword(String reConfirmPassword) {
        this.reConfirmPassword = reConfirmPassword;
    }

    public void enablePasswordPanel(ActionEvent event) {
        this.showPassForm = true;
    }

    public String onChangePassword() {

        System.out.println("Inside change password");
        if (!this.confirmPassword.equals(this.reConfirmPassword)) {

            Messages.addError(null, "New password and confirm password doesn't match");
            return null;
        }
        this.userManager.changePassword(this.currentUser.getUserName(), reConfirmPassword);
        Messages.addInfo(null, "Password changed sucessfully.");

        return null;
    }

}
