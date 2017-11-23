package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>{
}
