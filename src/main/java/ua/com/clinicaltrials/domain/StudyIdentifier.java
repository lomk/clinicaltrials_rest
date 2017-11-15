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
@Table(name = "study_identifiers")
@Getter
@Setter
public class StudyIdentifier implements Serializable {
    private static final long serialVersionUID = -1030119478157252957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "sponsors_protocol_number")
    private long sponsorsProtocolNumber;
    @Column(name = "clinical_trialscomua_identifier")
    private long clinicalTrialsComUaIdentifier;
    @Column(name = "clinical_trialsgov_identifier")
    private long clinicalTrialsGovIdentifier;
    @Column(name = "eudra_ct_number")
    private long eudraCTNumber;
    @ManyToOne
    @JoinColumn(name = "moh_of_ukraine_order_id")
    private MohOfUkraineOrder MohOfUkraineOrder;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country sponsorCountry;
    @ManyToOne
    @JoinColumn(name = "sponsor_id")
    private Sponsor sponsor;

    @Column(name = "estimated_enrollment_all_clinicaltrials_gov")
    private long estimatedEnrollmentAllClinicaltrialsGov;

    @Column(name = "estimated_enrollment_all_moh_of_ukraine")
    private long estimatedEnrollmentAllMoHOfUkraine;

    @Column(name = "estimated_enrollment_all_company")
    private long estimatedEnrollmentAllCompany;

    @Column(name = "start_date_enroling_in_ukraine")
    private long startDateEnrolingInUkraine;

    @ManyToOne
    @JoinColumn(name = "study_tatus_id")
    private Country studyStatus;

    @Column(name = "completition_date")
    private String comletitionDate;

    @Column(name = "final_enrollment_in_ukraine_moh")
    private long finalEnrollmentInUkraineMoH;

    @Column(name = "final_enrollment_in_ukraine_company")
    private long finalEnrollmentInUkraineCompany;

    @Column(name = "last_update_date")
    private String lastUpdateDate;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "clinical_study_id")
    ClinicalStudy clinicalStudy;
}
