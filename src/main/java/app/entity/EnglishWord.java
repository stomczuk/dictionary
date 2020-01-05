package app.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlInlineBinaryData;

@Entity
@Table(name = "eng_words")
public class EnglishWord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Size(min = 2, message = "Required minimum 2 characters in English Word")
    private String word;

    private String pronunciation;

//    @Size(min = 2, message = "Required minimum 2 characters in Translation")
//    @NotNull
    private String translation;

    public EnglishWord() {
    }

    public EnglishWord(String word, String pronunciation, String translation) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.translation = translation;
    }

    public Long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
