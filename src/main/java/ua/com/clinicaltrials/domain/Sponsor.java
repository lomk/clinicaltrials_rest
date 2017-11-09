package ua.com.clinicaltrials.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 06-Oct-16.
 */
@Entity
@Table(name = "sponsor")
@Getter
@Setter
public class Sponsor implements Serializable {
    private static final long serialVersionUID = -1000119278147259957L;

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
    @Column(name = "phone")
    private String phone;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;
    @Column(name = "url")
    private String url;
    @Column(name = "ukraine_department_name_ua")
    private String ukraineDepartmentNameUa;
    @Column(name = "ukraine_department_name_ru")
    private String ukraineDepartmentNameRu;
    @Column(name = "ukraine_department_name_en")
    private String ukraineDepartmentNameEn;
    @Column(name = "ukraine_department_adress_ua")
    private String ukraineDepartmentAdressUa;
    @Column(name = "ukraine_department_adress_ru")
    private String ukraineDepartmentAdressRu;
    @Column(name = "ukraine_department_adress_en")
    private String ukraineDepartmentAdressEn;
    @Column(name = "ukraine_department_phone")
    private String ukraineDepartmentPhone;
    @Column(name = "ukraine_department_fax")
    private String ukraineDepartmentFax;
    @Column(name = "ukraine_department_email")
    private String ukraineDepartmentEmail;
    @Column(name = "ukraine_department_url")
    private String ukraineDepartmentUrl;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @JsonIgnore
    @OneToOne(mappedBy = "sponsor")
    ClinicalStudy clinicalStudy;
}