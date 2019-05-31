package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.ChoiceQuestionMapper;
import org.eocencle.dasislcy.dao.ChoiceQuestionOptionMapper;
import org.eocencle.dasislcy.dao.QuestionOutlineMapper;
import org.eocencle.dasislcy.dao.SubjectQuestionMapper;
import org.eocencle.dasislcy.dto.ChoiceQuestionDto;
import org.eocencle.dasislcy.entity.ChoiceQuestionOptionEntity;
import org.eocencle.dasislcy.entity.QuestionOutlineEntity;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;
import org.eocencle.dasislcy.service.IChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 选择题service
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class ChoiceQuestionService implements IChoiceQuestionService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ChoiceQuestionMapper choiceQuestionMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ChoiceQuestionOptionMapper choiceQuestionOptionMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SubjectQuestionMapper subjectQuestionMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private QuestionOutlineMapper questionOutlineMapper;

    @Override
    public ChoiceQuestionDto getChoiceQuestionById(Integer id) {
        ChoiceQuestionDto dto = new ChoiceQuestionDto(this.choiceQuestionMapper.selectByPrimaryKey(id));

        ChoiceQuestionOptionEntity record = new ChoiceQuestionOptionEntity();
        record.setQuestionId(id);
        dto.setOptions(this.choiceQuestionOptionMapper.select(record));

        return dto;
    }

    @Override
    @Transactional
    public void addChoiceQuestion(ChoiceQuestionDto choiceQuestion, SubjectQuestionEntity subjectQuestion) {
        this.choiceQuestionMapper.insertSelective(choiceQuestion);

        for (ChoiceQuestionOptionEntity option: choiceQuestion.getOptions()) {
            option.setQuestionId(choiceQuestion.getId());
            this.choiceQuestionOptionMapper.insertSelective(option);
        }

        subjectQuestion.setQuestionId(choiceQuestion.getId());
        this.subjectQuestionMapper.insertSelective(subjectQuestion);
    }

    @Override
    @Transactional
    public void removeChoiceQuestionById(Integer id) {
        this.choiceQuestionMapper.deleteByPrimaryKey(id);

        ChoiceQuestionOptionEntity record = new ChoiceQuestionOptionEntity();
        record.setQuestionId(id);

        this.choiceQuestionOptionMapper.delete(record);
    }

    @Override
    @Transactional
    public void updateSubject(ChoiceQuestionDto choiceQuestion) {
        this.choiceQuestionMapper.updateByPrimaryKey(choiceQuestion);

        for (ChoiceQuestionOptionEntity option: choiceQuestion.getOptions()) {
            this.choiceQuestionOptionMapper.updateByPrimaryKeySelective(option);
        }
    }

    /**
     * 这个方法获取所有题型不应该放在选择题里
     * @param outlineIds
     * @return
     */
    @Override
    public PageAdapter<ChoiceQuestionDto> getChoiceQuestionsByOutlineIds(List<Integer> outlineIds) {
        if (null == outlineIds || 0 == outlineIds.size()) {
            return null;
        }

        Example example = new Example(QuestionOutlineEntity.class);
        example.createCriteria().andIn("outlineId", outlineIds);
        List<QuestionOutlineEntity> questions = this.questionOutlineMapper.selectByExample(example);

        if (null == questions || 0 == questions.size()) {
            return null;
        }


        return null;
    }

    @Override
    public PageAdapter<ChoiceQuestionDto> getChoiceQuestionsBySubjectId(Integer subjectId, PageAdapter<ChoiceQuestionDto> page) {
        if (null == subjectId || 0 == subjectId) {
            return null;
        }

        SubjectQuestionEntity sqRecord = new SubjectQuestionEntity();
        sqRecord.setSubjectId(subjectId);

        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<SubjectQuestionEntity> list = this.subjectQuestionMapper.select(sqRecord);
        PageInfo<SubjectQuestionEntity> info = new PageInfo<SubjectQuestionEntity>(list);

//        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return null;
    }
}
