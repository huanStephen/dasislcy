package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.dto.OutlineDto;
import org.eocencle.dasislcy.entity.OutlineEntity;
import org.eocencle.dasislcy.service.IOutlineService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<Map<String, Object>> getOutlines(Integer subjectId) {
        Result<Map<String, Object>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == subjectId || 1 > subjectId) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("科目ID有误！");
            return result;
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", this.outlineService.getOutlineBySubjectId(subjectId));
        result.setData(data);
        return result;
    }

}
