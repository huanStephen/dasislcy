package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dto.ExamQuestionDto;
import org.eocencle.dasislcy.entity.ExampaperEntity;
import org.eocencle.dasislcy.entity.ExamquestionEntity;
import org.eocencle.dasislcy.service.IExampaperService;
import org.eocencle.dasislcy.service.IExamquestionService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 试卷控制类
 */
@RestController
@RequestMapping("/wx/exampaper")
public class ExamPaperController {

    @Autowired
    private IExampaperService exampaperService;

    @Autowired
    private IExamquestionService examquestionService;

    @Value("${main.answersheet-dir}")
    private String answerSheetDir;

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

    @RequestMapping("/addExamPaper")
    public Result<Boolean> addExamPaper(ExampaperEntity exampaper) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.exampaperService.addExampaper(exampaper);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/updateExamPaper")
    public Result<Boolean> updateExamPaper(ExampaperEntity exampaper) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.exampaperService.updateExampaper(exampaper);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/delExamPaper")
    public Result<Boolean> delExamPaper(Integer examPaperId) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == examPaperId) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("试卷ID有误！");
            return result;
        }

        this.exampaperService.removeExampaperById(examPaperId);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/getExamQuestions")
    public Result<PageAdapter<ExamQuestionDto>> getExamQuestions(Integer exampaperId, Integer currPage, Integer pageSize) {
        Result<PageAdapter<ExamQuestionDto>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == currPage || 1 > currPage || null == pageSize || 1 > pageSize) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("分页有误！");
            return result;
        }

        PageAdapter<ExamQuestionDto> page = new PageAdapter<>();
        page.setCurrPage(currPage);
        page.setPageSize(pageSize);
        page = this.examquestionService.getExamquestionByExampaperId(exampaperId, page);

        result.setData(page);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/addExamQuestion")
    public Result<Boolean> addExamQuestion(ExamquestionEntity examquestion) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.examquestionService.addExamquestion(examquestion);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/addExamQuestions")
    public Result<Boolean> addExamQuestions(@RequestBody List<ExamquestionEntity> examquestions) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.examquestionService.addExamquestions(examquestions);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/delExamQuestion")
    public Result<Boolean> delExamQuestion(Integer examquestionId) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == examquestionId) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("试题ID有误！");
            return result;
        }

        this.examquestionService.removeExamquestionById(examquestionId);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    /**
     * 答题卡上传
     * @param answersheet
     * @return
     */
    @RequestMapping("/uploadAnswerSheet")
    public Result<Boolean> uploadAnswerSheet(@RequestParam("answersheet") MultipartFile answersheet){
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        try {
            String path = this.answerSheetDir + "/" + new Date().getTime() + answersheet.getOriginalFilename();
            File newFile = new File(path);
            //通过CommonsMultipartFile的方法直接写文件
            answersheet.transferTo(newFile);
            this.exampaperService.importAnswerSheet(path);

            result.setData(true);
            result.setMsg("答题卡上传成功！");
        } catch (IOException e) {
            result.setData(false);
            result.setMsg("答题卡上传失败！");
            e.printStackTrace();
        }

        return result;
    }

}
