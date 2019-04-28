package org.eocencle.dasislcy.service;

import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.entity.StudentEntity;

/**
 * 学生service
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public interface IStudentService {

    /**
     * 获取学生
     * @param id
     * @return
     */
    StudentEntity getStudentById(Integer id);

    /**
     * 添加学生
     * @param student
     */
    void addStudent(StudentEntity student);

    /**
     * 删除学生
     * @param id
     */
    void removeStudentById(Integer id);

    /**
     * 更新学生
     * @param student
     */
    void updateStudent(StudentEntity student);

    /**
     * 根据班级id获取学生
     * @param classId
     * @param page
     * @return
     */
    PageAdapter<StudentEntity> getStudentsByClassId(Integer classId, PageAdapter<StudentEntity> page);
}
