package org.eocencle.dasislcy.service.impl;

import org.eocencle.dasislcy.dao.QuestionScoreMapper;
import org.eocencle.dasislcy.dao.StudentExamResultMapper;
import org.eocencle.dasislcy.dto.StudentAbilityDto;
import org.eocencle.dasislcy.dto.StudentExamResultDto;
import org.eocencle.dasislcy.entity.QuestionScoreEntity;
import org.eocencle.dasislcy.entity.StudentExamResultEntity;
import org.eocencle.dasislcy.service.IScoreService;
import org.eocencle.dasislcy.util.BigdecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: Xukai
 * Description: 试题分数计算Service实现类
 * CreateDate: 2019/8/28 22:04
 * Modified By:
 */
@Service
public class ScoreService implements IScoreService {

    @Autowired
    private StudentExamResultMapper studentExamResultMapper;

    @Autowired
    private QuestionScoreMapper questionScoreMapper;

    @Override
    public StudentExamResultDto getScoreByStuIdAndExamId(Integer stuId, Integer examId) {
        List<QuestionScoreEntity> questionScoreList = questionScoreMapper.getAll();
        Map<Integer, Integer> typeScoreMap = questionScoreList.stream()
                .collect(Collectors.toMap(QuestionScoreEntity::getQuestionType, QuestionScoreEntity::getScore, (bean1, bean2) -> bean1));
        List<StudentExamResultEntity> studentExamResultList = studentExamResultMapper.getByStuIdAndExamId(stuId, examId);
        StudentExamResultDto studentExamResultDto = new StudentExamResultDto();
        studentExamResultDto.setQuestionCount(studentExamResultList.size());
        Integer totalScore = 0;
        for (StudentExamResultEntity entity : studentExamResultList) {
            totalScore += typeScoreMap.get(entity.getQuestionType());
        }
        studentExamResultDto.setTotalScore(totalScore);
        studentExamResultDto.setQuestionCount(studentExamResultList.size());
        List<StudentExamResultEntity> correctList = studentExamResultList.stream()
                .filter(bean -> bean.getHasTrue() == 1)
                .collect(Collectors.toList());
        Integer correctCount = 0;
        for (StudentExamResultEntity entity : correctList) {
            correctCount += typeScoreMap.get(entity.getQuestionType());
        }
        studentExamResultDto.setCorrectScore(correctCount);
        studentExamResultDto.setCorrectCount(correctList.size());
        studentExamResultDto.setCorrectRatio(BigdecimalUtils.div(String.valueOf(correctList.size()), String.valueOf(studentExamResultDto.getQuestionCount()), 4));
        studentExamResultDto.getParmsFromEntity(studentExamResultList.get(0));
        return studentExamResultDto;
    }

    @Override
    public StudentAbilityDto getStudentAbilityByStuId(Integer stuId) {
        return null;
    }
}
