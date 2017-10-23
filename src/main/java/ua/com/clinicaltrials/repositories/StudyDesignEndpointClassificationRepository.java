package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.StudyDesignEndpointClassification;

/**
 * Created by mater on 24-Jan-17.
 */
public interface StudyDesignEndpointClassificationRepository extends JpaRepository<StudyDesignEndpointClassification, Integer> {
}
