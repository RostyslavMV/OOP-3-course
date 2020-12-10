package com.rmv.oop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.rmv.oop.Student;
import com.rmv.oop.StudentsGroup;

@Controller
public class MainController {

    @GetMapping("/form")
    public String openForm() {
        return "form";
    }

    @GetMapping("/list")
    public String openList(Model model) {
        model.addAttribute("students", StudentsGroup.students);
        return "list";
    }

    @PostMapping("/submit")
    @ResponseBody
    public String posted(Student student) {
        System.out.println("Post");
        System.out.println(student.getName());
        System.out.println(student.getSurName());
        System.out.println(student.getAge());
        if (!student.getName().equals("") && !student.getSurName().equals(""))
            StudentsGroup.students.add(student);
        return "Ok";
    }
}
