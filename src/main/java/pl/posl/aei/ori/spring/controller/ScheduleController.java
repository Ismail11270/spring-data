package pl.posl.aei.ori.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.posl.aei.ori.spring.model.Schedule;
import pl.posl.aei.ori.spring.repository.ScheduleRepository;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleRepository sr;

    @GetMapping
    Page<Schedule> getSchedules( Pageable page){
        return sr.findAll( page );
    }

    @GetMapping("/searchBySubjectId")
    List<Schedule> getById( @RequestParam int subject){
        return sr.findSchedulesBySubjectId( subject );
    }
}
