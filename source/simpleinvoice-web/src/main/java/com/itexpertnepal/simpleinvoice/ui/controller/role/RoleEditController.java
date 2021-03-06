package com.itexpertnepal.simpleinvoice.ui.controller.role;

import com.itexpertnepal.simpleinvoice.api.common.RoleManager;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringViewScoped;
import com.itexpertnepal.simpleinvoice.ui.security.SpringSecurityUtility;
import com.itexpertnepal.simpleinvoice.utilities.CollectionUtility;
import com.itexpertnepal.simpleinvoice.utl.logger.Log;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.omnifaces.util.Messages;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringViewScoped
public class RoleEditController {

    private Role role;
    private long selectedRoleId;
    @Autowired
    private SpringSecurityUtility securityUtility;
    @Autowired
    private RoleManager roleManager;
    @Log
    private Logger logger;
    private List<String> selectedUserPermission = new ArrayList<String>();
    private List<String> selectedRolePermission = new ArrayList<String>();
    private List<String> selectedTaxRatePermission = new ArrayList<String>();
    private List<String> selectedPaymentTypePermission = new ArrayList<String>();
    private List<String> selectedProductsPermission = new ArrayList<String>();
    private List<String> selectedCustomersPermission = new ArrayList<String>();
    private List<String> selectedBillersPermission = new ArrayList<String>();
    private List<String> selectedInvoicePermission = new ArrayList<String>();
    private List<String> selectedPaymentPermission = new ArrayList<String>();
    private List<String> selectedRecurrencePermission = new ArrayList<String>();

    public void loadRoles() {
        logger.debug("inside load roles");

        if (this.selectedRoleId != 0 || this.selectedRoleId > 0) {
            this.role = this.roleManager.find(selectedRoleId);
            if (role != null) {
                logger.info("Role Type:" + role.getRoleType());
                this.filterPermission();
                logger.info("Selected User Permission:" + this.selectedUserPermission.size());
                this.role.cleanAllPermission();

            }

        }
    }

    private void filterPermission() {
        this.selectedUserPermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "USER"));
        this.selectedRolePermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "ROLE"));
        this.selectedTaxRatePermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "TAX_RATE"));
        this.selectedPaymentTypePermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "PAYMENT_TYPE"));
        this.selectedProductsPermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "PRODUCT"));
        this.selectedCustomersPermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "CUSTOMER"));
        this.selectedBillersPermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "BILLER"));
        this.selectedInvoicePermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "INVOICE"));
        this.selectedPaymentPermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "PAYMENT"));
        this.selectedRecurrencePermission.addAll(CollectionUtility.filter(this.role.permissionAsStringList(), "RECURRENCE"));

    }

    public String onUpdate() {
        logger.debug("Role Type:" + role.getRoleType());
        this.role.setCompanyCode(securityUtility.getPrinciple().getCompanyCode());
        this.role.getAuditInfo().setModifiedBy(securityUtility.getPrinciple().getUsername());
        this.role.getAuditInfo().setModifiedOn(new Date());
        if (securityUtility.getPrinciple().getRoleType().equals(Role.RoleType.CLIENT)) {
            this.role.setRoleType(Role.RoleType.CLIENT_USER);
        }
        this.addPermission();
        this.roleManager.update(role);
        Messages.addInfo(null, "Role Edit Sucessfully " + " done");
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
        role.addPermission(selectedPaymentPermission);
        role.addPermission(selectedInvoicePermission);
        role.addPermission(selectedRecurrencePermission);

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getSelectedRoleId() {
        return selectedRoleId;
    }

    public void setSelectedRoleId(long selectedRoleId) {
        this.selectedRoleId = selectedRoleId;
    }

    public List<String> getSelectedUserPermission() {
        return selectedUserPermission;
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
