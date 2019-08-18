package org.eocencle.dasislcy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "examquestion")
public class ExamquestionEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 试卷ID
     */
    @Column(name = "EXAMPAPER_ID")
    private Integer exampaperId;

    /**
     * 顺序
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 题型 1、选择题
     */
    @Column(name = "QUESTION_TYPE")
    private Integer questionType;

    /**
     * 试题ID
     */
    @Column(name = "QUESTION_ID")
    private Integer questionId;

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
     * 获取顺序
     *
     * @return SORT - 顺序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置顺序
     *
     * @param sort 顺序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取题型 1、选择题
     *
     * @return QUESTION_TYPE - 题型 1、选择题
     */
    public Integer getQuestionType() {
        return questionType;
    }

    /**
     * 设置题型 1、选择题
     *
     * @param questionType 题型 1、选择题
     */
    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    /**
     * 获取试题ID
     *
     * @return QUESTION_ID - 试题ID
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * 设置试题ID
     *
     * @param questionId 试题ID
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
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