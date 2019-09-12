package org.eocencle.dasislcy.dao;

import org.eocencle.dasislcy.entity.SubjectEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SubjectMapper extends Mapper<SubjectEntity> {

    List<SubjectEntity> getSubjects(Map<String, Integer> params);

    /**
     * 根据多个ID主键查找
     *
     * @param ids
     * @return
     */
    List<SubjectEntity> getByIds(List<Integer> ids);

}