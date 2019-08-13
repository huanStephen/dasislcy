package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.SubjectMapper;
import org.eocencle.dasislcy.entity.SubjectEntity;
import org.eocencle.dasislcy.service.IChapterService;
import org.eocencle.dasislcy.service.IOutlineService;
import org.eocencle.dasislcy.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private IChapterService chapterService;

    @Override
    public SubjectEntity getSubjectById(Integer id) {
        return this.subjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addSubject(SubjectEntity subject) {
        this.subjectMapper.insertSelective(subject);
    }

    @Override
    @Transactional
    public void removeSubjectById(Integer id) {
        // 删除科目
        this.subjectMapper.deleteByPrimaryKey(id);
        // 删除章节
        this.chapterService.removeChapterBySubjectId(id);
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
