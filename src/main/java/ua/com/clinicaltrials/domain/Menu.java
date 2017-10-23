package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Igor on 29-Sep-16.
 */

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu implements Serializable {
    private static final long serialVersionUID = -1200119078967282157L;

    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_ua")
    private String nameUa;
    @Column(name = "name_ru")
    private String nameRu;
    @Column(name = "name_en")
    private String nameEn;


    @OneToMany(mappedBy = "menu", targetEntity = Section.class)
    @OrderBy("section_id")
    public List<Section> sections;


}
