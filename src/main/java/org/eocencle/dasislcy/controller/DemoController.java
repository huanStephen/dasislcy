package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.dto.ChoiceQuestionDto;
import org.eocencle.dasislcy.entity.ChoiceQuestionOptionEntity;
import org.eocencle.dasislcy.entity.SubjectQuestionEntity;
import org.eocencle.dasislcy.service.IQuestionService;
import org.eocencle.dasislcy.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: shizh26250
 * @Date: 2018/12/1 09:55
 * @Description:
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Value("${main.page}")
    private String page;

    @Autowired
    private IQuestionService questionService;

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index() {
        String reg_whole1 = "<div[^>]*><a[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*></a></div>";
        Pattern r1 = Pattern.compile(reg_whole1);

        String reg_whole2 = "<div[^>]*><a[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*></a></div>";
        Pattern r2 = Pattern.compile(reg_whole2);

        String reg_whole3 = "<div[^>]*><a[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*></a></div>";
        Pattern r3 = Pattern.compile(reg_whole3);

        String path = "C:\\Users\\dell\\Desktop\\试题\\h3-english\\";

        for (int i = 1; i <= 951; i ++) {
            System.out.println(i);
            String content = null;
            try {
                content = FileUtil.readFile(path + i).replaceAll("\r", "").replaceAll("\n", "");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Matcher m1 = r1.matcher(content);

            ChoiceQuestionDto dto = null;
            if (m1.matches()) {
                dto = plan1(m1);
                FileUtil.move(path + i, "C:\\Users\\dell\\Desktop\\ok\\h3-english");
            } else {
                Matcher m2 = r2.matcher(content);

                if (m2.matches()) {
                    dto = plan2(m2);
                    FileUtil.move(path + i, "C:\\Users\\dell\\Desktop\\ok\\h3-english");
                } else {
                    Matcher m3 = r3.matcher(content);

                    if (m3.matches()) {
                        dto = plan3(m3);
                        FileUtil.move(path + i, "C:\\Users\\dell\\Desktop\\ok\\h3-english");
                    }
                }
            }

            if (null != dto) {
                dto.setType(1);

                SubjectQuestionEntity subjectQuestion = new SubjectQuestionEntity();
                subjectQuestion.setQuestionId(dto.getId());
                subjectQuestion.setQuestionType(1);
                subjectQuestion.setSubjectId(6);

                this.questionService.addChoiceQuestion(dto, subjectQuestion);
            }
        }
        return this.page;
    }

    private ChoiceQuestionDto plan1(Matcher m) {
        String reg_option1 = "A.([^B.]+)B.([^C.]+)C.([^D.]+)D.(.*)";
        String title = "", option = "";
        ChoiceQuestionDto dto = new ChoiceQuestionDto();
        List<ChoiceQuestionOptionEntity> options = new ArrayList<ChoiceQuestionOptionEntity>();
        dto.setTitle(m.group(1));
        dto.setType(1);
        option = m.group(2);

        Pattern r = Pattern.compile(reg_option1);
        Matcher m1 = r.matcher(option.trim());

        if (m1.find()) {
            ChoiceQuestionOptionEntity optionA = new ChoiceQuestionOptionEntity();
            optionA.setSort(1);
            optionA.setAnswer(m1.group(1).replaceAll(" ", "").trim());
            options.add(optionA);

            ChoiceQuestionOptionEntity optionB = new ChoiceQuestionOptionEntity();
            optionB.setSort(2);
            optionB.setAnswer(m1.group(2).replaceAll(" ", "").trim());
            options.add(optionB);

            ChoiceQuestionOptionEntity optionC = new ChoiceQuestionOptionEntity();
            optionC.setSort(3);
            optionC.setAnswer(m1.group(3).replaceAll(" ", "").trim());
            options.add(optionC);

            ChoiceQuestionOptionEntity optionD = new ChoiceQuestionOptionEntity();
            optionD.setSort(4);
            optionD.setAnswer(m1.group(4).replaceAll(" ", "").trim());
            options.add(optionD);
        }

        dto.setOptions(options);

        return dto;
    }

    private ChoiceQuestionDto plan2(Matcher m) {
        String reg_option1 = "A.([^B.]+)B.(.*)";
        String reg_option2 = "C.([^D.]+)D.(.*)";
        String title = "", option1 = "", option2 = "";
        ChoiceQuestionDto dto = new ChoiceQuestionDto();
        List<ChoiceQuestionOptionEntity> options = new ArrayList<ChoiceQuestionOptionEntity>();
        dto.setTitle(m.group(1));
        dto.setType(1);
        option1 = m.group(2);
        option2 = m.group(3);

        Pattern r1 = Pattern.compile(reg_option1);
        Pattern r2 = Pattern.compile(reg_option2);
        Matcher m1 = r1.matcher(option1.trim());
        Matcher m2 = r2.matcher(option2.trim());

        if (m1.find()) {
            ChoiceQuestionOptionEntity optionA = new ChoiceQuestionOptionEntity();
            optionA.setSort(1);
            optionA.setAnswer(m1.group(1).replaceAll(" ", "").trim());
            options.add(optionA);

            ChoiceQuestionOptionEntity optionB = new ChoiceQuestionOptionEntity();
            optionB.setSort(2);
            optionB.setAnswer(m1.group(2).replaceAll(" ", "").trim());
            options.add(optionB);
        }

        if (m2.find()) {
            ChoiceQuestionOptionEntity optionC = new ChoiceQuestionOptionEntity();
            optionC.setSort(3);
            optionC.setAnswer(m2.group(1).replaceAll(" ", "").trim());
            options.add(optionC);

            ChoiceQuestionOptionEntity optionD = new ChoiceQuestionOptionEntity();
            optionD.setSort(4);
            optionD.setAnswer(m2.group(2).replaceAll(" ", "").trim());
            options.add(optionD);
        }

        dto.setOptions(options);

        return dto;
    }

    private ChoiceQuestionDto plan3(Matcher m) {
        String reg_option1 = "A.([^B.]+)B.(.*)";
        String reg_option2 = "C.([^D.]+)D.(.*)";
        String title = "", option1 = "", option2 = "";
        ChoiceQuestionDto dto = new ChoiceQuestionDto();
        List<ChoiceQuestionOptionEntity> options = new ArrayList<ChoiceQuestionOptionEntity>();
        title = m.group(1) + "\n" + m.group(2);
        dto.setTitle(title);
        dto.setType(1);
        option1 = m.group(3);
        option2 = m.group(4);

        Pattern r1 = Pattern.compile(reg_option1);
        Pattern r2 = Pattern.compile(reg_option2);
        Matcher m1 = r1.matcher(option1.trim());
        Matcher m2 = r2.matcher(option2.trim());

        if (m1.find()) {
            ChoiceQuestionOptionEntity optionA = new ChoiceQuestionOptionEntity();
            optionA.setSort(1);
            optionA.setAnswer(m1.group(1).replaceAll(" ", "").trim());
            options.add(optionA);

            ChoiceQuestionOptionEntity optionB = new ChoiceQuestionOptionEntity();
            optionB.setSort(2);
            optionB.setAnswer(m1.group(2).replaceAll(" ", "").trim());
            options.add(optionB);
        }

        if (m2.find()) {
            ChoiceQuestionOptionEntity optionC = new ChoiceQuestionOptionEntity();
            optionC.setSort(3);
            optionC.setAnswer(m2.group(1).replaceAll(" ", "").trim());
            options.add(optionC);

            ChoiceQuestionOptionEntity optionD = new ChoiceQuestionOptionEntity();
            optionD.setSort(4);
            optionD.setAnswer(m2.group(2).replaceAll(" ", "").trim());
            options.add(optionD);
        }

        dto.setOptions(options);

        return dto;
    }

//    @RequestMapping("/enter")
//    @ResponseBody
//    public Result<DemoEntity> enter(Integer id) {
//        Result<DemoEntity> result = new Result<>(Result.STATUS_SUCCESSED);
//
//        if (null == id || 0 == id) {
//            result.setStatus(Result.STATUS_FAILED);
//            result.setMsg("id 不能为空或0");
//            return result;
//        }
//
//        result.setData(this.demoService.getDemo(id));
//        return result;
//    }
//
//    @RequestMapping("/update")
//    @ResponseBody
//    public Result<Boolean> update() throws Exception {
//        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);
//
//        this.demoService.updateDemo();
//
//        result.setData(true);
//        return result;
//    }

}
