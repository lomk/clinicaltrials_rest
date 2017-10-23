package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.AcademicDegree;

/**
 * Created by mater on 23-Jan-17.
 */
public interface AcademicDegreeRepository extends JpaRepository<AcademicDegree, Integer> {
}
