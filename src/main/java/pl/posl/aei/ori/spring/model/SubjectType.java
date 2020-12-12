package pl.posl.aei.ori.spring.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "SUBJECT_TYPES")
public class SubjectType implements Serializable {
    @Id
    @Column(name="SUB_TYPE_ID", length = 3)
    private String id;

    @Column(name = "SUB_TYPE_NAME", length = 50, nullable = false)
    private String name;
}
