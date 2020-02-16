package app.service;

import app.entity.EnglishWord;
import app.entity.User;

import java.util.List;

public interface EnglishWordService {

    void save(EnglishWord englishWord);

//    List<EnglishWord> findAllByUsers (User user);

    EnglishWord getEnglishWordById(Long id);

    public void updateEnglishWordById (Long id, EnglishWord englishWord);
}
