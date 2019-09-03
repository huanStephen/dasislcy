package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.dto.StudentExamResultDto;
import org.eocencle.dasislcy.service.IScoreService;
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
    private IScoreService scoreService;

    @GetMapping("/getScoreByStuIdAndExamId")
    public Result<StudentExamResultDto> getScoreByStuIdAndExamId(Integer stuId, Integer examId) {
        Result<StudentExamResultDto> result = new Result<>(Result.STATUS_SUCCESSED);
        StudentExamResultDto scoreByStuIdAndExamId = scoreService.getScoreByStuIdAndExamId(stuId, examId);
        result.setData(scoreByStuIdAndExamId);
        return result;
    }

    @GetMapping("/getStudentAbilityByStuId")
    public Result<StudentExamResultDto> getStudentAbilityByStuId(Integer stuId) {
        Result<StudentExamResultDto> result = new Result<>(Result.STATUS_SUCCESSED);



        return result;
    }
}
