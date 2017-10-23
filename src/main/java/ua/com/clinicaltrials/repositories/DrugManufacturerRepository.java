package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.DrugManufacturer;

/**
 * Created by mater on 23-Jan-17.
 */
public interface DrugManufacturerRepository extends JpaRepository<DrugManufacturer, Integer> {
}
