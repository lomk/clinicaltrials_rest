package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Country;

/**
 * Created by mater on 23-Jan-17.
 */
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByNameEn(String name);
    Country findByNameRu(String name);
    Country findByNameUa(String name);
}
