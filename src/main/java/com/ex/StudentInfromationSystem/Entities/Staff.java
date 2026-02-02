package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.StaffRoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeCode;
    private String name;

    @Enumerated(EnumType.STRING)
    private StaffRoleType roleType;
}
