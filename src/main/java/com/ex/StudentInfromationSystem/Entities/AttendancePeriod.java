package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "attendance_period",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_att_period",
                        columnNames = {"attendance_record_id", "periodNo"})})
@Getter
@Setter
@AllArgsConstructor
public class AttendancePeriod// extends AuditableEntity
{

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "attendance_record_id",
            foreignKey = @ForeignKey(name = "fk_period_att")
    )
    private AttendanceRecord attendanceRecord;

    @Column(nullable = false)
    private Integer periodNo;   // 1,2,3...

    @ManyToOne
    @JoinColumn(
            name = "subject_id",
            foreignKey = @ForeignKey(name = "fk_att_subject")
    )
    private Subject subject;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status;

    @Id
    private Integer id;
}
