package com.itexpertnepal.simpleinvoice.domain;

import com.itexpertnepal.simpleinvoice.domain.common.AbstractEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author binay
 */
@Entity
@Table(name = "customer_accounts")
public class CustomerAccount extends AbstractEntity implements Serializable {

    private String customerCode;
    private double balanceForwared;
    private double currentAmount;
    private double totalAmount;
    private double paidAmount;
    private boolean isBalanceForward = false;
    private double totalTaxAmt;

    public CustomerAccount() {
    }

    public CustomerAccount(String customerCode, double balanceForwared) {
        this();
        this.customerCode = customerCode;
        this.balanceForwared = balanceForwared;
    }

    @Column(name = "customer_code")
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Column(name = "balance_forward")
    public double getBalanceForwared() {
        return balanceForwared;
    }

    public void setBalanceForwared(double balanceForwared) {
        this.balanceForwared = balanceForwared;
    }

    @Column(name = "current_amount")
    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Column(name = "total_amount")
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(name = "paid_amount")
    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Column(name = "is_balance_forward")
    public boolean isIsBalanceForward() {
        return isBalanceForward;
    }

    public void setIsBalanceForward(boolean isBalanceForward) {
        this.isBalanceForward = isBalanceForward;
    }

    @Column(name = "total_tax_amt")
    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

}