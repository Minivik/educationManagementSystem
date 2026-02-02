package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.PeriodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(
    name = "period_definition",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"period_structure_id", "period_number"}
    )
)
@Getter
@Setter
public class PeriodDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(
        name = "period_structure_id",
        foreignKey = @ForeignKey(name = "fk_period_def_structure")
    )
    private PeriodStructure periodStructure;

    @Column(nullable = false)
    private Integer periodNumber;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeriodType periodType;

    @Column(nullable = false)
    private String  subjectPeriod;
}

//INSERT INTO period_definition
//(id, period_structure_id, period_number, start_time, end_time, period_type)
//VALUES
//(1, 1, 1, '09:00', '09:45', 'TEACHING' ,subj1),
//(2, 1, 2, '09:45', '10:30', 'TEACHING',subject2),
//(3, 1, 3, '10:30', '11:15', 'BREAK',subject3),
//(4, 1, 4, '11:15', '12:00', 'TEACHING',subjecr3),
//(5, 1, 5, '12:00', '12:45', 'LAB',subject 2);

