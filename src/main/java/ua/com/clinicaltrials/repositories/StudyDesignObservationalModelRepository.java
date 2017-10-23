package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.StudyDesignObservationalModel;

/**
 * Created by mater on 24-Jan-17.
 */
public interface StudyDesignObservationalModelRepository extends JpaRepository<StudyDesignObservationalModel, Integer> {
}
