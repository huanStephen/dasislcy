package org.eocencle.dasislcy.service.impl;

import org.eocencle.dasislcy.dao.ClassMapper;
import org.eocencle.dasislcy.entity.ClassEntity;
import org.eocencle.dasislcy.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 班级service
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class ClassService implements IClassService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ClassMapper classMapper;

    @Override
    public ClassEntity getClassById(Integer id) {
        return this.classMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addClass(ClassEntity cls) {
        this.classMapper.insertSelective(cls);
    }

    @Override
    public void removeClassById(Integer id) {
        this.classMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateClass(ClassEntity cls) {
        this.classMapper.updateByPrimaryKeySelective(cls);
    }
}
