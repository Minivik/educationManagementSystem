package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="student_scope_enrollment")
@Getter
@Setter
public class StudentAcademicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id",
            foreignKey=@ForeignKey(name="fk_enroll_student"))
    private Student student;

    @ManyToOne
    @JoinColumn(name="academic_year_id",
            foreignKey=@ForeignKey(name="fk_enroll_year"))
    private AcademicYear academicYear;

    @ManyToOne(optional = false)
    private ClassEntity classEntity;

    @ManyToOne(optional = false)
    private Section section;

    @ManyToOne(optional = false)
    private Subject subject;
    @Column(nullable = false)
    private Boolean frozen;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;
    //INSERT INTO student_scope_enrollment
    //(id, student_id, scope_id, academic_year_id, class_entity_id, section_id, status)
    //VALUES (1, 1, 1, 1, 1, 1, 'ENROLLED');
}
