package com.talenttrek.education.controller;

import com.talenttrek.education.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")  // for HTML pages
public class StudentController {

	private final StudentService studentService;



    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/test")
	public String studentsPage(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students"; // Thymeleaf template
	}
}
