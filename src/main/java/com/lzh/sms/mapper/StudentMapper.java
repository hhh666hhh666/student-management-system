package com.lzh.sms.mapper;

import com.lzh.sms.dto.StudentPageQueryDTO;
import com.lzh.sms.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 学生Mapper接口
 */
@Mapper
public interface StudentMapper {


    List<Student> list(StudentPageQueryDTO queryDTO);

    void insert(Student student);

    void delete(Long id);

    void update(Student student);
} 