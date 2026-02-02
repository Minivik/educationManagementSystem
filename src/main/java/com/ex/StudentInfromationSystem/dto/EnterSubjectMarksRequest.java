package com.ex.StudentInfromationSystem.dto;

import com.ex.StudentInfromationSystem.Enums.MarksStatus;
import lombok.Data;

@Data
public class EnterSubjectMarksRequest {
    private   Long studentId;
    private   Long examId;
    private   Long subjectId;
    private   Integer marks;
    private MarksStatus marksStatus;
}
