package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.AcademicCalendarDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicCalendarRepository extends JpaRepository<AcademicCalendarDay,Long> {

    List<AcademicCalendarDay>
    findByAcademicYearId(Long academicYearId);
    Optional<AcademicCalendarDay> findByAcademicYearIdAndCalendarDate(Long academicYearId, LocalDate date);
    Optional<AcademicCalendarDay> findByDate(LocalDate date);
}
