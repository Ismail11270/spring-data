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

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository er;

    @GetMapping("/gender")
    public Page<Employee> getEmployeesByGender(@RequestParam Gender gender){
        Pageable pageable = PageRequest.of(0,1000, Sort.by("date").descending());
        return er.findEmployeesByGenderLike(gender,pageable);
    }
}
