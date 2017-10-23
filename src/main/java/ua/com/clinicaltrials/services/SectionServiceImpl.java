package ua.com.clinicaltrials.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.clinicaltrials.domain.Menu;
import ua.com.clinicaltrials.domain.Section;
import ua.com.clinicaltrials.repositories.SectionRepository;

import java.util.List;

/**
 * Created by Igor on 29-Sep-16.
 */
@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Iterable<Section> listAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSectionById(Integer id) {
        return sectionRepository.findOne(id);
    }

    @Override
    public void saveSection(Section section) {
        sectionRepository.save(section);
    }

    @Override
    public void deleteSection(Integer id) {
        deleteSection(id);
    }

    @Override
    public Section findByUrl(String url) {
        return sectionRepository.findByUrl(url);
    }

    @Override
    public List<Section> findByMenu(Menu menu) {
        return sectionRepository.findByMenuOrderByIdAsc(menu);
    }

}
