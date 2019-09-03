package org.eocencle.dasislcy.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.eocencle.dasislcy.dto.StudentExamResultDto;
import org.eocencle.dasislcy.service.IScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: Xukai
 * Description:
 * CreateDate: 2019/8/28 23:09
 * Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ScoreServiceTest {

    @Autowired
    private IScoreService scoreService;

    @Test
    public void getScoreByStuIdAndExamId() {
        StudentExamResultDto studentExamResultDto = scoreService.getScoreByStuIdAndExamId(1, 1);
        log.info(studentExamResultDto.getStudentId().toString());
        log.info(studentExamResultDto.getExampaperId().toString());
        log.info(studentExamResultDto.getQuestionCount().toString());
        log.info(studentExamResultDto.getTotalScore().toString());
        log.info(studentExamResultDto.getCorrectScore().toString());
        log.info(studentExamResultDto.getCorrectRatio());
        log.info(studentExamResultDto.getCreateTime().toString());
        log.info(studentExamResultDto.getCorrectCount().toString());
    }
}