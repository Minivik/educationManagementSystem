package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.StudentAcademicRecord;
import com.ex.StudentInfromationSystem.Enums.AttendanceStatus;
import com.ex.StudentInfromationSystem.Enums.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentScopeEnrollmentRepo extends JpaRepository<StudentAcademicRecord,Long> {
    Optional<StudentAcademicRecord> findByStudentIdAndAcademicStatus(
            Long studentId,
            EnrollmentStatus status
    );
    long countByAcademicStatus(EnrollmentStatus status);
    boolean existsByAcademicRecordIdAndDate(Long academicRecordId, LocalDate date);

    long countByDate_Status(AttendanceStatus attendanceStatus, LocalDate date);
}
