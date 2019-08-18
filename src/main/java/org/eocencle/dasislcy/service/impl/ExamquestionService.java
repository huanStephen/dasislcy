package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.ChoiceQuestionOptionMapper;
import org.eocencle.dasislcy.dao.ExamquestionMapper;
import org.eocencle.dasislcy.dto.ExamQuestionDto;
import org.eocencle.dasislcy.entity.ChoiceQuestionOptionEntity;
import org.eocencle.dasislcy.entity.ExampaperEntity;
import org.eocencle.dasislcy.entity.ExamquestionEntity;
import org.eocencle.dasislcy.service.IExamquestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 试题service
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class ExamquestionService implements IExamquestionService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ExamquestionMapper examquestionMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ChoiceQuestionOptionMapper choiceQuestionOptionMapper;

    @Override
    public ExamquestionEntity getExamquestionById(Integer id) {
        return this.examquestionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addExamquestion(ExamquestionEntity examquestion) {
        Integer maxSort = this.examquestionMapper.getExamquestionMaxSort(examquestion.getExampaperId());
        int sort = 1;
        if (null != maxSort) {
            sort = maxSort + 1;
        }
        examquestion.setSort(sort);
        examquestion.setQuestionType(1);
        this.examquestionMapper.insertSelective(examquestion);
    }

    @Override
    @Transactional
    public void addExamquestions(List<ExamquestionEntity> examquestions) {
        Integer maxSort = this.examquestionMapper.getExamquestionMaxSort(examquestions.get(0).getExampaperId());
        int sort = 0;
        if (null != maxSort) {
            sort = maxSort;
        }
        for (ExamquestionEntity examquestion: examquestions) {
            sort ++;
            examquestion.setSort(sort);
            examquestion.setQuestionType(1);
            this.examquestionMapper.insertSelective(examquestion);
        }
    }

    @Override
    public void removeExamquestionById(Integer id) {
        this.examquestionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateExamquestion(ExamquestionEntity examquestion) {
        this.examquestionMapper.updateByPrimaryKeySelective(examquestion);
    }

    @Override
    public PageAdapter<ExamQuestionDto> getExamquestionByExampaperId(Integer exampaperId, PageAdapter<ExamQuestionDto> page) {
        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<ExamQuestionDto> list = this.examquestionMapper.getExamQuestions(exampaperId);
        PageInfo<ExamQuestionDto> info = new PageInfo<ExamQuestionDto>(list);

        for (ExamQuestionDto question: list) {
            Example example = new Example(ChoiceQuestionOptionEntity.class);
            example.createCriteria().andEqualTo("questionId", question.getQuestionId());
            example.orderBy("sort").asc();
            question.setOptions(this.choiceQuestionOptionMapper.selectByExample(example));
        }
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }
}
