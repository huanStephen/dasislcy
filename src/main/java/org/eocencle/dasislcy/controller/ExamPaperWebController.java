package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.ExampaperEntity;
import org.eocencle.dasislcy.service.IExampaperService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/exampaper")
public class ExamPaperWebController {

    @Autowired
    private IExampaperService exampaperService;

    @RequestMapping("/getExamPapers")
    public Result<PageAdapter<ExampaperEntity>> getExamPapers(Integer currPage, Integer pageSize) {
        Result<PageAdapter<ExampaperEntity>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == currPage || 1 > currPage || null == pageSize || 1 > pageSize) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("分页有误！");
            return result;
        }

        PageAdapter<ExampaperEntity> page = new PageAdapter<>();
        page.setCurrPage(currPage);
        page.setPageSize(pageSize);
        page = this.exampaperService.getExampapers(page);

        result.setData(page);
        result.setMsg("请求成功！");
        return result;
    }

}
