package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.OutlineEntity;
import org.eocencle.dasislcy.service.IOutlineService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 大纲控制层
 * @Auther: huanStephen
 * @Date: 2019/5/11
 * @Description:
 */
@RestController
@RequestMapping("/wx/outline")
public class OutlineController {

    @Autowired
    private IOutlineService outlineService;

    @RequestMapping("/getOutlines")
    public Result<PageAdapter<OutlineEntity>> getOutlines(Integer chapterId, Integer currPage, Integer pageSize) {
        Result<PageAdapter<OutlineEntity>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == currPage || 1 > currPage || null == pageSize || 1 > pageSize) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("分页有误！");
            return result;
        }

        PageAdapter<OutlineEntity> page = new PageAdapter<>();
        page.setCurrPage(currPage);
        page.setPageSize(pageSize);
        page = this.outlineService.getOutlineByChapterId(chapterId, page);

        result.setData(page);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/addOutline")
    public Result<Boolean> addOutline(OutlineEntity outline) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.outlineService.addOutline(outline);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/updateOutline")
    public Result<Boolean> updateOutline(OutlineEntity chapter) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.outlineService.updateOutline(chapter);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/delOutline")
    public Result<Boolean> delOutline(Integer outlineId) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.outlineService.removeOutlineById(outlineId);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }
}
