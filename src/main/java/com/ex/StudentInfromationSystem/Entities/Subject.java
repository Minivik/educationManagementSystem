package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.SubjectType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @ManyToOne(optional = false)
    private AcademicScope scope;
}
