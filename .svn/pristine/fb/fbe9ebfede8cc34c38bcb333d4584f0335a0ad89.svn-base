package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.common.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author binay
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByRoleName(String roleName);

    public List<Role> findByRoleType(Role.RoleType roleType);

    @Query("select r from Role r where r.roleType = ?1 ")
    public List<Role> findByClientUserRole(Role.RoleType roleType);

}
