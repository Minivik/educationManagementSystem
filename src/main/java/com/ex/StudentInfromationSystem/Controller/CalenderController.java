package com.ex.StudentInfromationSystem.Controller;

import com.ex.StudentInfromationSystem.Entities.AcademicCalendarDay;
import com.ex.StudentInfromationSystem.Enums.DayType;
import com.ex.StudentInfromationSystem.Service.AcademicCalendarService;
import com.ex.StudentInfromationSystem.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController("/academic/calender")
public class CalenderController {

    @Autowired
    private AcademicCalendarService academicCalendarService;


    @PostMapping("/createCalenderDay")
        public String CreateDayCalender(@RequestBody AcademicCalendarDay  input){
           try {
               academicCalendarService.createCalendarDay(input.getAcademicYear().getId()
                       ,input.getCalendarDate(),input.getDayType(),input.getDescription());
           }catch (Exception e){
               throw  new RuntimeException("calender creation issue");
        }
      return  "Calender created details " + input;
    }

    @PutMapping("/year/{academicYearId}/day")
    public  String  createOrUpdateDay(
            @PathVariable("academicYearId")
            Long academicYearId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,
            @RequestParam
            DayType dayType,
            @RequestParam(required = false)
            String description
    ) {
        try{
            academicCalendarService.createOrUpdateDay(academicYearId,date,dayType,description);
        }catch (Exception ex){
            throw  new RuntimeException("exception for update calender ");
        }
        return  "updated  successfully for date of   " +date;

 }
    @PostMapping("/year/{academicYearId}/range")
    public String createRange(
            @PathVariable Long academicYearId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            @RequestParam DayType defaultDayType
    ){
academicCalendarService.createRange(academicYearId,startDate,endDate,defaultDayType);

return    "calender created successfully up to " + endDate;

    }

    @GetMapping("/getYearCalendar{year}")
    public List<AcademicCalendarDay> getYearCalenderByYear(@PathVariable Long year){

      return   academicCalendarService.getCalendarByYear(year);

    }


}
