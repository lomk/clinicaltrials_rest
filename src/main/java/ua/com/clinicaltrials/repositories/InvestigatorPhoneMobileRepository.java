package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.MobilePhone;

/**
 * Created by mater on 23-Jan-17.
 */
public interface InvestigatorPhoneMobileRepository extends JpaRepository<MobilePhone, Integer> {
}
