package pl.posl.aei.ori.spring.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import pl.posl.aei.ori.spring.converter.GenderConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@EqualsAndHashCode
@Setter
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {
    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "EMP_NAME", length = 50)
    private String name;

    @Column(name = "GENDER")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "DATE_OF_BIRTH")
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "TEAM_ID", foreignKey = @ForeignKey(name = "FK_EMP_TEAM_ID"))
    private Team team;

}