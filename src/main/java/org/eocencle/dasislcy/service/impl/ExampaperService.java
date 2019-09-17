package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.ExampaperMapper;
import org.eocencle.dasislcy.entity.ExampaperEntity;
import org.eocencle.dasislcy.service.IExampaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 试卷service
 *
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class ExampaperService implements IExampaperService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ExampaperMapper exampaperMapper;

    @Override
    public ExampaperEntity getExampaperById(Integer id) {
        return this.exampaperMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addExampaper(ExampaperEntity exampaper) {
        this.exampaperMapper.insertSelective(exampaper);
    }

    @Override
    public void removeExampaperById(Integer id) {
        this.exampaperMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateExampaper(ExampaperEntity exampaper) {
        this.exampaperMapper.updateByPrimaryKeySelective(exampaper);
    }

    @Override
    public PageAdapter<ExampaperEntity> getExampapersBySubjectId(Integer subjectId, PageAdapter<ExampaperEntity> page) {
        ExampaperEntity record = new ExampaperEntity();
        record.setSubjectId(subjectId);

        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<ExampaperEntity> list = this.exampaperMapper.select(record);
        PageInfo<ExampaperEntity> info = new PageInfo<ExampaperEntity>(list);
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }

    /**
     * 需求不详
     *
     * @param outlineId
     * @param page
     * @return
     */
    @Override
    public PageAdapter<ExampaperEntity> getExampapersByOutlineId(Integer outlineId, PageAdapter<ExampaperEntity> page) {
        return null;
    }

    @Override
    public PageAdapter<ExampaperEntity> getExampapers(PageAdapter<ExampaperEntity> page) {
        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<ExampaperEntity> list = this.exampaperMapper.selectAll();
        PageInfo<ExampaperEntity> info = new PageInfo<ExampaperEntity>(list);
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }

    @Override
    public void importAnswerSheet(String filePath) {
        // 答题卡识别
    }
}
