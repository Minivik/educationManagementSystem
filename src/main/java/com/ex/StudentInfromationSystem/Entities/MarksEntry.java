package com.ex.StudentInfromationSystem.Entities;
import com.ex.StudentInfromationSystem.Enums.MarksStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="marks_entry")
@Getter @Setter
public class MarksEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_scope_enrollment_id",
            foreignKey=@ForeignKey(name="fk_marks_student"))
    private StudentAcademicRecord recordStudent;

    @JoinColumn(name="exam_id")
    private Long exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private Integer marks;

    @Enumerated(EnumType.STRING)
    private MarksStatus status;
    public MarksEntry(StudentAcademicRecord record,
                      Long examId,
                      Subject subject,
                      Integer marks, MarksStatus status) {
        this.recordStudent = record;
        this.exam = examId;
        this.subject = subject;
        this.marks = marks;
        this.status=status;
    }

}
