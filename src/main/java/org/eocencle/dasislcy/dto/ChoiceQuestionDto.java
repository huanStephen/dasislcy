package org.eocencle.dasislcy.dto;

import org.eocencle.dasislcy.entity.ChoiceQuestionEntity;
import org.eocencle.dasislcy.entity.ChoiceQuestionOptionEntity;
import java.util.List;

/**
 * @Auther: shizh26250
 * @Date: 2019/4/10 13:57
 * @Description:
 */
public class ChoiceQuestionDto extends ChoiceQuestionEntity {

    private List<ChoiceQuestionOptionEntity> options;

    public List<ChoiceQuestionOptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<ChoiceQuestionOptionEntity> options) {
        this.options = options;
    }
}
