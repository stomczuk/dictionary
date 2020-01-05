package app.controller;

import app.entity.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register (@ModelAttribute User user) {
        userRepository.save(user);
        return "Success";
    }
}
