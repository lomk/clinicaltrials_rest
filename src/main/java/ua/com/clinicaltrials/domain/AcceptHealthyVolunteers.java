package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 10-Oct-16.
 */
@Entity
@Table(name = "accept_healthy_volunteers")
@Getter
@Setter
public class AcceptHealthyVolunteers implements Serializable {
    private static final long serialVersionUID = -1000112367947252957L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "value_ua")
    private String valueUa;

    @Column(name = "value_ru")
    private String valueRu;

    @Column(name = "value_en")
    private String valueEn;
}
