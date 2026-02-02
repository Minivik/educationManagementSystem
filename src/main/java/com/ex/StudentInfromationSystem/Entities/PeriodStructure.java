package com.ex.StudentInfromationSystem.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "period_structure",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"scope_id"}
    )
)
@Getter
@Setter
public class PeriodStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CBSE / ICSE / KG / Secondary etc.
    @ManyToOne(optional = false)
    @JoinColumn(
        name = "scope_id",
        foreignKey = @ForeignKey(name = "fk_period_structure_scope")
    )
    private AcademicScope scope;

    @Column(nullable = false)
    private Integer periodsPerDay;

    @Column(nullable = false)
    private Integer periodDurationMinutes;

    @Column(nullable = false)
    private Boolean hasDoublePeriods;

    @Column(nullable = false)
    private Integer breakCount;

    /**
     * Simple rules:
     * {
     *   "max_consecutive_periods": 3,
     *   "lab_double_only": true
     * }
     */
    @Column(columnDefinition = "json")
    private String rulesJson;

    @OneToMany(
        mappedBy = "periodStructure",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @OrderBy("periodNumber ASC")
    private List<PeriodDefinition> periodDefinitions = new ArrayList<>();
}
//INSERT INTO period_structure
//(id, scope_id, periods_per_day, period_duration_minutes, has_double_periods, break_count, rules_json)
//VALUES
//(1, 1, 8, 45, true, 2,
// '{"max_consecutive_periods":3,"lab_double_only":true}');