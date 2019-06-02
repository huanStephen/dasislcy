package org.eocencle.dasislcy.dto;

import org.eocencle.dasislcy.entity.OutlineEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 大纲DTO
 * @Auther: huanStephen
 * @Date: 2019/5/30
 * @Description:
 */
public class OutlineDto extends OutlineEntity {
    // 子大纲
    private List<OutlineEntity> children = new ArrayList<>();

    public OutlineDto() {

    }

    public OutlineDto(OutlineEntity outline) {
        this.id = outline.getId();
        this.subjectId = outline.getSubjectId();
        this.parentId = outline.getParentId();
        this.sort = outline.getSort();
        this.title = outline.getTitle();
        this.description = outline.getDescription();
        this.createTime = outline.getCreateTime();
        this.updateTime = outline.getUpdateTime();
    }

    public List<OutlineEntity> getChildren() {
        return children;
    }

    public void setChildren(List<OutlineEntity> children) {
        this.children = children;
    }
}
