package org.eocencle.dasislcy.service.impl;

import org.eocencle.dasislcy.dao.ChoiceQuestionMapper;
import org.eocencle.dasislcy.dao.ChoiceQuestionOptionMapper;
import org.eocencle.dasislcy.dao.SubjectQuestionMapper;
import org.eocencle.dasislcy.dto.ChoiceQuestionDto;
import org.eocencle.dasislcy.entity.ChoiceQuestionOptionEntity;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;
import org.eocencle.dasislcy.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: shizh26250
 * @Date: 2019/3/29 16:10
 * @Description:
 */
@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ChoiceQuestionMapper choiceQuestionMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ChoiceQuestionOptionMapper choiceQuestionOptionMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SubjectQuestionMapper subjectQuestionMapper;

    @Override
    @Transactional
    public void addChoiceQuestion(ChoiceQuestionDto dto, SubjectQuestionEntity subjectQuestion) {
        this.choiceQuestionMapper.insertSelective(dto);

        for (ChoiceQuestionOptionEntity option: dto.getOptions()) {
            option.setQuestionId(dto.getId());
            this.choiceQuestionOptionMapper.insertSelective(option);
        }

        subjectQuestion.setQuestionId(dto.getId());
        this.subjectQuestionMapper.insertSelective(subjectQuestion);
    }

}
