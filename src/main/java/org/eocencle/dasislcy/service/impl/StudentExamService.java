package org.eocencle.dasislcy.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.eocencle.dasislcy.dao.QuestionScoreMapper;
import org.eocencle.dasislcy.dao.StudentExamResultMapper;
import org.eocencle.dasislcy.dao.StudentExampaperMapper;
import org.eocencle.dasislcy.dto.StudentExamResultDto;
import org.eocencle.dasislcy.dto.StudentExampaperDto;
import org.eocencle.dasislcy.entity.QuestionScoreEntity;
import org.eocencle.dasislcy.entity.StudentExamResultEntity;
import org.eocencle.dasislcy.entity.StudentExampaperEntity;
import org.eocencle.dasislcy.service.IStudentExamService;
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
public class StudentExamService implements IStudentExamService {

    @Autowired
    private StudentExamResultMapper studentExamResultMapper;

    @Autowired
    private StudentExampaperMapper studentExampaperMapper;

    @Autowired
    private QuestionScoreMapper questionScoreMapper;

    @Override
    public Integer updateRecord(Integer stuAndExamId) {
        List<QuestionScoreEntity> questionScoreList = questionScoreMapper.getAll();
        Map<Integer, Integer> typeScoreMap = questionScoreList.stream()
                .collect(Collectors.toMap(QuestionScoreEntity::getQuestionType, QuestionScoreEntity::getScore, (bean1, bean2) -> bean1));
        StudentExampaperEntity studentExampaper = studentExampaperMapper.getById(stuAndExamId);
        List<StudentExamResultEntity> studentExamResultList = studentExamResultMapper.getByStuAndExampaperId(studentExampaper.getId());
        // 封装考试结果
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
        studentExamResultDto.setCreateTime(studentExamResultList.get(0).getCreateTime());
        studentExampaper.setResultRecord(JSONObject.toJSONString(studentExamResultDto));
        // 封装考试大纲分析

        return studentExampaperMapper.updateRecord(studentExampaper);
    }

    @Override
    public StudentExampaperEntity getExamAnalyzeByStuAndExamId(Integer stuAndExamId) {
        StudentExampaperEntity studentExampaper = studentExampaperMapper.getById(stuAndExamId);
        return studentExampaper;
    }


    @Override
    public List<StudentExampaperDto> getExamsByStuId(Integer stuId) {
        List<StudentExampaperEntity> exampaperList = studentExampaperMapper.getByStuId(stuId);
        List<StudentExampaperDto> studentExampaperDtoList = exampaperList.stream()
                .map(t -> new StudentExampaperDto(t.getId(), t.getStudentId(), t.getExampaperId(), "", ""))
                .collect(Collectors.toList());
        return studentExampaperDtoList;
    }
}
