package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Igor on 19-Jul-16.
 */
@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment implements Serializable {
    private static final long serialVersionUID = -1000119478140011957L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "body", nullable = false, length = 2000)
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_field")
    private Date dateTimeField;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
