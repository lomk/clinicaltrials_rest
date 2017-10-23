package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mater on 21-Jan-17.
 */
@Entity
@Table(name = "medical_institution_of_higher_education_department")
@Getter
@Setter
public class MedicalInstitutionOfHigherEducationDepartment implements Serializable {
    private static final long serialVersionUID = -1000119478747252557L;

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

    @ManyToOne
    @JoinColumn(name = "medical_institution_of_higher_education_id")
    private MedicalInstitutionOfHigherEducation medicalInstitutionOfHigherEducation;
}
