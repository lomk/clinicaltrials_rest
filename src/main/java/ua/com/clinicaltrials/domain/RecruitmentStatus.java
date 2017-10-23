package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mater on 21-Jan-17.
 */
@Entity
@Table(name = "recruitment_status")
@Getter
@Setter
public class RecruitmentStatus  implements Serializable {
    private static final long serialVersionUID = -1000119428147251957L;

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
