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
import pl.posl.aei.ori.spring.model.Employee;
import pl.posl.aei.ori.spring.model.Gender;
import pl.posl.aei.ori.spring.repository.EmployeeRepository;
import pl.posl.aei.ori.spring.util.ParseUtils;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository er;

    @GetMapping
    public Page<Employee> getAll(@RequestParam(required = false, defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(0, 1000, Sort.by(sortBy).ascending());
        return er.findAll(pageable);
    }

    @GetMapping("/get")
    public Employee getById(@RequestParam int id) {
        return er.findById(id).orElse(null);
    }

    @GetMapping("/gender")
    public Page<Employee> getEmployeesByGender(@RequestParam Gender gender) {
        Pageable pageable = PageRequest.of(0, 1000, Sort.by("date").descending());
        return er.findEmployeesByGenderLike(gender, pageable);
    }

    @GetMapping("/findByName")
    public List<Employee> getByNameAndDateLessThan(@RequestParam String startsWith,
                                                   @RequestParam(required = false, defaultValue = ParseUtils.MIN_DATE_STRING) String date) {
            return er.findEmployeesByNameIgnoreCaseLikeAndDateLessThan(startsWith+'%', ParseUtils.parseDate(date));
    }

    @GetMapping("/count")
    public String getNumberOfEmployeesByTeamName(@RequestParam String teamName) {
        Integer n = er.findNumberOfEmployeesByTeamNameIgnoreCase(teamName);
        return String.format("%d Employees work in team %s", n, teamName);
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        er.deleteById(id);
        return "Employee with id " + id + " deleted!";
    }

}
