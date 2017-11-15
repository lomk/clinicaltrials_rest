package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "clinical_study")
@Getter
@Setter
public class ClinicalStudy implements Serializable {
    private static final long serialVersionUID = -1000119478140972957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_field")
    private Date creationDate;

    @OneToOne(mappedBy = "clinicalStudy", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    StudyIdentifier studyIdentifier;

    @OneToOne(mappedBy = "clinicalStudy", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    StudyGeneralInformation studyGeneralInformation;

    @OneToOne(mappedBy = "clinicalStudy", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    Eligibility eligibility;

    @OneToOne(mappedBy = "clinicalStudy", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    TrialSite trialSite;

    @OneToOne(mappedBy = "clinicalStudy", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    CroUkraine croUkraine;

    @OneToOne(mappedBy = "clinicalStudy", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    Sponsor sponsor;
}
