package org.eocencle.dasislcy.dto;

import org.eocencle.dasislcy.entity.ChoiceQuestionEntity;
import org.eocencle.dasislcy.entity.ChoiceQuestionOptionEntity;
import java.util.List;

/**
 * 选择题DTO
 * @Auther: huanStephen
 * @Date: 2019/4/10
 * @Description:
 */
public class ChoiceQuestionDto extends ChoiceQuestionEntity {

    public ChoiceQuestionDto() {

    }

    public ChoiceQuestionDto(ChoiceQuestionEntity entity) {
        this.setType(entity.getType());
        this.setTitle(entity.getTitle());
        this.setCreateTime(entity.getCreateTime());
        this.setUpdateTime(entity.getUpdateTime());
    }

    private List<ChoiceQuestionOptionEntity> options;

    public List<ChoiceQuestionOptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<ChoiceQuestionOptionEntity> options) {
        this.options = options;
    }
}
