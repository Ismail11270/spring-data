package pl.posl.aei.ori.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;
import pl.posl.aei.ori.spring.model.Gender;
import pl.posl.aei.ori.spring.model.Student;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {


    List<Student> findStudentsByDateOfBirthLessThanAndMajor_Institute_InstituteNameLike(@RequestParam Date dateOfBirth
            , @RequestParam String instituteNam);

    @Query("SELECT s FROM Student s WHERE s.gender=:gender OR s.dateOfBirth >= :dob")
    List<Student> findStudentNamesByGenderOrDateOfBirthMoreThan(@RequestParam Gender gender,
                                                       @RequestParam Date dob);

    List<Student> findStudentsByIdIsBetweenOrDateOfBirthIsBetween(@RequestParam int idA,@RequestParam int idB,
                                                                  @RequestParam Date dobA, @RequestParam Date dobB);

    Student findFirstByMajor_MajorNameIgnoreCaseOrderByDateOfBirthDesc(@RequestParam String majorName);

}
