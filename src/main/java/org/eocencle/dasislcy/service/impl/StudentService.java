package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.StudentMapper;
import org.eocencle.dasislcy.entity.StudentEntity;
import org.eocencle.dasislcy.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生service
 *
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class StudentService implements IStudentService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private StudentMapper studentMapper;

    @Override
    public StudentEntity getStudentById(Integer id) {
        return this.studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addStudent(StudentEntity student) {
        this.studentMapper.insertSelective(student);
    }

    @Override
    public void removeStudentById(Integer id) {
        this.studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateStudent(StudentEntity student) {
        this.studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public PageAdapter<StudentEntity> getStudentsByClassId(Integer classId, PageAdapter<StudentEntity> page) {
        StudentEntity record = new StudentEntity();
        record.setClassId(classId);

        PageHelper.startPage(page.getCurrPage(), page.getPageSize());
        List<StudentEntity> list = this.studentMapper.select(record);
        PageInfo<StudentEntity> info = new PageInfo<StudentEntity>(list);
        page.setList(list);
        page.setTotal(new Long(info.getTotal()).intValue());

        return page;
    }
}
