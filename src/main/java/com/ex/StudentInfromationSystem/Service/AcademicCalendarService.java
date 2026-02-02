package com.ex.StudentInfromationSystem.Service;

import com.ex.StudentInfromationSystem.Entities.AcademicCalendarDay;
import com.ex.StudentInfromationSystem.Entities.AcademicYear;
import com.ex.StudentInfromationSystem.Enums.DayType;
import com.ex.StudentInfromationSystem.Repository.AcademicCalendarRepository;
import com.ex.StudentInfromationSystem.Repository.AcademicYearRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AcademicCalendarService {

    private final AcademicCalendarRepository calendarRepository;
   private final AcademicYearRepository yearRepository;

    public void addDay(AcademicCalendarDay d){
        calendarRepository.save(d);
    }

    public List<AcademicCalendarDay> list(){
        return calendarRepository.findAll();
    }
//INSERT INTO academic_calendar_day
//(academic_year_id, calendar_date, day_type, description)
//VALUES (1, '2024-06-15', 'HOLIDAY', 'Bakrid');
    // insertin with below values
        public void  createCalendarDay(
                Long academicYearId,
                LocalDate date,
                DayType dayType,
                String description) {

            AcademicYear year = yearRepository.findById(academicYearId)
                    .orElseThrow(() ->
                            new NotFoundException("Academic year not found"));

          calendarRepository.findByAcademicYearIdAndCalendarDate(
                            academicYearId, date)
                    .ifPresent(d -> {
                        throw new RuntimeException(
                                "Calendar day already exists for this date");
                    });

            AcademicCalendarDay day = new AcademicCalendarDay();
            day.setAcademicYear(year);
            day.setCalendarDate(date);
            day.setDayType(dayType);
            day.setDescription(description);
            calendarRepository.save(day);

        }

        //  UPDATE (by ID)UPDATE academic_calendar_day
        //SET day_type = 'WORKING', description = 'Rescheduled'
        //WHERE academic_year_id = 1
        //AND calendar_date = '2024-06-15';

        //  UPSERT (CREATE OR UPDATE)
        public AcademicCalendarDay createOrUpdateDay(
                Long academicYearId,
                LocalDate date,
                DayType dayType,
                String description) {

            AcademicYear year = yearRepository.findById(academicYearId)
                    .orElseThrow(() ->
                            new RuntimeException("Academic year not found"));

            AcademicCalendarDay day = calendarRepository
                    .findByAcademicYearIdAndCalendarDate(
                            academicYearId, date)
                    .orElse(new AcademicCalendarDay());

            day.setAcademicYear(year);
            day.setCalendarDate(date);
            day.setDayType(dayType);
            day.setDescription(description);

            return calendarRepository.save(day);
        }

        //  BULK CREATE (DATE RANGE)
        public void createRange(
                Long academicYearId,
                LocalDate startDate,
                LocalDate endDate,
                DayType defaultDayType)throws RuntimeException {

            AcademicYear year = yearRepository.findById(academicYearId)
                    .orElseThrow(() ->
                            new RuntimeException("Academic year not found"));

            LocalDate date = startDate;

            while (!date.isAfter(endDate)) {

                LocalDate finalDate = date;
                calendarRepository
                        .findByAcademicYearIdAndCalendarDate(
                                academicYearId, date)
                        .orElseGet(() -> {

                            AcademicCalendarDay day = new AcademicCalendarDay();
                            day.setAcademicYear(year);
                            day.setCalendarDate(finalDate);
                            //set day week week off
                            //els eweek off
                            day.setDayType(defaultDayType);

                            return calendarRepository.save(day);
                        });

                date = date.plusDays(1);
            }
        }

        public  List<AcademicCalendarDay>  getCalendarByYear(Long year){

            return calendarRepository.findByAcademicYearId(year);
        }

    }



