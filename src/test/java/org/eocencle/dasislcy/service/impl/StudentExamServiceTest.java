package org.eocencle.dasislcy.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.eocencle.dasislcy.dto.StudentExampaperDto;
import org.eocencle.dasislcy.entity.StudentExampaperEntity;
import org.eocencle.dasislcy.service.IStudentExamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Author: Xukai
 * Description:
 * CreateDate: 2019/8/28 23:09
 * Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StudentExamServiceTest {

    @Autowired
    private IStudentExamService studentExamService;

    @Test
    public void getExamAnalyzeByStuAndExamId() {
        StudentExampaperEntity studentExampaperEntity = studentExamService.getExamAnalyzeByStuAndExamId(1);
        log.info(studentExampaperEntity.getId().toString());
        log.info(studentExampaperEntity.getStudentId().toString());
        log.info(studentExampaperEntity.getExampaperId().toString());
        log.info(studentExampaperEntity.getResultRecord());
    }

    @Test
    public void updateRecord() {
        studentExamService.updateRecord(1);
    }

    @Test
    public void getExamsByStuId() {
        List<StudentExampaperDto> exampaperDtos = studentExamService.getExamsByStuId(1);
        exampaperDtos.forEach(System.out::println);
    }
}