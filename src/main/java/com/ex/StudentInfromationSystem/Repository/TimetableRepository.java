package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.TimetableEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface TimetableRepository
        extends JpaRepository<TimetableEntry, Long> {

    List<TimetableEntry>
    findByClassEntityIdAndSectionIdAndDayOfWeek(
            Long classId,
            Long sectionId,
            DayOfWeek dayOfWeek);

    List<TimetableEntry>
    findByTeacherIdAndDayOfWeek(
            Long teacherId,
            DayOfWeek dayOfWeek);
}
