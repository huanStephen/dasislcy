package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.ClassEntity;

/**
 * 班级service
 *
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IClassService {

    /**
     * 获取班级
     *
     * @param id
     * @return
     */
    ClassEntity getClassById(Integer id);

    /**
     * 添加班级
     *
     * @param cls
     */
    void addClass(ClassEntity cls);

    /**
     * 删除班级
     *
     * @param id
     */
    void removeClassById(Integer id);

    /**
     * 更新班级
     *
     * @param cls
     */
    void updateClass(ClassEntity cls);

    /**
     * 获取班级列表
     *
     * @param page
     * @return
     */
    PageAdapter<ClassEntity> getClasses(PageAdapter<ClassEntity> page);
}
