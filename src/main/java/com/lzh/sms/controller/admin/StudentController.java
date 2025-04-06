package com.lzh.sms.controller.admin;

import com.lzh.sms.entity.Student;
import com.lzh.sms.result.PageResult;
import com.lzh.sms.result.Result;
import com.lzh.sms.dto.StudentPageQueryDTO;
import com.lzh.sms.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "学生信息相关接口")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> getStudentList(StudentPageQueryDTO queryDTO) throws InterruptedException {
        Thread.sleep(100);
        PageResult pageResult = studentService.pageQuery(queryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/add")
    @ApiOperation("添加学生")
    public Result addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除学生")
    public Result deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return Result.success();
    }

    @PutMapping("{id}")
    @ApiOperation("更新学生")
    public Result updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(student);
        return Result.success();
    }

    @GetMapping("/export")
    @ApiOperation("导出学生")
    public void exportStudentList(HttpServletResponse response) throws IOException {
        studentService.exportStudentList(response);
    }
}