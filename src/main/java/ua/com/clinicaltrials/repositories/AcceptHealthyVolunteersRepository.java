package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.AcceptHealthyVolunteers;

/**
 * Created by mater on 23-Jan-17.
 */
public interface AcceptHealthyVolunteersRepository extends JpaRepository<AcceptHealthyVolunteers, Integer> {

    AcceptHealthyVolunteers findByValueEn(String value);
    AcceptHealthyVolunteers findByValueRu(String value);
    AcceptHealthyVolunteers findByValueUa(String value);
}
