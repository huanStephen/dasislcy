package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.ClassEntity;
import org.eocencle.dasislcy.service.IClassService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 班级相关控制类
 * @Auther: huanStephen
 * @Date: 2019/5/7
 * @Description:
 */
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private IClassService classService;

    @RequestMapping("/getClasses")
    public Result<PageAdapter<ClassEntity>> getClasses(Integer currPage, Integer pageSize) {
        Result<PageAdapter<ClassEntity>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == currPage || 1 > currPage || null == pageSize || 1 > pageSize) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("分页有误！");
            return result;
        }

        PageAdapter<ClassEntity> page = new PageAdapter<>();
        page.setCurrPage(currPage);
        page.setPageSize(pageSize);
        page = this.classService.getClasses(page);

        result.setData(page);
        return result;
    }

}
