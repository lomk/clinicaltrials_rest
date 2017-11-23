package ua.com.clinicaltrials.domain;




import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu implements Serializable {
    private static final long serialVersionUID = -1200119078967282157L;

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

    @Column(name = "description_ua", nullable = false, length = 400)
    private String descUa;

    @Column(name = "description_ru", nullable = false, length = 400)
    private String descRu;

    @Column(name = "description_en", nullable = false, length = 400)
    private String descEn;

    @Column(name = "seo_description_ua", nullable = false, length = 400)
    private String seoDescUa;

    @Column(name = "seo_description_ru", nullable = false, length = 400)
    private String seoDescRu;

    @Column(name = "seo_description_en", nullable = false, length = 400)
    private String seoDescEn;


    @OneToMany(mappedBy = "menu", targetEntity = Section.class)
    @OrderBy("section_id")
    public List<Section> sections;

}
