package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.ChapterMapper;
import org.eocencle.dasislcy.entity.ChapterEntity;
import org.eocencle.dasislcy.service.IChapterService;
import org.eocencle.dasislcy.service.IOutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChapterService implements IChapterService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ChapterMapper chapterMapper;

    @Autowired
    private IOutlineService outlineService;

    @Override
    public ChapterEntity getChapterById(Integer id) {
        return this.chapterMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addChapter(ChapterEntity chapter) {
        this.chapterMapper.insertSelective(chapter);
    }

    @Override
    @Transactional
    public void removeChapterById(Integer id) {
        // 删除章节
        this.chapterMapper.deleteByPrimaryKey(id);
        // 删除大纲
        this.outlineService.removeOutlineByChapterId(id);
    }

    @Override
    @Transactional
    public void removeChapterBySubjectId(Integer subjectId) {
        // 删除章节
        ChapterEntity record = new ChapterEntity();
        record.setSubjectId(subjectId);
        this.chapterMapper.delete(record);
        // 删除大纲
        this.outlineService.removeOutlineBySubjectId(subjectId);
    }

    @Override
    public void updateChapter(ChapterEntity chapter) {
        this.chapterMapper.updateByPrimaryKeySelective(chapter);
    }

    @Override
    public PageAdapter<ChapterEntity> getChapters(PageAdapter<ChapterEntity> page) {
        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<ChapterEntity> list = this.chapterMapper.selectAll();
        PageInfo<ChapterEntity> info = new PageInfo<ChapterEntity>(list);
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }
}
