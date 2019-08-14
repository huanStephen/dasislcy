package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.ChapterEntity;
import org.eocencle.dasislcy.service.IChapterService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 章节控制类
 * @Auther: huanStephen
 * @Date: 2019/5/11
 * @Description:
 */
@RestController
@RequestMapping("/wx/chapter")
public class ChapterController {

    @Autowired
    private IChapterService chapterService;

    @RequestMapping("/getChapters")
    public Result<PageAdapter<ChapterEntity>> getChapters(Integer subjectId, Integer currPage, Integer pageSize) {
        Result<PageAdapter<ChapterEntity>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == currPage || 1 > currPage || null == pageSize || 1 > pageSize) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("分页有误！");
            return result;
        }

        PageAdapter<ChapterEntity> page = new PageAdapter<>();
        page.setCurrPage(currPage);
        page.setPageSize(pageSize);
        page = this.chapterService.getChapters(subjectId, page);

        result.setData(page);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/addChapter")
    public Result<Boolean> addChapter(ChapterEntity chapter) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.chapterService.addChapter(chapter);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/updateChapter")
    public Result<Boolean> updateChapter(ChapterEntity chapter) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.chapterService.updateChapter(chapter);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/delChapter")
    public Result<Boolean> delChapter(Integer chapterId) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.chapterService.removeChapterById(chapterId);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值

    }

}
