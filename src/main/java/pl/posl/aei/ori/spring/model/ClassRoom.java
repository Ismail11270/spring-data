package pl.posl.aei.ori.spring.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.posl.aei.ori.spring.util.converter.BooleanConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@EqualsAndHashCode
@Setter
@Table(name="CLASSROOMS")
public class ClassRoom implements Serializable {

    @Id
    @Column(name="ROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="ROOM_SIZE")
    private int roomSize;

    @Column(name="SCREEN")
    @Convert(converter = BooleanConverter.class)
    private Boolean screen;
}
