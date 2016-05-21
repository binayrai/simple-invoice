package com.itexpertnepal.simpleinvoice.ui.controller.role;

import com.itexpertnepal.simpleinvoice.api.common.RoleManager;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringRequestScoped;
import com.itexpertnepal.simpleinvoice.ui.aspect.CatchException;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringRequestScoped
public class RoleAddController {

    private Role role;
    @Log
    private Logger logger;

    @Autowired
    private SpringSecurityUtility securityUtility;
    @Autowired
    private RoleManager roleManager;
    private List<String> selectedUserPermission;
    private List<String> selectedRolePermission;
    private List<String> selectedTaxRatePermission;
    private List<String> selectedPaymentTypePermission;
    private List<String> selectedProductsPermission;
    private List<String> selectedCustomersPermission;
    private List<String> selectedBillersPermission;
    private List<String> selectedInvoicePermission;
    private List<String> selectedPaymentPermission;
    private List<String> selectedRecurrencePermission;

    public RoleAddController() {
    }

    @PostConstruct
    public void init() {
        this.role = new Role();
        this.selectedUserPermission = new ArrayList<String>();
        this.selectedRolePermission = new ArrayList<String>();
        this.selectedTaxRatePermission = new ArrayList<String>();
        this.selectedPaymentTypePermission = new ArrayList<String>();
        this.selectedProductsPermission = new ArrayList<String>();
        this.selectedCustomersPermission = new ArrayList<String>();
        this.selectedBillersPermission = new ArrayList<String>();
        this.selectedInvoicePermission = new ArrayList<String>();
        this.selectedPaymentTypePermission = new ArrayList<String>();
        this.selectedRecurrencePermission = new ArrayList<String>();

    }

    @CatchException
    public String onSave() {
        this.role.setRoleType(Role.RoleType.CLIENT);
        if (securityUtility.getPrinciple().getRoleType().equals(Role.RoleType.CLIENT)) {
            this.role.setRoleType(Role.RoleType.CLIENT_USER);
        }
        this.role.setCompanyCode(securityUtility.getPrinciple().getCompanyCode());
        this.role.getAuditInfo().setCreatedBy(securityUtility.getPrinciple().getUsername());
        this.role.getAuditInfo().setCreatedOn(new Date());
        this.addPermission();
        this.roleManager.addNew(role);
        Messages.addInfo(null, "Role Sucessfully " + " added");
        return "pretty:roles";
    }

    public void addPermission() {
        role.addPermission(selectedUserPermission);
        role.addPermission(selectedRolePermission);
        role.addPermission(selectedTaxRatePermission);
        role.addPermission(selectedPaymentTypePermission);
        role.addPermission(selectedProductsPermission);
        role.addPermission(selectedCustomersPermission);
        role.addPermission(selectedBillersPermission);
        role.addPermission(selectedInvoicePermission);
        role.addPermission(selectedPaymentPermission);
        role.addPermission(selectedRecurrencePermission);

    }

    public List<String> getSelectedUserPermission() {
        return selectedUserPermission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSelectedUserPermission(List<String> selectedUserPermission) {
        this.selectedUserPermission = selectedUserPermission;
    }

    public List<String> getSelectedRolePermission() {
        return selectedRolePermission;
    }

    public void setSelectedRolePermission(List<String> selectedRolePermission) {
        this.selectedRolePermission = selectedRolePermission;
    }

    public List<String> getSelectedTaxRatePermission() {
        return selectedTaxRatePermission;
    }

    public void setSelectedTaxRatePermission(List<String> selectedTaxRatePermission) {
        this.selectedTaxRatePermission = selectedTaxRatePermission;
    }

    public List<String> getSelectedPaymentTypePermission() {
        return selectedPaymentTypePermission;
    }

    public void setSelectedPaymentTypePermission(List<String> selectedPaymentTypePermission) {
        this.selectedPaymentTypePermission = selectedPaymentTypePermission;
    }

    public List<String> getSelectedProductsPermission() {
        return selectedProductsPermission;
    }

    public void setSelectedProductsPermission(List<String> selectedProductsPermission) {
        this.selectedProductsPermission = selectedProductsPermission;
    }

    public List<String> getSelectedCustomersPermission() {
        return selectedCustomersPermission;
    }

    public void setSelectedCustomersPermission(List<String> selectedCustomersPermission) {
        this.selectedCustomersPermission = selectedCustomersPermission;
    }

    public List<String> getSelectedBillersPermission() {
        return selectedBillersPermission;
    }

    public void setSelectedBillersPermission(List<String> selectedBillersPermission) {
        this.selectedBillersPermission = selectedBillersPermission;
    }

    public List<String> getSelectedInvoicePermission() {
        return selectedInvoicePermission;
    }

    public void setSelectedInvoicePermission(List<String> selectedInvoicePermission) {
        this.selectedInvoicePermission = selectedInvoicePermission;
    }

    public List<String> getSelectedPaymentPermission() {
        return selectedPaymentPermission;
    }

    public void setSelectedPaymentPermission(List<String> selectedPaymentPermission) {
        this.selectedPaymentPermission = selectedPaymentPermission;
    }

    public List<String> getSelectedRecurrencePermission() {
        return selectedRecurrencePermission;
    }

    public void setSelectedRecurrencePermission(List<String> selectedRecurrencePermission) {
        this.selectedRecurrencePermission = selectedRecurrencePermission;
    }

}
