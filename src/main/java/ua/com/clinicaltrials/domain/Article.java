package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created by Igor on 19-Jul-16.
 */
@Entity
@Table(name = "article")
@Getter
@Setter
public class Article implements Serializable {
    private static final long serialVersionUID = -1000119478147250324L;

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
    @Column(name = "rating", nullable = false)
    private Long rating;
    @Column (name = "img_url")
    private String imgUrl;

    @OneToMany(mappedBy = "article", targetEntity = Comment.class)
    @OrderBy("dateTimeField ASC")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinColumn(name = "tag_id")
    private List<Tag> tags ;


}
