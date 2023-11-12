package com.hxh.controller;

import com.hxh.domain.Student;
import com.hxh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/all")
    public String all(Model model){
        List<Student> allStudent = studentService.findAllStudent();
        model.addAttribute("students",allStudent);
        return "allStudent";
    }

    @RequestMapping("/add")
    public String add(Student student){
        studentService.addStudent(student);
        // 重定向到查询所有学生
        return "redirect:/student/all";
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        // 重定向到查询所有学生
        return "test";
    }

}