package ua.com.clinicaltrials.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.clinicaltrials.domain.AboutTrialsArticle;


import java.util.Collection;
import java.util.List;

public interface AboutTrialsArticleRepository extends JpaRepository<AboutTrialsArticle, Integer> {

}
