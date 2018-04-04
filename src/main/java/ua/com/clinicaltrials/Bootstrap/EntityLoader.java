package ua.com.clinicaltrials.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ua.com.clinicaltrials.domain.*;
import ua.com.clinicaltrials.repositories.*;
import ua.com.clinicaltrials.services.UserService;


import java.util.Calendar;

@Component
public class EntityLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
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
        category.setUrl("news");
        category.setNameRu("Новости");
        category.setNameEn("News");
        category.setNameUa("Новини");
        categoryRepository.save(category);

        Category category1 = new Category();
        category.setUrl("aboutus");
        category.setNameRu("О нас");
        category.setNameEn("About us");
        category.setNameUa("Про нас");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category.setUrl("abouttrials");
        category.setNameRu("О клинических исследованиях");
        category.setNameEn("About clinical trials");
        category.setNameUa("Про клінічні дослідження");
        categoryRepository.save(category2);


        Article article = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("news"));
        article.setUrl("news1");
        article.setBodyEn("news 1");
        article.setBodyRu("Новость 1");
        article.setBodyUa("Новина 1");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("news 1");
        article.setTitleRu("Новость 1");
        article.setTitleUa("Новина 1");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article);

        Article article1 = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("aboutus"));
        article.setUrl("whoweare");
        article.setBodyEn("Who we are");
        article.setBodyRu("Кто мы");
        article.setBodyUa("Хто ми");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("Who we are");
        article.setTitleRu("Кто мы");
        article.setTitleUa("Хто ми");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article1);

        Article article2 = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("aboutus"));
        article.setUrl("mission_and_goals");
        article.setBodyEn("Mission and goals");
        article.setBodyRu("Миссия и цели");
        article.setBodyUa("Місія та цілі");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("Mission and goals");
        article.setTitleRu("Миссия и цели");
        article.setTitleUa("Місія та цілі");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article2);

        Article article3 = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("aboutus"));
        article.setUrl("how_to_use_site");
        article.setBodyEn("How to use our site");
        article.setBodyRu("Как пользоваться сайтом");
        article.setBodyUa("Як користуватися сайтом");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("How to use our site");
        article.setTitleRu("Как пользоваться сайтом");
        article.setTitleUa("Як користуватися сайтом");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article3);



        Article article4 = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("abouttrials"));
        article.setUrl("what_is_clinical_research");
        article.setBodyEn("What is clinical research");
        article.setBodyRu("Что такое клинические исследования");
        article.setBodyUa("Що таке клінічні дослідження ");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("What is clinical research");
        article.setTitleRu("Что такое клинические исследования");
        article.setTitleUa("Що таке клінічні дослідження ");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article4);

        Article article5 = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("abouttrials"));
        article.setUrl("the_history_of_clinical_research_in_the_world");
        article.setBodyEn("The history of clinical research in the world");
        article.setBodyRu("История клинических исследований в мире");
        article.setBodyUa("Історія клінічних досліджень у світі ");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("The history of clinical research in the world");
        article.setTitleRu("История клиничесских исследований в мире");
        article.setTitleUa("Історія клінічних досліджень у світі ");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article5);

        Article article6 = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("abouttrials"));
        article.setUrl("the_history_of_clinical_research_in_the_ukraine");
        article.setBodyEn("The history of clinical research in the Ukraine");
        article.setBodyRu("История клинических исследований в Украине");
        article.setBodyUa("Історія клінічних досліджень в Україні");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("The history of clinical research in the Ukraine");
        article.setTitleRu("История клиничесских исследований в Украине");
        article.setTitleUa("Історія клінічних досліджень в Україні");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article6);

        Article article7 = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("abouttrials"));
        article.setUrl("legislative_and_regulatory_framework");
        article.setBodyEn("Legislative and regulatory framework");
        article.setBodyRu("Законодательная и нормативно-правовая безы");
        article.setBodyUa("Законодавча та нормативно-правова бази");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("Legislative and regulatory framework");
        article.setTitleRu("Законодательная и нормативно-правовая безы");
        article.setTitleUa("Законодавча та нормативно-правова бази");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article7);

        Article article8 = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("abouttrials"));
        article.setUrl("glossary_of_terms");
        article.setBodyEn("Glossary of terms");
        article.setBodyRu("Словарь терминов");
        article.setBodyUa("Словник термінів");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("Glossary of terms");
        article.setTitleRu("Словарь терминов");
        article.setTitleUa("Словник термінів");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article8);


        Article article9 = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("abouttrials"));
        article.setUrl("useful links");
        article.setBodyEn("useful links");
        article.setBodyRu("Полезные ссылки");
        article.setBodyUa("Корисні посилання");
        article.setDescEn("description");
        article.setDescRu("Описание");
        article.setDescUa("Опис");
        article.setTitleEn("useful links");
        article.setTitleRu("Полезные ссылки");
        article.setTitleUa("Корисні посилання");
        article.setSeoDescEn("Seo En");
        article.setSeoDescRu("Seo RU");
        article.setSeoDescUa("Seo UA");
        articleRepository.save(article9);


    }
}
