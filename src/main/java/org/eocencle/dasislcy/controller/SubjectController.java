package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.SubjectEntity;
import org.eocencle.dasislcy.service.ISubjectService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 科目控制类
 * @Auther: huanStephen
 * @Date: 2019/5/11
 * @Description:
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @RequestMapping("/getSubjects")
    public Result<PageAdapter<SubjectEntity>> getSubjects(Integer currPage, Integer pageSize) {
        Result<PageAdapter<SubjectEntity>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == currPage || 1 > currPage || null == pageSize || 1 > pageSize) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("分页有误！");
            return result;
        }

        PageAdapter<SubjectEntity> page = new PageAdapter<>();
        page.setCurrPage(currPage);
        page.setPageSize(pageSize);
        page = this.subjectService.getSubjects(page);

        result.setData(page);
        return result;
    }

}
