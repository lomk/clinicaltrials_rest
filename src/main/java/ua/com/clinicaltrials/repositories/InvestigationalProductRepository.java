package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.InvestigationalProduct;

/**
 * Created by mater on 23-Jan-17.
 */
public interface InvestigationalProductRepository extends JpaRepository<InvestigationalProduct, Integer> {
}
