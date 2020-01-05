package app.repository;

import app.entity.EnglishWord;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EnglishWordRepository extends JpaRepository <EnglishWord, Long> {
}
