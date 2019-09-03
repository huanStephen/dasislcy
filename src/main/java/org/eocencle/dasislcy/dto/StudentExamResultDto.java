package org.eocencle.dasislcy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eocencle.dasislcy.entity.StudentExamResultEntity;

import java.util.Date;

/**
 * Author: Xukai
 * Description: 学生试卷成绩DTO
 * CreateDate: 2019/9/1 16:04
 * Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamResultDto {

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 试卷ID
     */
    private Integer exampaperId;

    /**
     * 题目总数
     */
    private Integer questionCount;

    /**
     * 试卷总分
     */
    private Integer totalScore;

    /**
     * 正确得分
     */
    private Integer correctScore;

    /**
     * 正确答案数量
     */
    private Integer correctCount;

    /**
     * 正确率
     */
    private String correctRatio;

    /**
     * 创建时间
     */
    private Date createTime;

    public void getParmsFromEntity(StudentExamResultEntity studentExamResultEntity) {
        this.studentId = studentExamResultEntity.getStudentId();
        this.exampaperId = studentExamResultEntity.getExampaperId();
        this.createTime = studentExamResultEntity.getCreateTime();
    }

}
