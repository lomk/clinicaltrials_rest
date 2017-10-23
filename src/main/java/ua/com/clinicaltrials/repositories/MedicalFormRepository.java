package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.MedicalForm;

/**
 * Created by mater on 24-Jan-17.
 */
public interface MedicalFormRepository extends JpaRepository<MedicalForm, Integer> {
}
