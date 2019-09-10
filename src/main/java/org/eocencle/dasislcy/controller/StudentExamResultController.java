package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.entity.StudentExampaperEntity;
import org.eocencle.dasislcy.service.IStudentExamService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Xukai
 * Description:
 * CreateDate: 2019/9/1 19:02
 * Modified By:
 */
@RestController
@RequestMapping("/wx/studentAbility")
public class StudentExamResultController {

    @Autowired
    private IStudentExamService scoreService;

    @GetMapping("/getScoreByStuIdAndExamId")
    public Result<StudentExampaperEntity> getScoreByStuIdAndExamId(Integer stuId, Integer examId) {
        Result<StudentExampaperEntity> result = new Result<>(Result.STATUS_SUCCESSED);
        StudentExampaperEntity studentExampaperEntity = scoreService.getExamAnalyzeByStuAndExamId(1);
        result.setData(studentExampaperEntity);
        return result;
    }

}
