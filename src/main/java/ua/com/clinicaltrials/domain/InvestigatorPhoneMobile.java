package ua.com.clinicaltrials.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 11-Oct-16.
 */
@Entity
@Table(name = "investigator_phone_mobile")
@Getter
@Setter
public class InvestigatorPhoneMobile implements Serializable {
    private static final long serialVersionUID = -1000119478145452957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "investigator_id")
    private Investigator investigator;

    @ManyToOne
    @JoinColumn(name = "phone_mobile_code_id")
    private PhoneMobileCode phoneMobileCode;

    @Column(name = "phone")
    private String phone;
}
