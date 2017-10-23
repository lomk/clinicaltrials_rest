package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Igor on 06-Jul-16.
 */
@Entity
@Table(name = "category")
@Getter
@Setter
public class Category implements Serializable {
    private static final long serialVersionUID = -1000119478140972957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_ua")
    private String nameUa;
    @Column(name = "name_ru")
    private String nameRu;
    @Column(name = "name_en")
    private String nameEn;
    @Column
    private String url;

    @OneToMany(mappedBy = "category", targetEntity = Article.class)
    @OrderBy("dateField ASC")
    private Set<Article> articles;


}
