package ua.com.clinicaltrials.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 11-Oct-16.
 */
@Entity
@Table(name = "trial_site")
@Getter
@Setter
public class    TrialSite implements Serializable {
    private static final long serialVersionUID = -1002119478147252957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;


    @ManyToOne
    @JoinColumn(name = "investigator_id")
    private Investigator investigator;

    @ManyToOne
    @JoinColumn(name = "medical_institution_department_id")
    private MedicalInstitutionDepartment medicalInstitutionDepartment;

    @ManyToOne
    @JoinColumn(name = "medical_institution_of_higher_education_department_id")
    private MedicalInstitutionOfHigherEducationDepartment medicalInstitutionOfHigherEducationDepartment;

    @ManyToOne
    @JoinColumn(name = "recruitment_status_clinicaltrialsgov_id")
    private RecruitmentStatus recruitmentStatusclinicaltrialsGov;

    @ManyToOne
    @JoinColumn(name = "recruitment_status_moh_id")
    private RecruitmentStatus recruitmentStatusMOH;

    @ManyToOne
    @JoinColumn(name = "recruitment_status_company_id")
    private RecruitmentStatus recruitmentStatusCompany;

    @Column(name = "final_enrollment")
    private Integer finalEnrollment;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "clinical_study_id")
    ClinicalStudy clinicalStudy;


}
