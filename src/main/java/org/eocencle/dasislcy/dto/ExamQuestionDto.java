package org.eocencle.dasislcy.dto;

import org.eocencle.dasislcy.entity.ChoiceQuestionOptionEntity;
import org.eocencle.dasislcy.entity.ExamquestionEntity;

import java.util.List;

public class ExamQuestionDto extends ExamquestionEntity {
    
    /**
     * 试题title
     */
    private String title;

    /**
     * 选项
     */
    private List<ChoiceQuestionOptionEntity> options;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChoiceQuestionOptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<ChoiceQuestionOptionEntity> options) {
        this.options = options;
    }
}
