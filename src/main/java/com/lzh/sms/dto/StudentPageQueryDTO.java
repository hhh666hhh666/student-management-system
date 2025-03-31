package com.lzh.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生查询参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentPageQueryDTO {
    /**
     * 学生姓名(模糊查询)
     */
    private String name;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 性别(0:未知,1:男,2:女)
     */
    private Integer gender;

    /**
     * 页码(默认1)
     */
    private Integer page = 1;

    /**
     * 每页条数(默认10)
     */
    private Integer pageSize = 10;

    /**
     * 用户ID
     */
    private Long userId;
} 