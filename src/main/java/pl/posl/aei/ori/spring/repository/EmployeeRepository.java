package pl.posl.aei.ori.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;
import pl.posl.aei.ori.spring.model.Employee;
import pl.posl.aei.ori.spring.model.Gender;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findEmployeesByGenderLike(Gender gender, Pageable pageable);

    List<Employee> findEmployeesByNameIgnoreCaseLikeAndDateLessThan(@RequestParam String name, @RequestParam Date date);

    @Query("SELECT count(e) FROM Employee e WHERE e.team.teamName=:teamName")
    Integer findNumberOfEmployeesByTeamNameIgnoreCase(@RequestParam String teamName);
}
