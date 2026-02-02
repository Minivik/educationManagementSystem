package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.BoardType;
import com.ex.StudentInfromationSystem.Enums.EducationLevel;
import com.ex.StudentInfromationSystem.Enums.ScopeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "academic_scope")
@Getter
@Setter
public class AcademicScope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="institution_id",
            foreignKey=@ForeignKey(name="fk_scope_institution"))
    private Institution institution;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;// need to change for list of boards

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private ScopeStatus status;
}
