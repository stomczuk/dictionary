package app.service;

import app.entity.User;
import app.exception.domain.EmailExistException;
import app.exception.domain.UsernameExistException;

import java.util.List;
import java.util.Set;

public interface UserService {

    void save(User user);
    User register(String firstName, String lastName,String username, String email) throws UsernameExistException, EmailExistException;
    List<User>getUsers();
    User findByUsername(String username);
    User findByEmail(String email);


}
