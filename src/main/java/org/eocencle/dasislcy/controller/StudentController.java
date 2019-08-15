package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.StudentEntity;
import org.eocencle.dasislcy.service.IStudentService;
import org.eocencle.dasislcy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生相关控制类
 * @Auther: huanStephen
 * @Date: 2019/5/7
 * @Description:
 */
@RestController
@RequestMapping("/wx/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping("/getStudents")
    public Result<PageAdapter<StudentEntity>> getStudents(Integer classId, Integer currPage, Integer pageSize) {
        Result<PageAdapter<StudentEntity>> result = new Result<>(Result.STATUS_SUCCESSED);

        if (null == classId || 1 > classId) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("班级ID有误！");
            return result;
        }

        if (null == currPage || 1 > currPage || null == pageSize || 1 > pageSize) {
            result.setStatus(Result.STATUS_FAILED);
            result.setMsg("分页有误！");
            return result;
        }

        PageAdapter<StudentEntity> page = new PageAdapter<>();
        page.setCurrPage(currPage);
        page.setPageSize(pageSize);
        page = this.studentService.getStudentsByClassId(classId, page);

        result.setData(page);
        return result;
    }

    @RequestMapping("/addStudent")
    public Result<Boolean> addStudent(StudentEntity student) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.studentService.addStudent(student);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/updateStudent")
    public Result<Boolean> updateStudent(StudentEntity student) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.studentService.updateStudent(student);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

    @RequestMapping("/delStudent")
    public Result<Boolean> delStudent(Integer studentId) {
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        this.studentService.removeStudentById(studentId);

        result.setData(true);
        result.setMsg("请求成功！");
        return result;
    }

}
