package app.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
//@PasswordMatches(message = "Password doesn't match")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    //    @NotBlank(message = "Name cannot be blank")
    private String name;

    //    @NotBlank(message = "Username cannot be blank")
//    @UniqueUsername
    private String username;

    //    @Email
//    @UniqueEmail
    private String email;

    //    @Password
    private String password;

    @Transient
    private String passwordConfirm;

    private Date lastLoginDate;

    private Date lastLoginDateDisplay;

    private Date joinDate;

    private String[] roles;

    private String[] authorities;
    private boolean isActive;
    private boolean isNonLocked;


    public User() {
    }

    public User(Long id, String userId, String name, String username, String email, String password, String passwordConfirm, Date lastLoginDate, Date lastLoginDateDisplay, Date joinDate, String[] roles, String[] authorities, boolean isActive, boolean isNonLocked) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginDateDisplay = lastLoginDateDisplay;
        this.joinDate = joinDate;
        this.roles = roles;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNonLocked = isNonLocked;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNonLocked() {
        return isNonLocked;
    }

    public void setNonLocked(boolean nonLocked) {
        isNonLocked = nonLocked;
    }

    /*do zrobienia jak bede robil userow*/

//    public Set<EnglishWord> getEnglishWords() {
//        return englishWords;
//    }
//
//    public void setEnglishWords(Set<EnglishWord> englishWords) {
//        this.englishWords = englishWords;
//    }

//    public void addGroup(EnglishWord englishWord) {
//        this.englishWords.add(englishWord);
//        englishWord.getUsers().add(this);
//    }
//
//    public void removeGroup(EnglishWord englishWord) {
//        this.englishWords.remove(englishWord);
//        englishWord.getUsers().remove(this);
//        List test = new ArrayList<>();
//
//    }
}
