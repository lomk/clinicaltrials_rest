package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mater on 21-Jan-17.
 */
@Entity
@Table(name = "medical_institution_of_higher_education")
@Getter
@Setter
public class MedicalInstitutionOfHigherEducation implements Serializable {
    private static final long serialVersionUID = -1008114478107252957L;

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

    @Column(name = "website")
    private String website;
}
