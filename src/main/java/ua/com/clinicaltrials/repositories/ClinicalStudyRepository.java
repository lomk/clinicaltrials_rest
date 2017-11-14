package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.*;


import java.util.List;

public interface ClinicalStudyRepository extends JpaRepository<ClinicalStudy, Integer> {
    ClinicalStudy findByStudyIdentifier(StudyIdentifier studyIdentifier);
    ClinicalStudy findByStudyGeneralInformation(StudyGeneralInformation studyGeneralInformation);
    ClinicalStudy findByEligibility(Eligibility eligibility);
    ClinicalStudy findByTrialSite(TrialSite trialSite);
    ClinicalStudy findByCroUkraine(CroUkraine croUkraine);
    ClinicalStudy findBySponsor(Sponsor sponsor);

}
