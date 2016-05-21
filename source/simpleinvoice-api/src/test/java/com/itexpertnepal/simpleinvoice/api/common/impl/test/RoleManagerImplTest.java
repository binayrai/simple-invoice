package com.itexpertnepal.simpleinvoice.api.common.impl.test;

import com.itexpertnepal.simpleinvoice.api.common.RoleManager;
import com.itexpertnepal.simpleinvoice.domain.common.Permission;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.test.BaseTest;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

/**
 *
 * @author binay
 */
public class RoleManagerImplTest extends BaseTest {

    @Autowired
    private RoleManager roleManager;
    private static final Logger LOG = Logger.getLogger(RoleManagerImplTest.class.getName());

    @Test
    public void addNewTest() {
        Role role = new Role(Role.CLIENT_USER_ROLE_NAME, Role.RoleType.CLIENT_USER);
        role.addPermission(Permission.PROFILE_UPDATE);
        role.addPermission(Permission.USER_ADD);
        role.addPermission(Permission.ROLE_DELETE);
        role = this.roleManager.addNew(role);
        assertTrue(role.getId() > 0);

//        Role r = new Role(Role.ADMIN_ROLE_NAME, Role.RoleType.ADMIN);
//        r.addPermission(Permission.PROFILE_UPDATE);
//        r = this.roleManager.addNew(r);
//        assertTrue(r.getId() > 0);
    }

    @Test(dependsOnMethods = {"addNewTest"})
    public void findByRoleNameTest() {
        Role r = this.roleManager.findByRoleName(Role.CLIENT_USER_ROLE_NAME);
        assertNotNull(r);
    }

}
