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
@Table(name = "medical_institution")
@Getter
@Setter
public class MedicalInstitution implements Serializable {
    private static final long serialVersionUID = -1000114478147352057L;

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

    /*@ManyToOne
    @JoinColumn(name = "region")
    private Region region;*/

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    @Column(name = "website")
    private String website;

    @Column(name = "phone")
    private String phone;

    @Column(name = "information_desk_phone")
    private String informationDeskPhone;

    @Column(name = "registration_desk_phone")
    private String registrationDeskPhone;

    @Column(name = "reception_phone")
    private String receptionPhone;

    @Column(name = "fax")
    private String fax;

//    @OneToMany(mappedBy = "medicalInstitution", targetEntity = MedicalInstitutionDepartment.class)
//    private List<InvestigatorFax> faxList;
}
