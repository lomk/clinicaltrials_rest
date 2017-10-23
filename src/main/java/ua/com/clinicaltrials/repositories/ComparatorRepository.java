package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Comparator;

/**
 * Created by mater on 23-Jan-17.
 */
public interface ComparatorRepository extends JpaRepository<Comparator, Integer>{
}
