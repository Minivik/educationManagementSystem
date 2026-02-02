package com.ex.StudentInfromationSystem.Controller;


import com.ex.StudentInfromationSystem.Service.MarksService;
import com.ex.StudentInfromationSystem.dto.EnterSubjectMarksRequest;
import com.ex.StudentInfromationSystem.dto.StudentMarksResponse;
import com.ex.StudentInfromationSystem.dto.SubjectMarksResponse;
import com.ex.StudentInfromationSystem.dto.TotalMarksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class StudentMarksController {

    @Autowired
    private MarksService examService;

    @PostMapping("/enterMarks")
//    @PreAuthorized("hasRole("Teacher,Admin,Principle")
    public ResponseEntity<Void> enterSubjectMarks(@RequestBody EnterSubjectMarksRequest request) {

        examService.enterSubjectMarks(
                request.getStudentId(),
                request.getExamId(),
                request.getSubjectId(),
                request.getMarks(),request.getMarksStatus()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}/exam/{examId}")
    //Preauthorized("admin""principle","admin")
    public  ResponseEntity<List<SubjectMarksResponse>>
    getSubjectWiseMarks(
            @PathVariable Long studentId,
            @PathVariable Long examId) {

        return ResponseEntity.ok(
                examService.getSubjectWiseMarks(studentId, examId));

    }
// total marks from Student  by exam ID
    @GetMapping("/student/{studentId}/exam/{examId}/total")
    //Preauthorized("admin""principle","admin")
    public ResponseEntity<TotalMarksResponse>
    getTotalMarks(
            @PathVariable Long studentId,
            @PathVariable Long examId) {

        return ResponseEntity.ok(
                examService.getTotalMarks(studentId, examId)
        );
    }

//which return all students marks subject views and total from exam id
    @GetMapping("/exam/{examId}")
    //@Preauthorized("Principle")
    public List<StudentMarksResponse> getAllStudentsMarks(@PathVariable Long examId) {
        return  examService.getAllStudentsMarksForExam(examId);
    }
}
