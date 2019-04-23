package org.eocencle.dasislcy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "question_outline")
public class QuestionOutlineEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 题型 1、选择题
     */
    @Column(name = "QUESTION_TYPE")
    private Byte questionType;

    /**
     * 试题ID
     */
    @Column(name = "QUESTION_ID")
    private Integer questionId;

    /**
     * 大纲ID
     */
    @Column(name = "OUTLINE_ID")
    private Integer outlineId;

    /**
     * 权重
     */
    @Column(name = "WEIGHT")
    private Byte weight;

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
     * 获取题型 1、选择题
     *
     * @return QUESTION_TYPE - 题型 1、选择题
     */
    public Byte getQuestionType() {
        return questionType;
    }

    /**
     * 设置题型 1、选择题
     *
     * @param questionType 题型 1、选择题
     */
    public void setQuestionType(Byte questionType) {
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
     * 获取权重
     *
     * @return WEIGHT - 权重
     */
    public Byte getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(Byte weight) {
        this.weight = weight;
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