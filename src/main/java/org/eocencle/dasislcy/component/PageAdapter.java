package org.eocencle.dasislcy.component;

import java.util.List;

/**
 * 分页适配器
 * @Auther: huanStephen
 * @Date: 2019/4/25
 * @Description:
 */
public class PageAdapter<T> {

    // 当前页
    private Integer currPage;

    // 显示条数
    private Integer pageSize;

    // 总条数
    private Integer total;

    // 数据列表
    private List<T> list;

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
