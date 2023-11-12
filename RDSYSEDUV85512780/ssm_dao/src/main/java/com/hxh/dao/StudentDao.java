package com.hxh.dao;

import com.hxh.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {
    // 查询所有学生
    @Select("select * from student")
    List<Student> findAll();

    // 添加学生
    @Insert("insert into student values(null,#{name},#{sex},#{address})")
    void add(Student student);
}