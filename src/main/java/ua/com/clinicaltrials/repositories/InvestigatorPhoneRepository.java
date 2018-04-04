package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Phone;

/**
 * Created by mater on 23-Jan-17.
 */
public interface InvestigatorPhoneRepository extends JpaRepository<Phone, Integer> {
}
