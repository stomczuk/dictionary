package app.repository;

import app.entity.EnglishWord;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EnglishWordRepository extends JpaRepository <EnglishWord, Long> {

    List<EnglishWord> findAllByUsers(User user);
}
