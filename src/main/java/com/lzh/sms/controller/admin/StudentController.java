package com.lzh.sms.controller.admin;

import com.lzh.sms.entity.Student;
import com.lzh.sms.result.PageResult;
import com.lzh.sms.result.Result;
import com.lzh.sms.dto.StudentPageQueryDTO;
import com.lzh.sms.service.StudentService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 学生Controller
 */
@RestController
@RequestMapping("admin/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/page")
    public Result<PageResult> getStudentList(StudentPageQueryDTO queryDTO) throws InterruptedException {
        Thread.sleep(100);
        PageResult pageResult = studentService.pageQuery(queryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/add")
    public Result addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return Result.success();
    }

    @PutMapping("{id}")
    public Result updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(student);
        return Result.success();
    }

    @GetMapping("/export")
    public void exportStudentList(HttpServletResponse response) throws IOException {
        studentService.exportStudentList(response);
    }
}