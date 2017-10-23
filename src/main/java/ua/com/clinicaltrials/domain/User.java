package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * Created by Igor on 17-Jun-16.
 */
@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = -1000119078147252958L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "password_confirm")
    private String passwordConfirm;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", targetEntity = Article.class)
    private Set<Article> articles;

    @OneToMany(mappedBy = "user", targetEntity = Comment.class)
    private Set<Comment> comments;

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    @Transient
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}
