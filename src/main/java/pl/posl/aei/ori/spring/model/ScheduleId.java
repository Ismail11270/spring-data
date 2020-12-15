package pl.posl.aei.ori.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class ScheduleId implements Serializable {
    private int subjectId;
    private int employeeId;
    private int classRoomId;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass( ) != o.getClass( ) ) return false;
        ScheduleId that = ( ScheduleId ) o;
        return subjectId == that.subjectId &&
                employeeId == that.employeeId &&
                classRoomId == that.classRoomId;
    }

    @Override
    public int hashCode() {
        return Objects.hash( subjectId, employeeId, classRoomId );
    }

    public ScheduleId(){

    }
}
