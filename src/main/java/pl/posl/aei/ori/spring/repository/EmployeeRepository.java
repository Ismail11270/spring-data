package pl.posl.aei.ori.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.posl.aei.ori.spring.model.Employee;
import pl.posl.aei.ori.spring.model.Gender;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findEmployeesByGenderLike(Gender gender, Pageable pageable);




}
