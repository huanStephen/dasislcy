package org.eocencle.dasislcy.factory;

import org.eocencle.dasislcy.service.IChoiceQuestionService;
import org.eocencle.dasislcy.service.IQuestionService;
import org.eocencle.dasislcy.service.impl.ChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * 试题类型工厂
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
@Component
public class QuestionFactory {

    @Autowired
    private IChoiceQuestionService choiceQuestionService;

    public IQuestionService getQuestionMap(Integer type) {
        if (1 == type) {
            return null;
        } else {
            return null;
        }
    }

}
