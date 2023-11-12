package com.hxh.service;

import com.hxh.dao.StudentDao;
import com.hxh.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public List<Student> findAllStudent(){
        return studentDao.findAll();
    }

    public void addStudent(Student student){
        studentDao.add(student);
    }
}