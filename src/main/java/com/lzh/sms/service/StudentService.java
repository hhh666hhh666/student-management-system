package com.lzh.sms.service;

import com.lzh.sms.dto.StudentPageQueryDTO;
import com.lzh.sms.entity.Student;
import com.lzh.sms.result.PageResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 学生Service接口
 */
public interface StudentService {


    PageResult pageQuery(StudentPageQueryDTO queryDTO);

    void addStudent(Student student);

    void deleteStudent(Long id);

    void updateStudent(Student student);

    void exportStudentList(HttpServletResponse response) throws IOException;
}