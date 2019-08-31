package org.eocencle.dasislcy.entity;

import javax.persistence.*;

@Table(name = "question_score")
public class QuestionScoreEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型：1、选择题
     */
    @Column(name = "QUESTION_TYPE")
    private Integer questionType;

    /**
     * 分数
     */
    @Column(name = "SCORE")
    private Integer score;

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
     * 获取类型：1、选择题
     *
     * @return QUESTION_TYPE - 类型：1、选择题
     */
    public Integer getQuestionType() {
        return questionType;
    }

    /**
     * 设置类型：1、选择题
     *
     * @param questionType 类型：1、选择题
     */
    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    /**
     * 获取分数
     *
     * @return SCORE - 分数
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置分数
     *
     * @param score 分数
     */
    public void setScore(Integer score) {
        this.score = score;
    }
}