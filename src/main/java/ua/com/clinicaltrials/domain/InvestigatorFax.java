package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 11-Oct-16.
 */
@Entity
@Table(name = "investigator_fax")
@Getter
@Setter
public class InvestigatorFax implements Serializable {
    private static final long serialVersionUID = -1000119478191452957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "investigator_id")
    private Investigator investigator;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "phone")
    private String phone;
}
