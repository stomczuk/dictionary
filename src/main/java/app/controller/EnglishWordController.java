package app.controller;


import app.entity.EnglishWord;
import app.entity.Translation;
import app.repository.EnglishWordRepository;
import app.repository.TranslationRepository;
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
    EnglishWordRepository englishWordRepository;
    @Autowired
    TranslationRepository translationRepository;

    Logger logger = Logger.getGlobal();

    @GetMapping("/add")
    public String add(Model model) {
        EnglishWord englishWord = new EnglishWord();
        model.addAttribute("engWord", englishWord);
        return "addEnglishWord";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("engWord") EnglishWord englishWord, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addEnglishWord";
        } else {
            englishWordRepository.save(englishWord);
        }

        return "redirect:/englishWord/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (id != null) {
            try {
                englishWordRepository.deleteById(id);
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
               EnglishWord englishWord = englishWordRepository.getOne(id);
                model.addAttribute("englishWord", englishWord);
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return "editEnglishWord";
    }

    @PostMapping("/edit")
    @ResponseBody
    public String edit(@ModelAttribute("englishWord") EnglishWord englishWord) {

            englishWordRepository.save(englishWord);

        return "succcess";
    }

    @ModelAttribute("listOfEnglishWords")
    public List<EnglishWord> listOfWords() {
        List<EnglishWord> words = englishWordRepository.findAll();
        return words;
    }
}
