package com.ex.StudentInfromationSystem.Service;

import com.ex.StudentInfromationSystem.Entities.*;
import com.ex.StudentInfromationSystem.Repository.TimetableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TimetableService {

    private final TimetableRepository timetableRepo;

    public TimetableEntry createEntry(
            AcademicYear year,
            ClassEntity classEntity,
            Section section,
            DayOfWeek day,
            PeriodDefinition period,
            Subject subject,
            Staff teacher) {

        TimetableEntry entry = new TimetableEntry();

        entry.setAcademicYear(year);
        entry.setClassEntity(classEntity);
        entry.setSection(section);
        entry.setDayOfWeek(day);
        entry.setPeriodDefinition(period);
        entry.setSubject(subject);
        entry.setTeacher(teacher);
        return timetableRepo.save(entry);
    }

 //   @Transactional(readOnly = true)
    public List<TimetableEntry> getClassDayTimetable(
            Long classId,
            Long sectionId,
            DayOfWeek day) {

        return timetableRepo
                .findByClassEntityIdAndSectionIdAndDayOfWeek(
                        classId, sectionId, day);
    }
}
