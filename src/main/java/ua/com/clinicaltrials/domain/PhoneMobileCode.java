package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 11-Oct-16.
 */
@Entity
@Table(name = "phone_mobile_code")
@Getter
@Setter
public class PhoneMobileCode implements Serializable {
    private static final long serialVersionUID = -1000119478547292957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "code")
    private String code;
}
