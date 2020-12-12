package pl.posl.aei.ori.spring.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "TEAMS", uniqueConstraints = {
        @UniqueConstraint( columnNames = "TEAM_NAME", name = "UK_TEAM_NAME")
})
public class Team implements Serializable {
    @Id
    @Column(name="TEAM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TEAM_NAME", length = 50, nullable = false)
    private String teamName;

    @Column(name="MANAGER_ID")
    private Integer managerId;

    @ManyToOne
    @JoinColumn(name="INSTITUTE_ID", foreignKey = @ForeignKey(name = "FK_TEAM_INST_ID"))
    private Institute institute;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass( ) != o.getClass( ) ) return false;
        Team team = ( Team ) o;
        return id == team.id &&
                Objects.equals( teamName, team.teamName ) &&
                Objects.equals( institute, team.institute );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, teamName, institute );
    }

    private void writeObject( ObjectOutputStream oos) throws IOException {
        // default serialization
        oos.defaultWriteObject();
        // write the object attributes
        oos.writeInt(id);
        oos.writeObject(teamName);
        oos.writeInt(managerId);
        oos.writeObject(institute);
    }

    private void readObject( ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        // default deserialization
        ois.defaultReadObject();
        //read the attributes
        id = ois.readInt();
        teamName = ( String ) ois.readObject();
        managerId = ois.readInt();
        institute = ( Institute ) ois.readObject();
    }

}
