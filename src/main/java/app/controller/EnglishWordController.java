package app.controller;


import app.entity.EnglishWord;
import app.entity.User;
import app.service.EnglishWordService;
import app.service.SecurityService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/englishWord")
public class EnglishWordController {

    @Autowired
    EnglishWordService englishWordService;
    @Autowired
    SecurityService securityService;
    @Autowired
    UserService userService;

    Logger logger = Logger.getGlobal();

    @GetMapping("/add")
    public String add(Model model) {

        EnglishWord englishWord = new EnglishWord();
        model.addAttribute("engWord", englishWord);
        model.addAttribute("currentUsername", securityService.findLoggedInUsername());
        return "addEnglishWord";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("engWord") EnglishWord englishWord, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addEnglishWord";
        } else {
            User user = userService.findByUsername(securityService.findLoggedInUsername());
            user.addGroup(englishWord);
            englishWordService.save(englishWord);
        }

        return "redirect:/englishWord/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (id != null) {
            try {
                User user = userService.findByUsername(securityService.findLoggedInUsername());
                EnglishWord englishWord = englishWordService.getEnglishWordById(id);
                user.removeGroup(englishWord);
                englishWordService.save(englishWord);
            } catch (Exception e) {
               logger.info(e.getMessage());
            }
        }
        return "redirect:/englishWord/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        if (id != null) {
            try {
               EnglishWord englishWord = englishWordService.getEnglishWordById(id);
                model.addAttribute("englishWord", englishWord);
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return "editEnglishWord";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute("englishWord") EnglishWord englishWord, @PathVariable Long id) {
            englishWordService.updateEnglishWordById(id, englishWord);
            return "redirect:/englishWord/add";
    }

    @ModelAttribute("listOfEnglishWords")
    public List<EnglishWord> listOfWords() {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        return englishWordService.findAllEnglishWordsByUser(user);

    }
}
