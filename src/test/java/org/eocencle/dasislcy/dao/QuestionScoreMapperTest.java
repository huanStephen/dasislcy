package org.eocencle.dasislcy.dao;

import lombok.extern.slf4j.Slf4j;
import org.eocencle.dasislcy.entity.QuestionScoreEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: Xukai
 * Description:
 * CreateDate: 2019/9/1 19:22
 * Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuestionScoreMapperTest {

    @Autowired
    private QuestionScoreMapper questionScoreMapper;

    @Test
    public void getByType() {
        QuestionScoreEntity questionScore = questionScoreMapper.getByType(1);
        log.info(questionScore.getQuestionType().toString());
        log.info(questionScore.getScore().toString());
    }
}