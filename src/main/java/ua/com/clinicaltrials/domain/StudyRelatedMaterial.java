package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Igor on 10-Oct-16.
 */
@Entity
@Table(name = "study_related_material")
@Getter
@Setter
public class StudyRelatedMaterial  implements Serializable {
    private static final long serialVersionUID = -1000519478142252957L;

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
    @ManyToOne
    @JoinColumn(name = "drug_manufacturer_id")
    private DrugManufacturer drugManufacturer;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
