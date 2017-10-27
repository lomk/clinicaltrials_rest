package ua.com.clinicaltrials.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ua.com.clinicaltrials.domain.*;
import ua.com.clinicaltrials.repositories.ArticleRepository;
import ua.com.clinicaltrials.repositories.CategoryRepository;
import ua.com.clinicaltrials.repositories.RoleRepository;
import ua.com.clinicaltrials.repositories.TagRepository;
import ua.com.clinicaltrials.services.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class EntityLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role role = new Role();
        role.setName("ADMIN");
        roleRepository.save(role);

        Role role2 = new Role();
        role2.setName("TESTER");
        roleRepository.save(role2);


        User user = new User();
        user.setUsername("Admin");
        user.setPassword("admin123456");
        user.setRole(roleRepository.findByName("ADMIN"));
        userService.save(user);

        User user2 = new User();
        user2.setUsername("tester1");
        user2.setPassword("admin123456");
        user2.setRole(roleRepository.findByName("TESTER"));
        userService.save(user2);

        Category category = new Category();
        category.setUrl("testurl");
        category.setNameRu("имя");
        category.setNameEn("name");
        category.setNameUa("імя");
        categoryRepository.save(category);

        Tag tag = new Tag();
        tag.setName("test_tag");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        tagRepository.save(tag);

        Article article = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setRating((long) 0);
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(category);
        article.setBodyEn("text ");
        article.setBodyRu("текст РУ");
        article.setBodyUa("текст ЮА");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
//        article.setTags(tags);
        article.setTitleEn("TITLE");
        article.setTitleRu("Заголовок РУ");
        article.setTitleUa("Заголовок ЮА");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");

        articleRepository.save(article);
    }
}
