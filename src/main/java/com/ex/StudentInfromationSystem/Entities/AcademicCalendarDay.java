package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.DayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="academic_calendar_day",
        uniqueConstraints=@UniqueConstraint(
                columnNames={"academic_year_id","calendar_date"}))
@Getter
@Setter
public class AcademicCalendarDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="academic_year_id",
            foreignKey=@ForeignKey(name="fk_calendar_year"))
    private AcademicYear academicYear;

    @Column(name="calendar_date")
    private LocalDate calendarDate;

    @Enumerated(EnumType.STRING)
    private DayType dayType;

    private String description;
}
