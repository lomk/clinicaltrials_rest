package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Dosage;

/**
 * Created by mater on 23-Jan-17.
 */
public interface DosageRepository extends JpaRepository<Dosage, Integer> {
}
