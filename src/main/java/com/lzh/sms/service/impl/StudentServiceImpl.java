package com.lzh.sms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzh.sms.context.BaseContext;
import com.lzh.sms.dto.StudentPageQueryDTO;
import com.lzh.sms.entity.Student;
import com.lzh.sms.mapper.StudentMapper;
import com.lzh.sms.result.PageResult;
import com.lzh.sms.service.StudentService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 学生Service实现类
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public PageResult pageQuery(StudentPageQueryDTO queryDTO) {
        // 获取当前登录用户的ID
        Long userId = BaseContext.getCurrentId();
        
        // 设置分页参数
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());
        
        // 执行查询，传入当前用户ID确保只查询该用户的学生
        queryDTO.setUserId(userId);
        List<Student> students = studentMapper.list(queryDTO);
        
        // 转换为Page对象以获取分页数据
        Page<Student> page = (Page<Student>) students;
        
        // 封装结果并返回
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void addStudent(Student student) {
        // 获取当前登录用户的ID
        Long userId = BaseContext.getCurrentId();

        // 设置当前用户ID
        student.setUserId(userId);

        // 执行插入
        studentMapper.insert(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentMapper.delete(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void exportStudentList(HttpServletResponse response) throws IOException {
        List<Student> list = studentMapper.list(StudentPageQueryDTO.builder().userId(BaseContext.getCurrentId()).build());

        // 在内存中创建一个excel文件
        XSSFWorkbook excel = new XSSFWorkbook();
        // 在excel文件中创建一个sheet页
        XSSFSheet sheet = excel.createSheet("info");
        // 在Sheet中创建行对象, rownum编号从0开始
        XSSFRow row = sheet.createRow(0);
        // 设置表头内容
        String[] headers = {
            "学生姓名",
            "性别",
            "出生日期",
            "联系电话",
            "电子邮箱",
            "所属班级",
            "入学日期",
            "家庭住址"
        };
        // 创建表头单元格并设置内容
        for (int i = 0; i < headers.length; i++) {
            row.createCell(i).setCellValue(headers[i]);
        }

        // 填充数据
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(student.getName());
            row.createCell(1).setCellValue(student.getGender());
            row.createCell(2).setCellValue(String.valueOf(student.getBirthdate()));
            row.createCell(3).setCellValue(student.getPhone());
            row.createCell(4).setCellValue(student.getEmail());
            row.createCell(5).setCellValue(student.getClassName());
            row.createCell(6).setCellValue(String.valueOf(student.getAdmissionDate()));
            row.createCell(7).setCellValue(student.getAddress());
        }

        for (int i = 0; i < headers.length; i++) {
//            sheet.autoSizeColumn(i); // 这一句在docker环境中会引发空指针异常
            sheet.setColumnWidth(i,sheet.getColumnWidth(i)*17/10);
        }

        ServletOutputStream out = response.getOutputStream();
        excel.write(out);

        // 关闭资源
        out.close();
        excel.close();
    }
}
