package org.eocencle.dasislcy.dto;

import lombok.Data;

import java.util.List;

/**
 * Author: Xukai
 * Description: 学生能力概况
 * CreateDate: 2019/9/3 21:28
 * Modified By:
 */
@Data
public class StudentAbilityDto {

    /**
     * 学生ID
     */
    private Integer studentId;


    /**
     * 所有考试
     */
    private List<StudentExamResultDto> exams;
}
