package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 10-Oct-16.
 */
@Entity
@Table(name = "medical_form")
@Getter
@Setter
public class MedicalForm implements Serializable {
    private static final long serialVersionUID = -1000119474155252957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "form_ua")
    private String formUa;
    @Column(name = "form_ru")
    private String formRu;
    @Column(name = "form_en")
    private String formEn;
}
