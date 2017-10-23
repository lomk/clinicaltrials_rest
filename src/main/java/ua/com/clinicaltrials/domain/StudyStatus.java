package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 07-Oct-16.
 */
@Entity
@Table(name = "study_status")
@Getter
@Setter
public class StudyStatus implements Serializable {
    private static final long serialVersionUID = -1000119478147452957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "status_ua")
    private String statusUa;

    @Column(name = "status_ru")
    private String statusRu;

    @Column(name = "status_en")
    private String statusEn;
}
