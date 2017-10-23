package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Menu;
import ua.com.clinicaltrials.domain.Section;

import java.util.List;

/**
 * Created by Igor on 29-Sep-16.
 */
public interface SectionRepository extends JpaRepository<Section, Integer> {
    Section findByUrl(String url);
    Section findByNameUa(String name);
    Section findByNameRu(String name);
    Section findByNameEn(String name);
    List<Section> findByMenuOrderByIdAsc(Menu menu);
}
