package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.ExamType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="exam")
@Getter
@Setter
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @ManyToOne
    @JoinColumn(name="academic_year_id",
            foreignKey=@ForeignKey(name="fk_exam_year"))
    private AcademicYear academicYear;
}
