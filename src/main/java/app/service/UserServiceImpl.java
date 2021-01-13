package app.service;

import app.DTO.UserPrincipal;
import app.entity.User;
import app.enumeration.Role;
import app.exception.domain.EmailExistException;
import app.exception.domain.UsernameExistException;
import app.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static app.model.UserImplConstant.*;


@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    final static String ROLE_USER = "USER";
    private final UserRepository userRepository;
    private final EnglishWordService englishWordService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, EnglishWordService englishWordService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.englishWordService = englishWordService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
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
    public User register(String firstName, String lastName, String username, String email) throws UsernameExistException, EmailExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
        User user = new User();
        String password = generatePassword();
        String encodedPassword = encodePassword(password);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setUsername(username);
        user.setEmail(email);
        user.setJoinDate(new Date());
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setNonLocked(true);
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        userRepository.save(user);
        LOGGER.info("New user password: " + password);
        return user;
    }

    private String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws EmailExistException, UsernameExistException {
        User userByNewUsername = findByUsername(newUsername);
        User userByNewEmail = findByEmail(newEmail);
         if (StringUtils.isNotBlank(currentUsername)) {
             User currentUser = findByUsername(currentUsername);
             if (currentUser == null) {
                 throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
             }
             if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                 throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
             }
             if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                 throw new EmailExistException(EMAIL_ALREADY_EXISTS);
             }
             return currentUser;
         } else {
             if (userByNewUsername != null) {
                 throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
             }
             if (userByNewEmail != null) {
                 throw new EmailExistException(EMAIL_ALREADY_EXISTS);
             }
             return null;
         }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);

    }
}
