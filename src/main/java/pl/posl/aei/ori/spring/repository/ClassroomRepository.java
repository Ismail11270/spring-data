package pl.posl.aei.ori.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.posl.aei.ori.spring.model.ClassRoom;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassRoom, Integer> {

}
