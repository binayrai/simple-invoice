package com.itexpertnepal.simpleinvoice.api.common;

import com.itexpertnepal.simpleinvoice.domain.common.User;
import java.util.List;

/**
 *
 * @author binay
 */
public interface UserManager extends CrudService<User, Long> {

    public User findByUserName(String userName);

    public void changePassword(String userName, String password);

    public List<User> findAllClient();

    public List<User> findAllClientUsers();

    public void removeUser(User user);

}
