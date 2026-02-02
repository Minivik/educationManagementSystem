package com.ex.StudentInfromationSystem.Controller;


import com.ex.StudentInfromationSystem.Entities.AcademicYear;
import com.ex.StudentInfromationSystem.Entities.TimetableEntry;
import com.ex.StudentInfromationSystem.Service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.List;

@RestController("/timetable")
public class TimeTableController {

    @Autowired
    private TimetableService timetableService;

    /// timetable /getDayTimeTable
    @GetMapping("/getDayTimeTable")
    public List<TimetableEntry> getTimetable(Long clasId, Long section, DayOfWeek day){

      return   timetableService.getClassDayTimetable(clasId,section,day);

    }
//
     @PostMapping("/createTimeTable")
      public String  createTimeTable(){
     //  return timetableService.createEntry(year,)// use this method and change the return type also
        return "need use service method for time table creation ";
    }
}
