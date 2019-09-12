package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.QuestionScoreEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface QuestionScoreMapper extends Mapper<QuestionScoreEntity> {


    /**
     * 根据题目类型获得实体
     *
     * @param type
     * @return
     */
    QuestionScoreEntity getByType(Integer type);


    /**
     * 获得所有题目-分数对应关系
     *
     * @return
     */
    List<QuestionScoreEntity> getAll();
}