package app.controller;

import app.entity.Role;
import app.entity.User;
import app.repository.UserRepository;
import app.service.SecurityService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;


    @Autowired
    SecurityService securityService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String register (@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userService.save(userForm);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }

    @PostMapping("/loginSuccess")
    public String loginSuccess() {
        return "redirect:/englishWord/add";
    }

}
