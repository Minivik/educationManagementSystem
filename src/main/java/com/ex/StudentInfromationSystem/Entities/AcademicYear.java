package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.AcademicYearStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="academic_year")
@Getter
@Setter
public class AcademicYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String yearLabel;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne(optional=false)
    @JoinColumn(name="scope_id",
            foreignKey=@ForeignKey(name="fk_year_scope"))
    private AcademicScope scope;

    @Enumerated(EnumType.STRING)
    private AcademicYearStatus status;
}
