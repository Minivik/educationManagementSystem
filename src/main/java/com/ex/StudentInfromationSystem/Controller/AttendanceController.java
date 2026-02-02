package com.ex.StudentInfromationSystem.Controller;

import com.ex.StudentInfromationSystem.Entities.AttendanceRecord;
import com.ex.StudentInfromationSystem.Enums.AttendanceStatus;
import com.ex.StudentInfromationSystem.Service.AttendanceServiceImpl;
import com.ex.StudentInfromationSystem.dto.AttendanceSummaryResponse;
import com.ex.StudentInfromationSystem.dto.DailyAttendancePercentageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class AttendanceController {

@Autowired
private AttendanceServiceImpl attendanceService;

    @PostMapping("markAttendance")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN,'PRINCIPAL')")
    public void markAttendance(@RequestBody Map<Long, AttendanceStatus> records){
        attendanceService.markAttendance(LocalDate.now(),records);
    }


    @GetMapping("StudAttendance{studentId}")
      @PreAuthorize("hasAnyRole('TEACHER','ADMIN','PRINCIPAL")
    public List<AttendanceRecord> get(@PathVariable Long studentId) {
        return attendanceService.getForStudentAttendance(studentId);
    }

    @GetMapping("/AttendanceSummary")
    //  @PreAuthorize("hasAnyRole('ADMIN','PRINCIPAL')")
    public AttendanceSummaryResponse getStd_AttendanceSummary(@PathVariable Long std_id){
        return attendanceService.StdAttendanceSummary(std_id);
    }

    @GetMapping("/AttendancePercentage")
    //  @PreAuthorize("hasAnyRole('ADMIN','PRINCIPAL')")
    public DailyAttendancePercentageResponse std_AttendancePercentage(LocalDate date){
        return attendanceService.dailyAttendanceTotalPercentage( date);
    }
}
