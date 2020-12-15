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
import pl.posl.aei.ori.spring.model.ClassRoom;
import pl.posl.aei.ori.spring.repository.ClassroomRepository;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomRepository cr;

    @GetMapping
    public Page<ClassRoom> getAll(@RequestParam String sortBy){
        Pageable pageable = PageRequest.of(0,1000, Sort.by(sortBy).descending());
        return cr.findAll(pageable);
    }

    @GetMapping("/get")
    public ClassRoom getById(@RequestParam int id){
        return cr.findById(id).orElse(null);
    }

    @GetMapping("/sizeScreen")
    public List<ClassRoom> getBySizeAfterAndScreen(@RequestParam int size, @RequestParam boolean screen) {
        return cr.findClassRoomsByRoomSizeIsAfterAndScreen(size,screen);
    }

    @GetMapping("/withSize")
    public List<ClassRoom> getByRoomSize(@RequestParam int size){
        return cr.findClassRoomsByRoomSizeIsLike(size);
    }

    @GetMapping("/withScreen")
    public Page<ClassRoom> getClassRoomsWithScreenAndMaxSize(@RequestParam(required = false, defaultValue = "100") int maxSize,
                                                             @RequestParam(required = false, defaultValue = "100") int limit){
        return cr.findClassRoomsByScreenIsTrueAndRoomSizeLessThanEqual(maxSize, PageRequest.of(0,limit, Sort.by("roomSize").ascending()));
    }

    @GetMapping("/maxSize")
    public String getMaxSizeOfRooms(@RequestParam boolean screen) {
        return String.format("<h1>Max size is %d</h1>", cr.findMaxSizeOfRooms(screen));
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id){
        cr.deleteById(id);
        return "Classroom with id " + id + " deleted!";
    }
}