package com.ex.StudentInfromationSystem.dto;

import java.time.LocalDate;

public class DailyAttendancePercentageResponse {

    public LocalDate date;
    public long totalStudents;
    public long presentStudents;
    public double presentPercentage;

    public DailyAttendancePercentageResponse(
            LocalDate date,
            long totalStudents,
            long presentStudents
    ) {
        this.date = date;
        this.totalStudents = totalStudents;
        this.presentStudents = presentStudents;
        this.presentPercentage =
                totalStudents == 0 ? 0.0 :
                        (presentStudents * 100.0) / totalStudents;
    }
}
