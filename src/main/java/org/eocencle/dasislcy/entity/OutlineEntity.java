package org.eocencle.dasislcy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "outline")
public class OutlineEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 科目ID
     */
    @Column(name = "SUBJECT_ID")
    protected Integer subjectId;

    /**
     * 章节ID
     */
    @Column(name = "CHAPTER_ID")
    protected Integer chapterId;

    /**
     * 顺序
     */
    @Column(name = "SORT")
    protected Byte sort;

    /**
     * 标题
     */
    @Column(name = "TITLE")
    protected String title;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    protected String description;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    protected Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    protected Date updateTime;

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
     * 获取顺序
     *
     * @return SORT - 顺序
     */
    public Byte getSort() {
        return sort;
    }

    /**
     * 设置顺序
     *
     * @param sort 顺序
     */
    public void setSort(Byte sort) {
        this.sort = sort;
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
     * 获取描述
     *
     * @return DESCRIPTION - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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