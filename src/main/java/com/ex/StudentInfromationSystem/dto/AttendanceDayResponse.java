package com.ex.StudentInfromationSystem.dto;

import com.ex.StudentInfromationSystem.Enums.AttendanceStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDayResponse {

    public LocalDate date;
    public AttendanceStatus status;

    public AttendanceDayResponse(LocalDate date, AttendanceStatus status) {
        this.date = date;
        this.status = status;
    }
}
