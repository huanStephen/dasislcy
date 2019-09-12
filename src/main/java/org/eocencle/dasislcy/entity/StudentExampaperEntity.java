package org.eocencle.dasislcy.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "student_exampaper")
public class StudentExampaperEntity {
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
     * 试卷ID
     */
    @Column(name = "EXAMPAPER_ID")
    private Integer exampaperId;

    /**
     * 考试结果记录
     */
    @Column(name = "RESULT_RECORD")
    private String resultRecord;

    /**
     * 考试大纲记录
     */
    @Column(name = "OUTLINE_RECORD")
    private String outlineRecord;

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
     * 获取考试结果记录
     *
     * @return RESULT_RECORD - 考试结果记录
     */
    public String getResultRecord() {
        return resultRecord;
    }

    /**
     * 设置考试结果记录
     *
     * @param resultRecord 考试结果记录
     */
    public void setResultRecord(String resultRecord) {
        this.resultRecord = resultRecord == null ? null : resultRecord.trim();
    }

    /**
     * 获取考试大纲记录
     *
     * @return OUTLINE_RECORD - 考试大纲记录
     */
    public String getOutlineRecord() {
        return outlineRecord;
    }

    /**
     * 设置考试大纲记录
     *
     * @param outlineRecord 考试大纲记录
     */
    public void setOutlineRecord(String outlineRecord) {
        this.outlineRecord = outlineRecord == null ? null : outlineRecord.trim();
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