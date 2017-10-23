package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Sponsor;

/**
 * Created by mater on 24-Jan-17.
 */
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {
}
