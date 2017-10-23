package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.MedicalCondition;

/**
 * Created by mater on 24-Jan-17.
 */
public interface MedicalConditionReopository extends JpaRepository<MedicalCondition, Integer> {
}
