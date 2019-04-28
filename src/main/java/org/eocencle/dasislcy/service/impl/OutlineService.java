package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.OutlineMapper;
import org.eocencle.dasislcy.entity.OutlineEntity;
import org.eocencle.dasislcy.service.IOutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大纲service
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class OutlineService implements IOutlineService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OutlineMapper outlineMapper;

    @Override
    public OutlineEntity getOutlineById(Integer id) {
        return this.outlineMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addOutline(OutlineEntity outline) {
        this.outlineMapper.insertSelective(outline);
    }

    @Override
    public void removeOutlineById(Integer id) {
        this.outlineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateOutline(OutlineEntity outline) {
        this.outlineMapper.updateByPrimaryKeySelective(outline);
    }

    @Override
    public PageAdapter<OutlineEntity> getOutlineBySubjectId(Integer subjectId, PageAdapter<OutlineEntity> page) {
        OutlineEntity record = new OutlineEntity();
        record.setSubjectId(subjectId);

        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<OutlineEntity> list = this.outlineMapper.select(record);
        PageInfo<OutlineEntity> info = new PageInfo<OutlineEntity>(list);
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }
}
