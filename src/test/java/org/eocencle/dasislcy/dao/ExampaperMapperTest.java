package org.eocencle.dasislcy.dao;

import lombok.extern.slf4j.Slf4j;
import org.eocencle.dasislcy.entity.ExampaperEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: Xukai
 * Description:
 * CreateDate: 2019/9/11 23:13
 * Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExampaperMapperTest {

    @Autowired
    private ExampaperMapper exampaperMapper;

    @Test
    public void getById() {
        ExampaperEntity exampaperEntity = exampaperMapper.getById(2);
        log.info(exampaperEntity.getId().toString());
    }
}