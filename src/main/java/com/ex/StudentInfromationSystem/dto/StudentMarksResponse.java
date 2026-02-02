package com.ex.StudentInfromationSystem.dto;
import java.util.List;

public record StudentMarksResponse(
        Long studentId,
        String studentName,
        List<SubjectMarksResponse> subjectMarks,
        Integer totalMarks
) {}
