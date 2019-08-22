package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.ExamquestionMapper;
import org.eocencle.dasislcy.dao.StudentExamResultMapper;
import org.eocencle.dasislcy.dto.StudentExamQuestionDto;
import org.eocencle.dasislcy.entity.ExamquestionEntity;
import org.eocencle.dasislcy.entity.StudentExamResultEntity;
import org.eocencle.dasislcy.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 答卷service
 *
 * @Auther: huanStephen
 * @Date: 2019/4/26
 * @Description:
 */
@Service
public class AnswerService implements IAnswerService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private StudentExamResultMapper studentExamResultMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ExamquestionMapper examquestionMapper;

    @Override
    public StudentExamResultEntity getStudentExamResultById(Integer id) {
        return this.studentExamResultMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addStudentExamResult(StudentExamResultEntity studentExamResult) {
        this.studentExamResultMapper.insertSelective(studentExamResult);
    }

    @Override
    public void removeStudentExamResultById(Integer id) {
        this.studentExamResultMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateStudentExamResult(StudentExamResultEntity studentExamResult) {
        this.studentExamResultMapper.updateByPrimaryKey(studentExamResult);
    }

    @Override
    public PageAdapter<StudentExamResultEntity> getStudentExamResultByExampagerId(Integer exampagerId, PageAdapter page) {
        Example example = new Example(StudentExamResultEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("exampagerId", exampagerId);
        example.orderBy("sort").asc();

        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<StudentExamResultEntity> list = this.studentExamResultMapper.selectByExample(example);
        PageInfo<StudentExamResultEntity> info = new PageInfo<StudentExamResultEntity>(list);
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }

    @Override
    public StudentExamResultEntity getStudentExamResultByExamquestionId(Integer studentId, Integer exampaperId, Integer examquestionId) {
        StudentExamResultEntity record = new StudentExamResultEntity();
        record.setStudentId(studentId);
        record.setExampaperId(exampaperId);
        record.setExamquestionId(examquestionId);

        List<StudentExamResultEntity> list = this.studentExamResultMapper.select(record);

        if (null == list || 0 == list.size()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    @Transactional
    public void generateStudentExamCardByExampagerId(Integer studentId, Integer exampaperId) {
        ExamquestionEntity record = new ExamquestionEntity();
        record.setExampaperId(exampaperId);

        List<ExamquestionEntity> list = this.examquestionMapper.select(record);

        StudentExamQuestionDto dto = null;
        for (ExamquestionEntity entity : list) {
            dto = new StudentExamQuestionDto((entity));
            dto.setStudentId(studentId);
            this.studentExamResultMapper.insertSelective(dto);
        }
    }
}
