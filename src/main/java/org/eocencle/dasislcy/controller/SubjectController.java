package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.SubjectEntity;
import org.eocencle.dasislcy.service.ISubjectService;
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
 * 科目控制类
 * @Auther: huanStephen
 * @Date: 2019/5/11
 * @Description:
 */
@RestController
@RequestMapping("/wx/subject")
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
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/addSubject")
    public Result<Boolean> addSubject(SubjectEntity subject) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.subjectService.addSubject(subject);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/updateSubject")
    public Result<Boolean> updateSubject(SubjectEntity subject) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.subjectService.updateSubject(subject);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/delSubject")
    public Result<Boolean> delSubject(Integer subjectId) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.subjectService.removeSubjectById(subjectId);

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
