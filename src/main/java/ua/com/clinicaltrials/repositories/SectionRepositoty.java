package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Section;

public interface SectionRepositoty extends JpaRepository<Section, Integer> {
}
