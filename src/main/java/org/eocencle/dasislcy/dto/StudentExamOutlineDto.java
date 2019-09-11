package org.eocencle.dasislcy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eocencle.dasislcy.entity.StudentExamOutlineEntity;

/**
 * Author: Xukai
 * Description:
 * CreateDate: 2019/9/10 22:15
 * Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamOutlineDto {

    /**
     * 科目ID
     */
    private Integer subjectId;

    /**
     * 章节ID
     */
    private Integer chapterId;

    /**
     * 大纲ID
     */
    private Integer outlineId;

    /**
     * 应得总计
     */
    private Integer countDeserved;

    /**
     * 实得总计
     */
    private Integer countActually;

    /**
     * 应得与实得比例
     */
    private String ratio;

    public StudentExamOutlineDto(StudentExamOutlineEntity studentExamOutlineEntity) {
        this.subjectId = studentExamOutlineEntity.getSubjectId();
        this.chapterId = studentExamOutlineEntity.getChapterId();
        this.outlineId = studentExamOutlineEntity.getOutlineId();
    }

}
