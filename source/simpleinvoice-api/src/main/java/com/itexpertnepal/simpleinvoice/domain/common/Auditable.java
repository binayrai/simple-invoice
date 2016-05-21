package com.itexpertnepal.simpleinvoice.domain.common;

/**
 *
 * @author binay
 */
public interface Auditable {

    public AuditInfo getAuditInfo();

    public void setAuditInfo(AuditInfo ai);
}
