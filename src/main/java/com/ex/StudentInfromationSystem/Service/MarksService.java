package com.ex.StudentInfromationSystem.Service;

import com.ex.StudentInfromationSystem.Entities.MarksEntry;
import com.ex.StudentInfromationSystem.Entities.StudentAcademicRecord;
import com.ex.StudentInfromationSystem.Entities.Subject;
import com.ex.StudentInfromationSystem.Enums.EnrollmentStatus;
import com.ex.StudentInfromationSystem.Enums.MarksStatus;
import com.ex.StudentInfromationSystem.Repository.MarksRepository;
import com.ex.StudentInfromationSystem.Repository.StudentScopeEnrollmentRepo;
import com.ex.StudentInfromationSystem.Repository.SubjectRepository;
import com.ex.StudentInfromationSystem.dto.StudentMarksResponse;
import com.ex.StudentInfromationSystem.dto.SubjectMarksResponse;
import com.ex.StudentInfromationSystem.dto.TotalMarksResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MarksService {

    private final StudentScopeEnrollmentRepo enrollmentRepo;
    private final MarksRepository marksRepo;
    private final SubjectRepository subjectRepo;

    public MarksService(StudentScopeEnrollmentRepo academicRepo, MarksRepository marksRepo, SubjectRepository subjectRepo) {
        this.enrollmentRepo = academicRepo;
        this.marksRepo = marksRepo;
        this.subjectRepo = subjectRepo;
    }


    public void enterSubjectMarks(Long studentId,
                                  Long examId,
                                  Long subjectId,
                                  Integer marks , MarksStatus status) {

        StudentAcademicRecord record =
                enrollmentRepo.findByStudentIdAndAcademicStatus(
                        studentId, EnrollmentStatus.ACTIVE
                ).orElseThrow();

        if (record.getFrozen()) {
            throw new RuntimeException("Academic year frozen");
        }

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow();

        marksRepo.save(new MarksEntry(record, examId, subject, marks ,status));

    }

    public List<SubjectMarksResponse>
    getSubjectWiseMarks(Long studentId, Long examId) {

        StudentAcademicRecord record =
                enrollmentRepo.findByStudentIdAndAcademicStatus(
                        studentId,  EnrollmentStatus.ACTIVE
                ).orElseThrow();

        return marksRepo
                .findByAcademicRecordIdAndExamId(record.getId(), examId)
                .stream()
                .map(m -> new SubjectMarksResponse(
                        m.getSubject().getName(),
                        m.getMarks()
                ))
                .toList();
    }
    public TotalMarksResponse getTotalMarks(
            Long studentId, Long examId) {

        StudentAcademicRecord record =
                enrollmentRepo.findByStudentIdAndAcademicStatus(
                        studentId, EnrollmentStatus.ACTIVE
                ).orElseThrow();

        int total =
                marksRepo
                        .findByAcademicRecordIdAndExamId(record.getId(), examId)
                        .stream()
                        .mapToInt(MarksEntry::getMarks)
                        .sum();

        return new TotalMarksResponse(
                studentId, examId, total
        );


}
    public List<StudentMarksResponse>
    getAllStudentsMarksForExam(Long examId) {

        List<MarksEntry> marks = marksRepo.findByExamId(examId);

        // Group by Academic Record (i.e., one student per year)
        Map<StudentAcademicRecord, List<MarksEntry>> grouped =
                marks.stream()
                        .collect(Collectors.groupingBy(
                                MarksEntry::getRecordStudent
                        ));
        List<StudentMarksResponse> response = new ArrayList<>();
        for (Map.Entry<StudentAcademicRecord, List<MarksEntry>> entry
                : grouped.entrySet()) {

            StudentAcademicRecord record = entry.getKey();
            List<MarksEntry> studentMarks = entry.getValue();

            List<SubjectMarksResponse> subjects =
                    studentMarks.stream()
                            .map(m -> new SubjectMarksResponse(
                                    m.getSubject().getName(),
                                    m.getMarks()
                            ))
                            .toList();

            int total =
                    studentMarks.stream()
                            .mapToInt(MarksEntry::getMarks)
                            .sum();

            response.add(new StudentMarksResponse(
                    record.getStudent().getId(),
                    record.getStudent().getFullName(),
                    subjects,
                    total
            ));
        }

        return response;
    }

}
