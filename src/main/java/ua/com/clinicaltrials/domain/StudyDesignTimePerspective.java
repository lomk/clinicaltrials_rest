package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 09-Oct-16.
 */
@Entity
@Table(name = "study_design_time_perspective")
@Getter
@Setter
public class StudyDesignTimePerspective implements Serializable {
    private static final long serialVersionUID = -1008119478147251957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name_ua")
    private String nameUa;
    @Column(name = "name_ru")
    private String nameRu;
    @Column(name = "name_en")
    private String nameEn;
}
