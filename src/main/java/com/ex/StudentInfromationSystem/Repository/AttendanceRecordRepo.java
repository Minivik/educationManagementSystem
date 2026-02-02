package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.AttendanceRecord;
import com.ex.StudentInfromationSystem.dto.AttendanceDayResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface AttendanceRecordRepo extends JpaRepository<AttendanceRecord,Long> {
    public boolean existsByAcademicRecordIdAndDate(Long academicRecordId, LocalDate date) ;

    List<AttendanceRecord> findByAcademicRecordId(Long id);

    List<AttendanceDayResponse> findByStudentId(Long studentId);
}
