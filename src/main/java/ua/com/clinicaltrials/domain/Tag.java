package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Igor on 19-Jul-16.
 */
@Entity
@Table(name = "tag")
@Getter
@Setter
public class Tag implements Serializable {
    private static final long serialVersionUID = -1004119478147252957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

//    @ManyToMany(mappedBy = "tags", targetEntity = Article.class)
//    @OrderBy("dateField ASC")
//    private List<Article> articles;
}
