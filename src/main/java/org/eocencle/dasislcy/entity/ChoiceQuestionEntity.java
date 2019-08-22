package org.eocencle.dasislcy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "choice_question")
public class ChoiceQuestionEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型：1、选择题
     */
    @Column(name = "TYPE")
    private Integer type;

    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 标准答案
     */
    @Column(name = "ANSWER")
    private Integer answer;

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
     * 获取类型：1、选择题
     *
     * @return TYPE - 类型：1、选择题
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型：1、选择题
     *
     * @param type 类型：1、选择题
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取标题
     *
     * @return TITLE - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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