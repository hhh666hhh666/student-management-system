package com.lzh.sms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 学生实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 性别(0未知 1男 2女)
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private LocalDate birthdate;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 所属班级
     */
    private String className;

    /**
     * 入学日期
     */
    private LocalDate admissionDate;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 