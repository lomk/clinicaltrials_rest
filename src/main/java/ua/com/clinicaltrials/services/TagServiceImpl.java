package ua.com.clinicaltrials.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.clinicaltrials.domain.Tag;
import ua.com.clinicaltrials.repositories.TagRepository;

/**
 * Created by Igor on 20-Jul-16.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Override
    public Iterable<Tag> listAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagRepository.findOne(id);
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Integer id) {
        tagRepository.delete(id);
    }

    @Override
    public Tag findByUrl(String url) {
        return tagRepository.findByUrl(url);
    }
}
