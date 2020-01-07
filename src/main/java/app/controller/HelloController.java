package app.controller;



import app.entity.EnglishWord;
import app.entity.User;
import app.repository.EnglishWordRepository;
import app.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.logging.Logger;


@Controller
public class HelloController {

    @Autowired
    EnglishWordRepository englishWordRepository;
    @Autowired
    TranslationRepository translationRepository;

    Logger logger = Logger.getGlobal();

    @GetMapping("/")
    public String hello() {

        return "hello";
    }



}

