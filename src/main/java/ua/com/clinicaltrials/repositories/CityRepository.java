package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.City;

/**
 * Created by mater on 23-Jan-17.
 */
public interface CityRepository extends JpaRepository<City, Integer> {
}
