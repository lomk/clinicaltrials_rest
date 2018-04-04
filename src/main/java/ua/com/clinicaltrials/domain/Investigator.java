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

    @ManyToMany
    @JoinTable(name = "investigator_phone",
            joinColumns = {@JoinColumn(name = "investigator_id")},
            inverseJoinColumns = {@JoinColumn(name = "phone_id")}
    )
    private List<Phone> phones;


    @ManyToMany
    @JoinTable(name = "investigator_mobile_phone",
            joinColumns = {@JoinColumn(name = "investigator_id")},
            inverseJoinColumns = {@JoinColumn(name = "mobile_phone_id")}
    )
    private List<MobilePhone> mobilePhones;

    @ManyToMany
    @JoinTable(name = "investigator_fax",
            joinColumns = {@JoinColumn(name = "investigator_id")},
            inverseJoinColumns = {@JoinColumn(name = "fax_id")}
    )
    private List<Fax> faxes;
}
