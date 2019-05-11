package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.entity.OutlineEntity;
import org.eocencle.dasislcy.service.IOutlineService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 大纲控制层
 * @Auther: huanStephen
 * @Date: 2019/5/11
 * @Description:
 */
@RestController
@RequestMapping("/outline")
public class OutlineController {

    @Autowired
    private IOutlineService outlineService;

    @RequestMapping("/getOutlines")
    public Result<List<OutlineEntity>> getOutlines(Integer subjectId) {
        Result<List<OutlineEntity>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == subjectId || 1 > subjectId) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("科目ID有误！");
            return result;
        }

        result.setData(this.outlineService.getOutlineBySubjectId(subjectId));
        return result;
    }

}
