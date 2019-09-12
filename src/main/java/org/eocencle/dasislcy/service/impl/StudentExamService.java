package org.eocencle.dasislcy.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.eocencle.dasislcy.dao.*;
import org.eocencle.dasislcy.dto.StudentExamOutlineDto;
import org.eocencle.dasislcy.dto.StudentExamResultDto;
import org.eocencle.dasislcy.dto.StudentExampaperDto;
import org.eocencle.dasislcy.entity.*;
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
    private StudentExamOutlineMapper studentExamOutlineMapper;

    @Autowired
    private StudentExampaperMapper studentExampaperMapper;

    @Autowired
    private ExampaperMapper exampaperMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private QuestionScoreMapper questionScoreMapper;

    @Override
    public Integer updateRecord(Integer stuAndExamId) {
        List<QuestionScoreEntity> questionScoreList = questionScoreMapper.getAll();
        Map<Integer, Integer> typeScoreMap = questionScoreList.stream()
                .collect(Collectors.toMap(QuestionScoreEntity::getQuestionType, QuestionScoreEntity::getScore, (bean1, bean2) -> bean1));
        StudentExampaperEntity studentExampaper = studentExampaperMapper.getById(stuAndExamId);
        // 封装考试结果
        List<StudentExamResultEntity> studentExamResultList = studentExamResultMapper.getByStuAndExampaperId(stuAndExamId);
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
        ExampaperEntity exampaper = exampaperMapper.getById(studentExampaper.getExampaperId());
        Integer subjectId = exampaper.getSubjectId();

        List<StudentExamOutlineEntity> studentExamOutlineList = studentExamOutlineMapper.getByStuAndExampaperId(stuAndExamId);
        List<StudentExamOutlineEntity> effectiveExamOutlineList = studentExamOutlineList.stream().filter(bean -> bean.getSubjectId().equals(subjectId)).collect(Collectors.toList());
        Integer countDeserved = effectiveExamOutlineList.stream().map(StudentExamOutlineEntity::getDeserved)
                .reduce(0, (x, y) -> x + y);
        Integer countActually = effectiveExamOutlineList.stream().map(StudentExamOutlineEntity::getActually)
                .reduce(0, (x, y) -> x + y);
        String ratio = BigdecimalUtils.div(countActually.toString(), countDeserved.toString(), 4);
        StudentExamOutlineDto studentExamOutlineDto = new StudentExamOutlineDto(effectiveExamOutlineList.get(0));
        studentExamOutlineDto.setCountDeserved(countDeserved);
        studentExamOutlineDto.setCountActually(countActually);
        studentExamOutlineDto.setRatio(ratio);
        studentExampaper.setOutlineRecord(JSONObject.toJSONString(studentExamOutlineDto));
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
        List<Integer> exampaperIds = exampaperList.stream().map(StudentExampaperEntity::getExampaperId).collect(Collectors.toList());
        List<ExampaperEntity> exampaperEntityList = exampaperMapper.getByIds(exampaperIds);
        Map<Integer, String> exampaperId2ExamNameMap = exampaperEntityList.stream().collect(Collectors.toMap(ExampaperEntity::getId, ExampaperEntity::getName, (x, y) -> x));
        Map<Integer, Integer> exampaperId2SubjectIdMap = exampaperEntityList.stream().collect(Collectors.toMap(ExampaperEntity::getId, ExampaperEntity::getSubjectId, (x, y) -> x));
        List<Integer> subjectIdList = exampaperEntityList.stream().map(ExampaperEntity::getSubjectId).collect(Collectors.toList());
        List<SubjectEntity> subjectList = subjectMapper.getByIds(subjectIdList);
        Map<Integer, String> subjectId2SubjectNameMap = subjectList.stream().collect(Collectors.toMap(SubjectEntity::getId, SubjectEntity::getName, (x, y) -> x));
        List<StudentExampaperDto> studentExampaperDtoList = exampaperList.stream()
                .map(t -> new StudentExampaperDto(t.getId(), t.getStudentId(), t.getExampaperId(),
                        exampaperId2ExamNameMap.get(t.getExampaperId()),
                        subjectId2SubjectNameMap.get(exampaperId2SubjectIdMap.get(t.getExampaperId()))))
                .collect(Collectors.toList());
        return studentExampaperDtoList;
    }
}
