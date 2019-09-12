package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.ExampaperEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ExampaperMapper extends Mapper<ExampaperEntity> {

    /**
     * 根据ID主键查找
     *
     * @param id
     * @return
     */
    ExampaperEntity getById(Integer id);

    /**
     * 根据多个ID主键查找
     *
     * @param ids
     * @return
     */
    List<ExampaperEntity> getByIds(List<Integer> ids);
}