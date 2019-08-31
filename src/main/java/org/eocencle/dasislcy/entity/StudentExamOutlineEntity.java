package org.eocencle.dasislcy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "student_exam_outline")
public class StudentExamOutlineEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学生ID
     */
    @Column(name = "STUDENT_ID")
    private Integer studentId;

    /**
     * 科目ID
     */
    @Column(name = "SUBJECT_ID")
    private Integer subjectId;

    /**
     * 章节ID
     */
    @Column(name = "CHAPTER_ID")
    private Integer chapterId;

    /**
     * 大纲ID
     */
    @Column(name = "OUTLINE_ID")
    private Integer outlineId;

    /**
     * 试卷ID
     */
    @Column(name = "EXAMPAPER_ID")
    private Integer exampaperId;

    /**
     * 试题ID
     */
    @Column(name = "EXAMQUESTION_ID")
    private Integer examquestionId;

    /**
     * 应得分数(权重分)
     */
    @Column(name = "DESERVED")
    private Integer deserved;

    /**
     * 实得分数(权重分)
     */
    @Column(name = "ACTUALLY")
    private Integer actually;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学生ID
     *
     * @return STUDENT_ID - 学生ID
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 设置学生ID
     *
     * @param studentId 学生ID
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取科目ID
     *
     * @return SUBJECT_ID - 科目ID
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * 设置科目ID
     *
     * @param subjectId 科目ID
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取章节ID
     *
     * @return CHAPTER_ID - 章节ID
     */
    public Integer getChapterId() {
        return chapterId;
    }

    /**
     * 设置章节ID
     *
     * @param chapterId 章节ID
     */
    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    /**
     * 获取大纲ID
     *
     * @return OUTLINE_ID - 大纲ID
     */
    public Integer getOutlineId() {
        return outlineId;
    }

    /**
     * 设置大纲ID
     *
     * @param outlineId 大纲ID
     */
    public void setOutlineId(Integer outlineId) {
        this.outlineId = outlineId;
    }

    /**
     * 获取试卷ID
     *
     * @return EXAMPAPER_ID - 试卷ID
     */
    public Integer getExampaperId() {
        return exampaperId;
    }

    /**
     * 设置试卷ID
     *
     * @param exampaperId 试卷ID
     */
    public void setExampaperId(Integer exampaperId) {
        this.exampaperId = exampaperId;
    }

    /**
     * 获取试题ID
     *
     * @return EXAMQUESTION_ID - 试题ID
     */
    public Integer getExamquestionId() {
        return examquestionId;
    }

    /**
     * 设置试题ID
     *
     * @param examquestionId 试题ID
     */
    public void setExamquestionId(Integer examquestionId) {
        this.examquestionId = examquestionId;
    }

    /**
     * 获取应得分数(权重分)
     *
     * @return DESERVED - 应得分数(权重分)
     */
    public Integer getDeserved() {
        return deserved;
    }

    /**
     * 设置应得分数(权重分)
     *
     * @param deserved 应得分数(权重分)
     */
    public void setDeserved(Integer deserved) {
        this.deserved = deserved;
    }

    /**
     * 获取实得分数(权重分)
     *
     * @return ACTUALLY - 实得分数(权重分)
     */
    public Integer getActually() {
        return actually;
    }

    /**
     * 设置实得分数(权重分)
     *
     * @param actually 实得分数(权重分)
     */
    public void setActually(Integer actually) {
        this.actually = actually;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}