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
     * 是否是单选题，1、是 2、否
     */
    @Column(name = "TYPE")
    private Boolean type;

    /**
     * 题干
     */
    @Column(name = "TITLE")
    private String title;

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
     * 获取是否是单选题，1、是 2、否
     *
     * @return TYPE - 是否是单选题，1、是 2、否
     */
    public Boolean getType() {
        return type;
    }

    /**
     * 设置是否是单选题，1、是 2、否
     *
     * @param type 是否是单选题，1、是 2、否
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * 获取题干
     *
     * @return TITLE - 题干
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置题干
     *
     * @param title 题干
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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