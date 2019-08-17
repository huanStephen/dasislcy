package org.eocencle.dasislcy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "subject_question")
public class SubjectQuestionEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 题目类型 1、单选题
     */
    @Column(name = "QUESTION_TYPE")
    private Integer questionType;

    /**
     * 题目ID
     */
    @Column(name = "QUESTION_ID")
    private Integer questionId;

    /**
     * 权重
     */
    @Column(name = "WEIGHT")
    private Integer weight;

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
     * 获取题目类型 1、单选题
     *
     * @return QUESTION_TYPE - 题目类型 1、单选题
     */
    public Integer getQuestionType() {
        return questionType;
    }

    /**
     * 设置题目类型 1、单选题
     *
     * @param questionType 题目类型 1、单选题
     */
    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    /**
     * 获取题目ID
     *
     * @return QUESTION_ID - 题目ID
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * 设置题目ID
     *
     * @param questionId 题目ID
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取权重
     *
     * @return WEIGHT - 权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(Integer weight) {
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