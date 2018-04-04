package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 29-Jun-16.
 */
@Entity
@Table(name = "role")
@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = -1200119878147252957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "role", targetEntity = User.class)
//    private Set<User> users;

}
