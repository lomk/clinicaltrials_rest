package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.PhoneMobileCode;

/**
 * Created by mater on 24-Jan-17.
 */
public interface PhoneMobileCodeRepository extends JpaRepository<PhoneMobileCode, Integer> {
}
