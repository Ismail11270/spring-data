package pl.posl.aei.ori.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.posl.aei.ori.spring.model.Schedule;
import pl.posl.aei.ori.spring.model.ScheduleId;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {
//    @Query("SELECT s FROM Schedule s WHERE s.subject=(:id)")
    List<Schedule> findSchedulesBySubjectId( @RequestParam int subjectId );
}
