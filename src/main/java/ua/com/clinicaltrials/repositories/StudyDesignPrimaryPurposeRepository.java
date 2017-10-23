package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.StudyDesignPrimaryPurpose;

/**
 * Created by mater on 24-Jan-17.
 */
public interface StudyDesignPrimaryPurposeRepository extends JpaRepository<StudyDesignPrimaryPurpose, Integer> {
}
