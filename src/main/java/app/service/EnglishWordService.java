package app.service;

import app.entity.EnglishWord;
import app.entity.User;

import java.util.List;

public interface EnglishWordService {

    void save(EnglishWord englishWord);

    EnglishWord getEnglishWordById(Long id);

    void updateEnglishWordById(Long id, EnglishWord englishWord);

    List<EnglishWord>findAllEnglishWordsByUser(User user);

}
