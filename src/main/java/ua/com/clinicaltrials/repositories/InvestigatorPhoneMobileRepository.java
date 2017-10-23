package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.InvestigatorPhoneMobile;

/**
 * Created by mater on 23-Jan-17.
 */
public interface InvestigatorPhoneMobileRepository extends JpaRepository<InvestigatorPhoneMobile, Integer> {
}
