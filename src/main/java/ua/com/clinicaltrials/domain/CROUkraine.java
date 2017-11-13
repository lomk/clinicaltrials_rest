package ua.com.clinicaltrials.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 05-Oct-16.
 */
@Entity
@Table(name = "cro_ukraine")
@Getter
@Setter
public class CROUkraine implements Serializable {
    private static final long serialVersionUID = -1000119454547252957L;

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
    @Column(name="adress_ua")
    private String adressUa;
    @Column(name="adress_ru")
    private String adressRu;
    @Column(name="adress_en")
    private String adressEn;
    @Column(name="phone")
    private String phone;
    @Column(name="fax")
    private String fax;
    @Column(name="email")
    private String email;
    @Column(name="url")
    private String url;
    @Column(name="name_of_headquarter_ua")
    private String nameOfHeadquarterUa;
    @Column(name="name_of_headquarter_ru")
    private String nameOfHeadquarterRu;
    @Column(name="name_of_headquarter_en")
    private String nameOfHeadquarterEn;

    @Column(name="address_of_headquarter_ua")
    private String addressOfHeadquarterUa;
    @Column(name="address_of_headquarter_ru")
    private String addressOfHeadquarterRu;
    @Column(name="address_of_headquarter_en")
    private String addressOfHeadquarterEn;

    @Column(name="phone_of_headquarter")
    private String phoneOfHeadquarter;
    @Column(name="fax_of_headquarter")
    private String faxOfHeadquarter;
    @Column(name="email_of_heeadquarter")
    private String emailOfHeadquarter;
    @Column(name="url_of_headquarter")
    private String UrlOfHeadquarter;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "clinical_study_id")
    ClinicalStudy clinicalStudy;

}
