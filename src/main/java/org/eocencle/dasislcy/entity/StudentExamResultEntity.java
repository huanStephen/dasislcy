package org.eocencle.dasislcy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "student_exam_result")
public class StudentExamResultEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学生试卷ID
     */
    @Column(name = "STUDENT_EXAMPAPER_ID")
    private Integer studentExampaperId;

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
    @Column(name = "EXAMQUESTION_ID")
    private Integer examquestionId;

    /**
     * 标准答案
     */
    @Column(name = "ANSWER")
    private Integer answer;

    /**
     * 实际答案
     */
    @Column(name = "ACTUAL")
    private Integer actual;

    /**
     * 是否正确 1、正确 2、错误
     */
    @Column(name = "HAS_TRUE")
    private Integer hasTrue;

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
     * 获取学生试卷ID
     *
     * @return STUDENT_EXAMPAPER_ID - 学生试卷ID
     */
    public Integer getStudentExampaperId() {
        return studentExampaperId;
    }

    /**
     * 设置学生试卷ID
     *
     * @param studentExampaperId 学生试卷ID
     */
    public void setStudentExampaperId(Integer studentExampaperId) {
        this.studentExampaperId = studentExampaperId;
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
     * 获取标准答案
     *
     * @return ANSWER - 标准答案
     */
    public Integer getAnswer() {
        return answer;
    }

    /**
     * 设置标准答案
     *
     * @param answer 标准答案
     */
    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    /**
     * 获取实际答案
     *
     * @return ACTUAL - 实际答案
     */
    public Integer getActual() {
        return actual;
    }

    /**
     * 设置实际答案
     *
     * @param actual 实际答案
     */
    public void setActual(Integer actual) {
        this.actual = actual;
    }

    /**
     * 获取是否正确 1、正确 2、错误
     *
     * @return HAS_TRUE - 是否正确 1、正确 2、错误
     */
    public Integer getHasTrue() {
        return hasTrue;
    }

    /**
     * 设置是否正确 1、正确 2、错误
     *
     * @param hasTrue 是否正确 1、正确 2、错误
     */
    public void setHasTrue(Integer hasTrue) {
        this.hasTrue = hasTrue;
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