package ua.com.clinicaltrials.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Igor on 05-Oct-16.
 */
@Entity
@Table(name = "mof_ukraine_order")
@Getter
@Setter
public class MohOfUkraineOrder implements Serializable {
    private static final long serialVersionUID = -1000119475147222957L;

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
