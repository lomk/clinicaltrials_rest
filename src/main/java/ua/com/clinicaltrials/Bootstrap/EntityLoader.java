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
        category1.setUrl("about_us");
        category1.setNameRu("О нас");
        category1.setNameEn("About us");
        category1.setNameUa("Про нас");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setUrl("about_trials");
        category2.setNameRu("О клинических исследованиях");
        category2.setNameEn("About clinical trials");
        category2.setNameUa("Про клінічні дослідження");
        categoryRepository.save(category2);


        Article article = new Article();
        article.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article.setUser(userService.findByUsername("Admin"));
        article.setCategory(categoryRepository.findByUrl("news").get(0));
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
        article1.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article1.setUser(userService.findByUsername("Admin"));
        article1.setCategory(categoryRepository.findByUrl("about_us").get(0));
        article1.setUrl("whoweare");
        article1.setBodyEn("Who we are");
        article1.setBodyRu("Кто мы");
        article1.setBodyUa("Хто ми");
        article1.setDescEn("description");
        article1.setDescRu("Описание");
        article1.setDescUa("Опис");
        article1.setTitleEn("Who we are");
        article1.setTitleRu("Кто мы");
        article1.setTitleUa("Хто ми");
        article1.setSeoDescEn("Seo En");
        article1.setSeoDescRu("Seo RU");
        article1.setSeoDescUa("Seo UA");
        articleRepository.save(article1);

        Article article2 = new Article();
        article2.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article2.setUser(userService.findByUsername("Admin"));
        article2.setCategory(categoryRepository.findByUrl("about_us").get(0));
        article2.setUrl("mission_and_goals");
        article2.setBodyEn("Mission and goals");
        article2.setBodyRu("Миссия и цели");
        article2.setBodyUa("Місія та цілі");
        article2.setDescEn("description");
        article2.setDescRu("Описание");
        article2.setDescUa("Опис");
        article2.setTitleEn("Mission and goals");
        article2.setTitleRu("Миссия и цели");
        article2.setTitleUa("Місія та цілі");
        article2.setSeoDescEn("Seo En");
        article2.setSeoDescRu("Seo RU");
        article2.setSeoDescUa("Seo UA");
        articleRepository.save(article2);

        Article article3 = new Article();
        article3.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article3.setUser(userService.findByUsername("Admin"));
        article3.setCategory(categoryRepository.findByUrl("about_us").get(0));
        article3.setUrl("how_to_use_site");
        article3.setBodyEn("How to use our site");
        article3.setBodyRu("Как пользоваться сайтом");
        article3.setBodyUa("Як користуватися сайтом");
        article3.setDescEn("description");
        article3.setDescRu("Описание");
        article3.setDescUa("Опис");
        article3.setTitleEn("How to use our site");
        article3.setTitleRu("Как пользоваться сайтом");
        article3.setTitleUa("Як користуватися сайтом");
        article3.setSeoDescEn("Seo En");
        article3.setSeoDescRu("Seo RU");
        article3.setSeoDescUa("Seo UA");
        articleRepository.save(article3);



        Article article4 = new Article();
        article4.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article4.setUser(userService.findByUsername("Admin"));
        article4.setCategory(categoryRepository.findByUrl("about_trials").get(0));
        article4.setUrl("what_is_clinical_research");
        article4.setBodyEn("What is clinical research");
        article4.setBodyRu("Что такое клинические исследования");
        article4.setBodyUa("Що таке клінічні дослідження ");
        article4.setDescEn("description");
        article4.setDescRu("Описание");
        article4.setDescUa("Опис");
        article4.setTitleEn("What is clinical research");
        article4.setTitleRu("Что такое клинические исследования");
        article4.setTitleUa("Що таке клінічні дослідження ");
        article4.setSeoDescEn("Seo En");
        article4.setSeoDescRu("Seo RU");
        article4.setSeoDescUa("Seo UA");
        articleRepository.save(article4);

        Article article5 = new Article();
        article5.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article5.setUser(userService.findByUsername("Admin"));
        article5.setCategory(categoryRepository.findByUrl("about_trials").get(0));
        article5.setUrl("the_history_of_clinical_research_in_the_world");
        article5.setBodyEn("The history of clinical research in the world");
        article5.setBodyRu("История клинических исследований в мире");
        article5.setBodyUa("Історія клінічних досліджень у світі ");
        article5.setDescEn("description");
        article5.setDescRu("Описание");
        article5.setDescUa("Опис");
        article5.setTitleEn("The history of clinical research in the world");
        article5.setTitleRu("История клиничесских исследований в мире");
        article5.setTitleUa("Історія клінічних досліджень у світі ");
        article5.setSeoDescEn("Seo En");
        article5.setSeoDescRu("Seo RU");
        article5.setSeoDescUa("Seo UA");
        articleRepository.save(article5);

        Article article6 = new Article();
        article6.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article6.setUser(userService.findByUsername("Admin"));
        article6.setCategory(categoryRepository.findByUrl("about_trials").get(0));
        article6.setUrl("the_history_of_clinical_research_in_the_ukraine");
        article6.setBodyEn("The history of clinical research in the Ukraine");
        article6.setBodyRu("История клинических исследований в Украине");
        article6.setBodyUa("Історія клінічних досліджень в Україні");
        article6.setDescEn("description");
        article6.setDescRu("Описание");
        article6.setDescUa("Опис");
        article6.setTitleEn("The history of clinical research in the Ukraine");
        article6.setTitleRu("История клиничесских исследований в Украине");
        article6.setTitleUa("Історія клінічних досліджень в Україні");
        article6.setSeoDescEn("Seo En");
        article6.setSeoDescRu("Seo RU");
        article6.setSeoDescUa("Seo UA");
        articleRepository.save(article6);

        Article article7 = new Article();
        article7.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article7.setUser(userService.findByUsername("Admin"));
        article7.setCategory(categoryRepository.findByUrl("about_trials").get(0));
        article7.setUrl("legislative_and_regulatory_framework");
        article7.setBodyEn("Legislative and regulatory framework");
        article7.setBodyRu("Законодательная и нормативно-правовая безы");
        article7.setBodyUa("Законодавча та нормативно-правова бази");
        article7.setDescEn("description");
        article7.setDescRu("Описание");
        article7.setDescUa("Опис");
        article7.setTitleEn("Legislative and regulatory framework");
        article7.setTitleRu("Законодательная и нормативно-правовая безы");
        article7.setTitleUa("Законодавча та нормативно-правова бази");
        article7.setSeoDescEn("Seo En");
        article7.setSeoDescRu("Seo RU");
        article7.setSeoDescUa("Seo UA");
        articleRepository.save(article7);

        Article article8 = new Article();
        article8.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article8.setUser(userService.findByUsername("Admin"));
        article8.setCategory(categoryRepository.findByUrl("about_trials").get(0));
        article8.setUrl("glossary_of_terms");
        article8.setBodyEn("Glossary of terms");
        article8.setBodyRu("Словарь терминов");
        article8.setBodyUa("Словник термінів");
        article8.setDescEn("description");
        article8.setDescRu("Описание");
        article8.setDescUa("Опис");
        article8.setTitleEn("Glossary of terms");
        article8.setTitleRu("Словарь терминов");
        article8.setTitleUa("Словник термінів");
        article8.setSeoDescEn("Seo En");
        article8.setSeoDescRu("Seo RU");
        article8.setSeoDescUa("Seo UA");
        articleRepository.save(article8);


        Article article9 = new Article();
        article9.setDateField(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        article9.setUser(userService.findByUsername("Admin"));
        article9.setCategory(categoryRepository.findByUrl("about_trials").get(0));
        article9.setUrl("useful links");
        article9.setBodyEn("useful links");
        article9.setBodyRu("Полезные ссылки");
        article9.setBodyUa("Корисні посилання");
        article9.setDescEn("description");
        article9.setDescRu("Описание");
        article9.setDescUa("Опис");
        article9.setTitleEn("useful links");
        article9.setTitleRu("Полезные ссылки");
        article9.setTitleUa("Корисні посилання");
        article9.setSeoDescEn("Seo En");
        article9.setSeoDescRu("Seo RU");
        article9.setSeoDescUa("Seo UA");
        articleRepository.save(article9);


    }
}
