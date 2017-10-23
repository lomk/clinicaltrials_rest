package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Menu;

import java.util.List;

/**
 * Created by Igor on 29-Sep-16.
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findAllByOrderByIdAsc();
    Menu findByNameEn(String name);
    Menu findByNameRu(String name);
    Menu findByNameUa(String name);
}
