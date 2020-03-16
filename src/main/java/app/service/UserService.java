package app.service;

import app.entity.Role;
import app.entity.User;

import java.util.Set;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    Set<Role> setUserRole();

    User findByEmail(String email);




}
