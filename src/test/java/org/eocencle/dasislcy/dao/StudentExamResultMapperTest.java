package org.eocencle.dasislcy.dao;

import lombok.extern.slf4j.Slf4j;
import org.eocencle.dasislcy.entity.StudentExamResultEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Author: Xukai
 * Description:
 * CreateDate: 2019/9/1 20:49
 * Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StudentExamResultMapperTest {

    @Autowired
    private StudentExamResultMapper studentExamResultMapper;

    @Test
    public void getByStuIdAndExamId() {
        List<StudentExamResultEntity> studentExamResultEntityList = studentExamResultMapper.getByStuIdAndExamId(1, 1);
        for (StudentExamResultEntity studentExamResultEntity : studentExamResultEntityList) {
            log.info(studentExamResultEntity.getId().toString());
        }
    }
}