package app.service;

import app.entity.EnglishWord;
import app.entity.User;
import app.repository.EnglishWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EnglishWordServiceImpl implements EnglishWordService {

    @Autowired
    EnglishWordRepository englishWordRepository;
    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;
    @Autowired
    EnglishWordService englishWordService;

    @Override
    public void save(EnglishWord englishWord) {
        englishWordRepository.save(englishWord);
    }

    @Override
    public EnglishWord getEnglishWordById(Long id) {
        return englishWordRepository.getOne(id);
    }

    @Override
    public void updateEnglishWordById(Long id, EnglishWord englishWord) {
        EnglishWord newEnglishWord = englishWordService.getEnglishWordById(id);
        Assert.notNull(newEnglishWord, "You can not update this word");
        newEnglishWord.setWord(englishWord.getWord());
        newEnglishWord.setPronunciation(englishWord.getPronunciation());
        newEnglishWord.setTranslation(englishWord.getTranslation());

        englishWordService.save(newEnglishWord);
    }

    @Override
    public List<EnglishWord>findAllEnglishWordsByUser(User user) {
        return englishWordRepository.findAllByUsers(user);
    }
}
