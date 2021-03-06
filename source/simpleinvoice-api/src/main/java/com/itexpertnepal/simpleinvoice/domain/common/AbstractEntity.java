package com.itexpertnepal.simpleinvoice.domain.common;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author binay
 */
@MappedSuperclass
public class AbstractEntity implements Auditable, Serializable {

    private Long id;
    private boolean active;
    private AuditInfo auditInfo;

    public AbstractEntity() {
        this.auditInfo = new AuditInfo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "is_active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Embedded
    public AuditInfo getAuditInfo() {
        return this.auditInfo;
    }

    public void setAuditInfo(AuditInfo ai) {
        this.auditInfo = ai;
    }

//    @PrePersist
//    @PreUpdate
//    public void updateActive() {
//        this.active = true;
//        if (this.active == false) {
//            this.active = false;
//        }
//
//    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
