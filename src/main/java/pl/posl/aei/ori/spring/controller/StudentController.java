package pl.posl.aei.ori.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.posl.aei.ori.spring.model.Student;
import pl.posl.aei.ori.spring.repository.StudentRepository;
import pl.posl.aei.ori.spring.util.ParseUtils;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class StudentController {
    @Autowired
    private StudentRepository sr;

    @GetMapping
    public Page<Student> getStudents(@RequestParam(required = false, defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(0, 1000, Sort.by(sortBy).ascending());
        return sr.findAll(pageable);
    }

    @GetMapping("/byId")
    public Student getById(@RequestParam int id){
        return sr.findById(id).orElse(null);
    }

    @GetMapping("/getByIdOrBirthdayRange")
    public List<Student> getByIdRangeOrDateOfBirthRange(@RequestParam int idA,@RequestParam int idB,
                                                        @RequestParam String dateOfBirthA, @RequestParam String dateOfBirthB) {
        return sr.findStudentsByIdIsBetweenOrDateOfBirthIsBetween(idA, idB, ParseUtils.parseDate(dateOfBirthA), ParseUtils.parseDate(dateOfBirthB) );
    }

    @GetMapping("/getByGenderOrOlderThan")
    public List<Student> getByGenderOrOlderThan(@RequestParam String gender, String birthdayBefore) {
        return sr.findStudentNamesByGenderOrDateOfBirthMoreThan(ParseUtils.parseGender(gender), ParseUtils.parseDate(birthdayBefore));
    }

    @GetMapping("/getByInstituteName")
    public List<Student> getStudentsFromInstitute(@RequestParam String startsWith,
                                                  @RequestParam(required = false, defaultValue = "1900-01-01") String dateOfBirth){
        return sr.findStudentsByDateOfBirthLessThanAndMajor_Institute_InstituteNameLike(ParseUtils.parseDate(dateOfBirth), startsWith + '%');
    }

    @GetMapping("/youngestByMajor")
    public Student getYoungestStudentByMajor(@RequestParam String majorName){
        return sr.findFirstByMajor_MajorNameIgnoreCaseOrderByDateOfBirthDesc(majorName);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        sr.deleteById(id);
        return "Student with id " + id + " was deleted!";
    }
}
