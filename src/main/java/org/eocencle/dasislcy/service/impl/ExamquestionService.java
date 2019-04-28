package org.eocencle.dasislcy.service.impl;

import org.eocencle.dasislcy.dao.ExamquestionMapper;
import org.eocencle.dasislcy.entity.ExamquestionEntity;
import org.eocencle.dasislcy.service.IExamquestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ExamquestionEntity getExamquestionById(Integer id) {
        return this.examquestionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addExamquestion(ExamquestionEntity examquestion) {
        this.examquestionMapper.insertSelective(examquestion);
    }

    @Override
    public void removeExamquestionById(Integer id) {
        this.examquestionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateExamquestion(ExamquestionEntity examquestion) {
        this.examquestionMapper.updateByPrimaryKeySelective(examquestion);
    }
}
