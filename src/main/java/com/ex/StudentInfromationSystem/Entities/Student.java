package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.StudentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="student")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String admissionNo;

//    @OneToMany(optional=false)
//    @JoinColumn(name="institution_id",
//            foreignKey=@ForeignKey(name="fk_scope_institution"))
//    private Institution institution;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
