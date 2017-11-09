package ua.com.clinicaltrials.domain;

import javax.persistence.*;
import java.io.Serializable;

public class ClinicalStudy implements Serializable {
    private static final long serialVersionUID = -1000119478140972957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "study_idetifier_id")
    StudyIdentifier studyIdentifier;

    @OneToOne
    @JoinColumn(name = "study_general_information_id")
    StudyGeneralInformation studyGeneralInformation;

    @OneToOne
    @JoinColumn(name = "eligibility_id")
    Eligibility eligibility;

    @OneToOne
    @JoinColumn(name = "trial_site_id")
    TrialSite trialSite;

    @OneToOne
    @JoinColumn(name = "cro_ukraine_id")
    CROUkraine croUkraine;

    @OneToOne
    @JoinColumn(name = "sponsor_id")
    Sponsor sponsor;
}
