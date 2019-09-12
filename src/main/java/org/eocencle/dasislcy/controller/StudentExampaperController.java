package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.dto.StudentExampaperDto;
import org.eocencle.dasislcy.entity.StudentExampaperEntity;
import org.eocencle.dasislcy.service.IStudentExamService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: Xukai
 * Description:
 * CreateDate: 2019/9/1 19:02
 * Modified By:
 */
@RestController
@RequestMapping("/wx/studentExampaper")
public class StudentExampaperController {

    @Autowired
    private IStudentExamService studentExamService;

    @GetMapping("/getExamsByStuId")
    public Result<List<StudentExampaperDto>> getExamsByStuId(Integer stuId) {
        Result<List<StudentExampaperDto>> result = new Result<>(Result.STATUS_SUCCESSED);
        List<StudentExampaperDto> exampaperDtos = studentExamService.getExamsByStuId(1);
        result.setData(exampaperDtos);
        return result;
    }

    @GetMapping("/getExamAnalyzeById")
    public Result<StudentExampaperEntity> getExamAnalyzeById(Integer id) {
        Result<StudentExampaperEntity> result = new Result<>(Result.STATUS_SUCCESSED);
        StudentExampaperEntity studentExampaperEntity = studentExamService.getExamAnalyzeByStuAndExamId(id);
        result.setData(studentExampaperEntity);
        return result;
    }

}
