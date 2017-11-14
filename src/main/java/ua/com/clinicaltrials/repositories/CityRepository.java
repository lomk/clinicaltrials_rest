package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.City;
import ua.com.clinicaltrials.domain.Region;

import java.util.List;

/**
 * Created by mater on 23-Jan-17.
 */
public interface CityRepository extends JpaRepository<City, Integer> {
    City findByNameEn(String name);
    City findByNameRu(String name);
    City findByNameUa(String name);
    City findByPhoneCode(Integer code);
    List<City> findAllByRegion(Region region);
}
