package com.talenttrek.education.ui;


import com.talenttrek.education.model.Student;
import com.talenttrek.education.service.StudentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Route("") // Root URL
public class StudentView extends VerticalLayout {

    private final StudentService studentService;
    private final Grid<Student> grid = new Grid<>(Student.class);

    @Autowired
    public StudentView(StudentService studentService) {
        this.studentService = studentService;
        List<Student> students = studentService.getAllStudents();

        add(grid);
        grid.setItems(students); // Use your existing service
        grid.setColumns("id", "firstName", "email"); // Show only relevant columns

        TextField nameField = new TextField("firstName");
        TextField emailField = new TextField("Email");
        Button saveButton = new Button("Save");

        FormLayout form = new FormLayout();
        form.add(nameField, emailField, saveButton);

        saveButton.addClickListener(e -> {
            Student student = new Student();
            student.setFirstName(nameField.getValue());
            student.setEmail(emailField.getValue());
            studentService.saveStudent(student);
            grid.setItems(studentService.getAllStudents()); // Refresh grid
        });


    }
}

