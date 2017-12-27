package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "about_us_article")
@Getter
@Setter
public class AboutUsArticle {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "title_ua", nullable = false, length = 200)
    private String titleUa;

    @Column(name = "title_ru", nullable = false, length = 200)
    private String titleRu;

    @Column(name = "title_en", nullable = false, length = 200)
    private String titleEn;

    @Column(name = "description_ua", nullable = false, length = 400)
    private String descUa;

    @Column(name = "description_ru", nullable = false, length = 400)
    private String descRu;

    @Column(name = "description_en", nullable = false, length = 400)
    private String descEn;

    @Column(name = "seo_description_ua", nullable = false, length = 400)
    private String seoDescUa;

    @Column(name = "seo_description_ru", nullable = false, length = 400)
    private String seoDescRu;

    @Column(name = "seo_description_en", nullable = false, length = 400)
    private String seoDescEn;

    @Column(name = "body_ua", nullable = false, length = 10000, columnDefinition="TEXT")
    private String bodyUa;

    @Column(name = "body_ru", nullable = false, length = 10000, columnDefinition="TEXT")
    private String bodyRu;

    @Column(name = "body_en", nullable = false, length = 10000, columnDefinition="TEXT")
    private String bodyEn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_field")
    private Date dateField;

    @Column (name = "img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
