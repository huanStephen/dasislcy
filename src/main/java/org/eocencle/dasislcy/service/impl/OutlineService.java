package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.OutlineMapper;
import org.eocencle.dasislcy.dto.OutlineDto;
import org.eocencle.dasislcy.entity.OutlineEntity;
import org.eocencle.dasislcy.service.IOutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
    public List<OutlineDto> getOutlineBySubjectId(Integer subjectId) {
        if (null == subjectId || 0 == subjectId) {
            return null;
        }

        OutlineEntity record = new OutlineEntity();
        record.setSubjectId(subjectId);
        Example example = new Example(OutlineEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("subjectId", subjectId);
        criteria.andEqualTo("parentId", 0);
        example.orderBy("sort").asc();

        List<OutlineEntity> list = this.outlineMapper.selectByExample(example);

        List<OutlineDto> rlist = new ArrayList<>();
        OutlineDto dto = null;
        for (OutlineEntity outline: list) {
            dto = new OutlineDto(outline);

            Example childExample = new Example(OutlineEntity.class);
            Example.Criteria childCriteria = example.createCriteria();
            childCriteria.andEqualTo("subjectId", subjectId);
            childCriteria.andEqualTo("parentId", outline.getId());
            childExample.orderBy("sort").asc();

            dto.setChildren(this.outlineMapper.selectByExample(childExample));

            rlist.add(dto);
        }

        return rlist;
    }
}
