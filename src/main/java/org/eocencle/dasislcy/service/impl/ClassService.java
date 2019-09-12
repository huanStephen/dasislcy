package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.ClassMapper;
import org.eocencle.dasislcy.entity.ClassEntity;
import org.eocencle.dasislcy.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班级service
 *
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
        cls.setCnt(0);
        this.classMapper.insertSelective(cls);
    }

    @Override
    public void removeClassById(Integer id) {
        this.classMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateClass(ClassEntity cls) {
        ClassEntity old = this.classMapper.selectByPrimaryKey(cls.getId());
        cls.setCnt(old.getCnt());
        this.classMapper.updateByPrimaryKeySelective(cls);
    }

    @Override
    public PageAdapter<ClassEntity> getClasses(PageAdapter<ClassEntity> page) {
        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<ClassEntity> list = this.classMapper.selectAll();
        PageInfo<ClassEntity> info = new PageInfo<ClassEntity>(list);
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }
}
