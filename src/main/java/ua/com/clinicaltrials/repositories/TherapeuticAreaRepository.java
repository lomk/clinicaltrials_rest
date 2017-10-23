package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.TherapeuticArea;

/**
 * Created by mater on 24-Jan-17.
 */
public interface TherapeuticAreaRepository extends JpaRepository<TherapeuticArea, Integer> {
}
