package org.eocencle.dasislcy.dto;

import org.eocencle.dasislcy.entity.SubjectQuestionEntity;

public class SubjectQuestionRelationDto extends SubjectQuestionEntity {
    // 科目名称
    private String subjectName;
    // 章节名称
    private String chapterName;
    // 大纲名称
    private String outlineName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getOutlineName() {
        return outlineName;
    }

    public void setOutlineName(String outlineName) {
        this.outlineName = outlineName;
    }
}
