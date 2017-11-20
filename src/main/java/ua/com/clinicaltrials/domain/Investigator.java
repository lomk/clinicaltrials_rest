package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Igor on 11-Oct-16.
 */
@Entity
@Table(name = "investigator")
@Getter
@Setter
public class Investigator implements Serializable {
    private static final long serialVersionUID = -1000119478004252957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name_ua")
    private String firstNameUa;
    @Column(name = "first_name_ru")
    private String firstNameRu;
    @Column(name = "first_name_en")
    private String firstNameEn;

    @Column(name = "last_name_ua")
    private String lastNameUa;
    @Column(name = "last_name_ru")
    private String lastNameRu;
    @Column(name = "last_name_en")
    private String lastNameEn;

    @Column(name = "patronymic_name_ua")
    private String patronymicNameUa;
    @Column(name = "patronymic_name_ru")
    private String patronymicNameRu;
    @Column(name = "patronymic_name_en")
    private String patronymicNameEn;
    @Column(name = "birthDate")
    private String birthDate;

    @ManyToMany
    @JoinTable(name = "investigator_degree",
            joinColumns = {@JoinColumn(name = "investigator_id")},
            inverseJoinColumns = {@JoinColumn(name = "academic_degree_id")}
    )
    private List<AcademicDegree> academicDegrees;

    @OneToMany(mappedBy = "investigator", targetEntity = InvestigatorPhone.class)
    private List<InvestigatorPhone> phoneList;

    @OneToMany(mappedBy = "investigator", targetEntity = InvestigatorPhoneMobile.class)
    private List<InvestigatorPhoneMobile> phoneMobileList;

    @OneToMany(mappedBy = "investigator", targetEntity = InvestigatorFax.class)
    private List<InvestigatorFax> faxList;


}
