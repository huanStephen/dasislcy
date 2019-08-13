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
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void addOutline(OutlineEntity outline) {
        // 更新sort大于等于他的数据
//        Example example = new Example(OutlineEntity.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("subjectId", outline.getSubjectId());
//        criteria.andEqualTo("parentId", outline.getParentId());
//        criteria.andGreaterThanOrEqualTo("sort", outline.getSort());
//
//        List<OutlineEntity> upList = this.outlineMapper.selectByExample(example);
//        for (OutlineEntity up: upList) {
//            up.setSort(up.getSort() + 1);
//            this.outlineMapper.updateByPrimaryKeySelective(up);
//        }
//
//        this.outlineMapper.insertSelective(outline);
    }

    @Override
    public void removeOutlineById(Integer id) {
        this.outlineMapper.deleteByPrimaryKey(id);
        // 检索id的记录，如果parentId为0则删除子集的数据，如果不为0则只删除本级数据
//        OutlineEntity search = this.outlineMapper.selectByPrimaryKey(id);
//
//        if (null != search) {
//            if (0 == search.getParentId()) {
//                OutlineEntity record = new OutlineEntity();
//                record.setSubjectId(search.getSubjectId());
//                record.setParentId(id);
//
//                List<OutlineEntity> dels = this.outlineMapper.select(record);
//                for (OutlineEntity del: dels) {
//                    this.outlineMapper.deleteByPrimaryKey(del.getId());
//                }
//            }
//
//            this.outlineMapper.deleteByPrimaryKey(id);
//        }
    }

    @Override
    public void removeOutlineBySubjectId(Integer subjectId) {
        OutlineEntity record = new OutlineEntity();
        record.setSubjectId(subjectId);
        this.outlineMapper.delete(record);
    }

    @Override
    public void removeOutlineByChapterId(Integer chapterId) {
        OutlineEntity record = new OutlineEntity();
        record.setChapterId(chapterId);
        this.outlineMapper.delete(record);
    }

    @Override
    public void updateOutline(OutlineEntity outline) {
        this.outlineMapper.updateByPrimaryKeySelective(outline);
    }

    @Override
    public List<OutlineDto> getOutlineBySubjectId(Integer subjectId, Integer level) {
        if (null == subjectId || 0 == subjectId) {
            return null;
        }

        Example example = new Example(OutlineEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("subjectId", subjectId);
        criteria.andEqualTo("parentId", level);
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
