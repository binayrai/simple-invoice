package com.itexpertnepal.simpleinvoice.api.common.impl.test;

import com.itexpertnepal.simpleinvoice.api.common.RoleManager;
import com.itexpertnepal.simpleinvoice.api.common.UserManager;
import com.itexpertnepal.simpleinvoice.domain.common.Role;
import com.itexpertnepal.simpleinvoice.domain.common.User;
import com.itexpertnepal.simpleinvoice.test.BaseTest;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author binay
 */
public class UserManagerImplTest extends BaseTest {

    @Autowired
    private UserManager userManager;
    @Autowired
    private RoleManager roleManager;

    @Test(dependsOnMethods = {"com.itexpertnepal.simpleinvoice.api.common.impl.test.RoleManagerImplTest.addNewTest"})
    public void addNewTest() {

        Set<Role> roles = new HashSet<Role>();
        roles.add(this.roleManager.findByRoleName(Role.DEFAULT_ROLE_NAME));
        roles.add(this.roleManager.findByRoleName(Role.ADMIN_ROLE_NAME));

        User user = null;
        user = new User("binay@gmail.com", "123", roles);

        user = this.userManager.addNew(user);
//        logger.debug("user name:" + user.getUserName());
        assertTrue(user.getUserName() != null);

    }

}
