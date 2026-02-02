package com.ex.StudentInfromationSystem.Entities;

import com.ex.StudentInfromationSystem.Enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "attendance_record",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_att_student_day",
                        columnNames = {"student_id", "attendance_date"}
                )
        }
)
@Getter @Setter
public class AttendanceRecord //extends AuditableEntity
{

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(name = "fk_att_student")
    )
    private Student student;

    @Column(nullable = false)
    private LocalDate attendanceDate;

//    @OneToMany(
//            mappedBy = "attendanceRecord",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//   private List<AttendancePeriod> periods = new ArrayList<>();

@Enumerated(EnumType.STRING)
@Column(nullable = false)
private AttendanceStatus status;

    public AttendanceRecord(Student academicRecordId, LocalDate date, AttendanceStatus status) {
        this.student = academicRecordId;
        this.attendanceDate = date;
        this.status = status;
    }

    //-- Daily record
    //INSERT INTO attendance_record (id, student_id, attendance_date)
    //VALUES (1, 1, '2024-06-01');INSERT INTO attendance_period
    //(id, attendance_record_id, period_no, subject_id, status)
    //VALUES
    //(1, 1, 1, 1, 'PRESENT'),
    //(2, 1, 2, 1, 'PRESENT'),
    //(3, 1, 3, 1, 'ABSENT');

    @Id
    private Integer id;
}
