package com.ex.StudentInfromationSystem.dto;
    public record TotalMarksResponse(
            Long studentId,
            Long examId,
            Integer totalMarks
    ) {}


