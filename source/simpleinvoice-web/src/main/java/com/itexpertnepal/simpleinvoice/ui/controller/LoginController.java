package com.itexpertnepal.simpleinvoice.ui.controller;

import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import java.io.IOException;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component("loginBean")
@SpringRequestScoped
public class LoginController {

    private String username;
    private String password;
    private String msg;

    public LoginController() {
        msg = null;
    }

    //@PostConstruct
    public String load() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            Exception e = (Exception) Faces.getSessionAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
            if (e instanceof BadCredentialsException) {
                System.out.println("user name and pwd not valid");
                msg = "Username or password not valid.";
                //context.addMessage(null, new FacesMessage("Username or password not valid.", null));
            } else if (e instanceof DisabledException) {
                Messages.addError(null, "Account disabled");
            }
            if (e instanceof Exception) {
                Faces.setSessionAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, null);
            }
            return null;
        }
        return "pretty:home";
    }

    public void process() throws IOException {
        msg = null;
        Faces.getExternalContext().dispatch("/j_spring_security_check");
        Faces.getContext().responseComplete();

    }

    public void logout() throws IOException {
        Faces.invalidateSession();
        Faces.getExternalContext().dispatch("/logout");
        Faces.getContext().responseComplete();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
