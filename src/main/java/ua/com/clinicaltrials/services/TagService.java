package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.Tag;

/**
 * Created by Igor on 20-Jul-16.
 */
public interface TagService {

    Iterable<Tag> listAllTags();

    Tag getTagById(Integer id);

    void saveTag(Tag tag);

    void deleteTag(Integer id);

    Tag findByUrl(String url);
}
