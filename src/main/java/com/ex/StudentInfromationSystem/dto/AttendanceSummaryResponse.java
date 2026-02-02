package com.ex.StudentInfromationSystem.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class AttendanceSummaryResponse {

    public Long studentId;
    public long totalDays;
    public long presentDays;
    public long absentDays;

    public List<AttendanceDayResponse> dailyDetails;


public AttendanceSummaryResponse(
        Long studentId,
        long totalDays,
        long presentDays,
        long absentDays,
        List<AttendanceDayResponse> dailyDetails
) {
    this.studentId = studentId;
    this.totalDays = totalDays;
    this.presentDays = presentDays;
    this.absentDays = absentDays;
    this.dailyDetails = dailyDetails;
}

}