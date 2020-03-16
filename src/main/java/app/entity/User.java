package app.entity;


import app.validation.Password;
import app.validation.PasswordMatches;
import app.validation.UniqueEmail;
import app.validation.UniqueUsername;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@PasswordMatches(message = "Password doesn't match")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Username cannot be blank")
    @UniqueUsername
    private String username;

    @Email
    @UniqueEmail
    private String email;

    @Password
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "users_english_words",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "eng_words_id"))
    private Set<EnglishWord> englishWords;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<EnglishWord> getEnglishWords() {
        return englishWords;
    }

    public void setEnglishWords(Set<EnglishWord> englishWords) {
        this.englishWords = englishWords;
    }

    public void addGroup(EnglishWord englishWord) {
        this.englishWords.add(englishWord);
        englishWord.getUsers().add(this);
    }

    public void removeGroup(EnglishWord englishWord) {
        this.englishWords.remove(englishWord);
        englishWord.getUsers().remove(this);
        List test = new ArrayList<>();

    }
}
