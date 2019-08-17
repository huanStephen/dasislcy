package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.SubjectMapper;
import org.eocencle.dasislcy.dao.SubjectQuestionMapper;
import org.eocencle.dasislcy.dto.SubjectQuestionRelationDto;
import org.eocencle.dasislcy.entity.SubjectEntity;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;
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
    private IChapterService chapterService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private SubjectQuestionMapper subjectQuestionMapper;

    @Override
    public SubjectEntity getSubjectById(Integer id) {
        return this.subjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addSubject(SubjectEntity subject) {
        this.subjectMapper.insertSelective(subject);
    }

    @Override
    public void addSubjectQuestionMapping(SubjectQuestionEntity mapping) {
        this.subjectQuestionMapper.insertSelective(mapping);
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
    public void removeSubjectQuestionMapping(Integer mappingId) {
        SubjectQuestionEntity mapping = this.subjectQuestionMapper.selectByPrimaryKey(mappingId);
        SubjectQuestionEntity search = new SubjectQuestionEntity();
        search.setQuestionId(mapping.getQuestionId());
        List<SubjectQuestionEntity> mappingList = this.subjectQuestionMapper.select(search);
        // 判断映射是否只有一条，如果是一条则更新为“未分类”，如果多条则删除
        if (1 == mappingList.size()) {
            mapping.setSubjectId(0);
            mapping.setChapterId(null);
            mapping.setOutlineId(null);
            mapping.setUpdateTime(null);
            mapping.setWeight(0);
            this.subjectQuestionMapper.updateByPrimaryKey(mapping);
        } else {
            this.subjectQuestionMapper.deleteByPrimaryKey(mappingId);
        }
    }

    @Override
    public void updateSubject(SubjectEntity subject) {
        this.subjectMapper.updateByPrimaryKeySelective(subject);
    }

    @Override
    public void updateSubjectQuestionMapping(SubjectQuestionEntity mapping) {
        this.subjectQuestionMapper.updateByPrimaryKeySelective(mapping);
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

    @Override
    public List<SubjectQuestionRelationDto> getSubjectQuestionMappings(Integer questionId) {
        return this.subjectQuestionMapper.getSubjectQuestionMappings(questionId);
    }
}
