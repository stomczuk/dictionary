package app.service;

import app.DTO.UserPrincipal;
import app.entity.Role;
import app.entity.User;
import app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    final static String ROLE_USER = "USER";
    private final UserRepository userRepository;

    private final EnglishWordService englishWordService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, EnglishWordService englishWordService) {
        this.userRepository = userRepository;
        this.englishWordService = englishWordService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            LOGGER.error("Cannot find user by username: " + username);
            throw new UsernameNotFoundException("Cannot find user by username: " + username);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Returning found user by username: " + username);
            return userPrincipal;
        }
    }

    @Override
    public void save(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Set<Role> setUserRole() {
        Set<Role> roles = new HashSet<>();
        roles.add(null);
        return roles;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);

    }
}
