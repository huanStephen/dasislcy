package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.SubjectMapper;
import org.eocencle.dasislcy.entity.SubjectEntity;
import org.eocencle.dasislcy.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 科目service
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class SubjectService implements ISubjectService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SubjectMapper subjectMapper;

    @Override
    public SubjectEntity getSubjectById(Integer id) {
        return this.subjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addSubject(SubjectEntity subject) {
        this.subjectMapper.insertSelective(subject);
    }

    @Override
    public void removeSubjectById(Integer id) {
        this.subjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateSubject(SubjectEntity subject) {
        this.subjectMapper.updateByPrimaryKeySelective(subject);
    }

    @Override
    public PageAdapter<SubjectEntity> getSubjects(PageAdapter<SubjectEntity> page) {
        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<SubjectEntity> list = this.subjectMapper.selectAll();
        PageInfo<SubjectEntity> info = new PageInfo<SubjectEntity>(list);
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }
}
