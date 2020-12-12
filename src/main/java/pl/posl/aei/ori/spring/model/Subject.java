package pl.posl.aei.ori.spring.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "SUBJECTS")
public class Subject implements Serializable {
    @Id
    @Column(name="SUBJECT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="SUBJECT_NAME",length = 40,nullable = false)
    private String subjectName;

    @ManyToOne
    @Cascade( org.hibernate.annotations.CascadeType.SAVE_UPDATE )
    @JoinColumn(name = "SUB_TYPE_ID", foreignKey = @ForeignKey(name = "FK_SUBJECT_SUBJECT_TYPE_ID"), nullable = false)
    private SubjectType subjectType;

    @ManyToOne
    @Cascade( org.hibernate.annotations.CascadeType.SAVE_UPDATE )
    @JoinColumn(name = "SUPER_SUB_ID", foreignKey = @ForeignKey(name = "FK_SUBJECT_SUPER_SUBJECT_ID"))
    private Subject superSubject;

    @ManyToOne
    @Cascade( org.hibernate.annotations.CascadeType.SAVE_UPDATE )
    @JoinColumn(name = "SUPERVISOR_ID", foreignKey = @ForeignKey(name = "FK_SUBJECT_EMPLOYEE_ID"))
    private Employee supervisor;

    @ManyToOne
    @Cascade( org.hibernate.annotations.CascadeType.SAVE_UPDATE )
    @JoinColumn(name = "MAJOR_ID", foreignKey = @ForeignKey(name = "FK_SUBJECT_MAJOR_ID"))
    private Major major;



}
