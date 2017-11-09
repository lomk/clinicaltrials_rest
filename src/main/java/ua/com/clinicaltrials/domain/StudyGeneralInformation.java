package ua.com.clinicaltrials.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Igor on 09-Oct-16.
 */@Entity
@Table(name = "study_general_information")
@Getter
@Setter
public class StudyGeneralInformation implements Serializable {
    private static final long serialVersionUID = -1000119468147212957L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /*@ManyToOne
    @JoinColumn(name = "therapeutic_area_id")
    private TherapeuticArea therapeuticArea;*/

    @ManyToOne
    @JoinColumn(name = "medical_condition_id")
    private MedicalCondition medicalCondition;

    @Column(name = "protocol_title_ua")
    private String protocolTitleUa;

    @Column(name = "protocol_title_ru")
    private String protocolTitleRu;

    @Column(name = "protocol_title_en")
    private String protocolTitleEn;

    @Column(name = "detailed_description_ua")
    private String detailedDescriptionUa;

    @Column(name = "detailed_description_ru")
    private String detailedDescriptionRu;

    @Column(name = "detailed_description_en")
    private String detailedDescriptionEn;

    @Column(name = "purpose_ua")
    private String purposeUa;
    @Column(name = "purpose_ru")
    private String purposeRu;
    @Column(name = "purpose_en")
    private String purposeEn;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @ManyToOne
    @JoinColumn(name = "study_type_id")
    private StudyType studyType;

    @ManyToOne
    @JoinColumn(name = "study_design_allocation")
    private StudyDesignAllocation studyDesignAllocation;

    @ManyToOne
    @JoinColumn(name = "study_design_endpoint_classification")
    private StudyDesignEndpointClassification studyDesignEndpointClassification;

    @ManyToOne
    @JoinColumn(name = "study_design_intervention_model")
    private StudyDesignInterventionModel studyDesignInterventionModel;

    @ManyToOne
    @JoinColumn(name = "study_design_masking")
    private StudyDesignMasking masking;

    @ManyToOne
    @JoinColumn(name = "study_design_primary_purpose")
    private StudyDesignPrimaryPurpose primaryPurpose;

    @ManyToOne
    @JoinColumn(name = "study_design_observationModel")
    private StudyDesignObservationalModel observationalModel;

    @ManyToOne
    @JoinColumn(name = "study_design_time_perspective")
    private StudyDesignTimePerspective timePerspective;

    @Column(name = "quantity_of_trial_sites")
    private Integer quantityOfTrialSites;

    @JsonIgnore
    @OneToOne(mappedBy = "studyIdentifier")
    ClinicalStudy studyGeneralInformation;

    /*@ManyToOne
    @JoinColumn(name = "study_conduction_id")
    private StudyConduction studyConduction;*/

//    @ManyToMany
//    @JoinTable(name = "study_general_information_country",
//            joinColumns = {@JoinColumn(name = "study_general_info_id")},
//            inverseJoinColumns = {@JoinColumn(name = "country_id")}
//    )
//    @OrderBy("name_en")
//    private SortedSet<Country> studyConductionCountries;
//
//    @ManyToMany
//    @JoinTable(name = "study_general_information_investigational_products",
//    joinColumns = {@JoinColumn(name = "investigational_product_id")},
//            inverseJoinColumns = {@JoinColumn(name = "study_general_information_id")}
//    )
//    private Set<InvestigationalProduct> investigationalProducts;
//
//    @ManyToMany
//    @JoinTable(name = "study_general_information_comparator",
//            joinColumns = {@JoinColumn(name = "study_general_information_id")},
//            inverseJoinColumns = {@JoinColumn(name = "comparator_id")}
//    )
//    @OrderBy("name_en")
//    private SortedSet<Comparator> comparators;
//
//    @ManyToMany
//    @JoinTable(name = "study_general_information_material",
//            joinColumns = {@JoinColumn(name = "study_general_information_id")},
//            inverseJoinColumns = {@JoinColumn(name = "material_id")}
//    )
//    @OrderBy("name_en")
//    private SortedSet<StudyRelatedMaterial> studyRelatedMaterials;
}
