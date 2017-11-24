package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Menu;
import ua.com.clinicaltrials.domain.Section;

import java.util.List;

public interface SectionRepositoty extends JpaRepository<Section, Integer> {
    List<Section> findByMenu(Integer menuId);
}
