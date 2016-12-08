package kr.pe.jady.wordict.word.model;

import javax.persistence.*;

/**
 * Word Entity
 * @author jhlee7854
 */
@Entity
public class Word {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    public Word() {}

    public Word(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
