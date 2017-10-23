package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 09-Oct-16.
 */
@Entity
@Table(name = "medical_condition")
@Getter
@Setter
public class MedicalCondition implements Serializable {
    private static final long serialVersionUID = -1000119409177252957L;

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

    @Column(name = "short_description_ua")
    private String shortDescriptionUa;
    @Column(name = "short_description_ru")
    private String shortDescriptionRu;
    @Column(name = "short_description_en")
    private String shortDescriptionEn;

    @Column(name = "full_description_ua")
    private String full_descriptionUa;
    @Column(name = "full_description_ru")
    private String full_descriptionRu;
    @Column(name = "full_description_en")
    private String full_descriptionEn;

    @ManyToOne
    @JoinColumn(name = "therapeutic_area_id")
    private TherapeuticArea therapeuticArea;
}
