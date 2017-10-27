package ua.com.clinicaltrials.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.clinicaltrials.domain.Tag;

/**
 * Created by Igor on 20-Jul-16.
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);
}
