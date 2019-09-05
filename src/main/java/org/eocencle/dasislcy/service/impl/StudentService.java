package org.eocencle.dasislcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eocencle.dasislcy.component.PageAdapter;
import org.eocencle.dasislcy.dao.ClassMapper;
import org.eocencle.dasislcy.dao.StudentMapper;
import org.eocencle.dasislcy.entity.ClassEntity;
import org.eocencle.dasislcy.entity.StudentEntity;
import org.eocencle.dasislcy.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学生service
 * @Auther: huanStephen
 * @Date: 2019/4/28
 * @Description:
 */
@Service
public class StudentService implements IStudentService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ClassMapper classMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private StudentMapper studentMapper;

    @Override
    public StudentEntity getStudentById(Integer id) {
        return this.studentMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void addStudent(StudentEntity student) {
        this.studentMapper.insertSelective(student);

        ClassEntity clazz = this.classMapper.selectByPrimaryKey(student.getClassId());
        if (null == clazz.getCnt()) {
            clazz.setCnt(1);
        } else {
            clazz.setCnt(clazz.getCnt() + 1);
        }
        this.classMapper.updateByPrimaryKeySelective(clazz);
    }

    @Override
    @Transactional
    public void removeStudentById(Integer id) {
        StudentEntity student = this.studentMapper.selectByPrimaryKey(id);
        this.studentMapper.deleteByPrimaryKey(id);

        ClassEntity clazz = this.classMapper.selectByPrimaryKey(student.getClassId());
        clazz.setCnt(clazz.getCnt() - 1);
        this.classMapper.updateByPrimaryKeySelective(clazz);
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
