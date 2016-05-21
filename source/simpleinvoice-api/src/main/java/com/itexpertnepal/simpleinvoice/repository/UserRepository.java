package com.itexpertnepal.simpleinvoice.repository;

import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.domain.common.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author binay
 */
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userName = ?1 and u.active is true")
    public User findByUserName(String userName);

    @Query("select u from User u where u.roleType = ?1 and u.active is true")
    public List<User> findByRoleType(Role.RoleType roleType);

    @Query("select u from User u where u.roleType = ?1 and u.active is true")
    public List<User> findByClientUsers(Role.RoleType roleType);

}
