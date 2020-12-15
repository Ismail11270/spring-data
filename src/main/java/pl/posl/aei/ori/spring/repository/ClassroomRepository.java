package pl.posl.aei.ori.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.posl.aei.ori.spring.model.ClassRoom;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<ClassRoom, Integer> {

    List<ClassRoom> findClassRoomsByRoomSizeIsAfterAndScreen(@RequestParam int roomSize,
                                                             @RequestParam boolean screen);

    List<ClassRoom> findClassRoomsByRoomSizeIsLike(@RequestParam int size);

    Page<ClassRoom> findClassRoomsByScreenIsTrueAndRoomSizeLessThanEqual(@RequestParam int roomSize, Pageable pageable);

    @Query("SELECT max(c.roomSize) FROM ClassRoom c WHERE c.screen=:screen")
    Integer findMaxSizeOfRooms(@RequestParam boolean screen);
}
