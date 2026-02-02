package com.ex.StudentInfromationSystem.Repository;

import com.ex.StudentInfromationSystem.Entities.AttendancePeriod;
import com.ex.StudentInfromationSystem.Entities.AttendanceRecord;
import com.ex.StudentInfromationSystem.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;

@Repository
public interface AttendancePeriodRepository  extends JpaRepository<AttendancePeriod,Long> {
//AttendanceRecord findByStudentAndAttendanceDate(Student student, LocalDate date);
}
