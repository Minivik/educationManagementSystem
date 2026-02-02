package com.ex.StudentInfromationSystem.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;

@Entity
@Table(
    name = "timetable_entry",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {
            "academic_year_id",
            "class_entity_id",
            "section_id",
            "day_of_week",
            "period_definition_id"
        }
    )
)
@Getter
@Setter
public class TimetableEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Academic context
    @ManyToOne(optional = false)
    private AcademicYear academicYear;

    @ManyToOne(optional = false)
    private ClassEntity classEntity;

    @ManyToOne(optional = false)
    private Section section;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    // Period slot
    @ManyToOne(optional = false)
    private PeriodDefinition periodDefinition;

    // Teaching assignment
    @ManyToOne(optional = false)
    private Subject subject;

   @ManyToOne(optional = false)
   private Staff teacher;
}

//-- Timetable
//INSERT INTO timetable_entry
//        (id, academic_year_id, class_entity_id, section_id, day_of_week,
//         period_definition_id, subject_id, teacher_id)
//VALUES
//        (1, 1, 1, 1, 'MONDAY', 1, 1, 2);
//
//-- Teacher availability
//INSERT INTO teacher_availability
//        (id, teacher_id, day_of_week, period_definition_id, available)
//VALUES
//        (1, 2, 'MONDAY', 1, true),
//(2, 2, 'MONDAY', 2, false);

