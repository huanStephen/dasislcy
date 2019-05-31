package org.eocencle.dasislcy.vo;

/**
 * 前端返回结果类
 * @Auther: huanStephen
 * @Date: 2018/12/3
 * @Description:
 */
public class Result<T> {

    // 状态
    private Integer status;

    // 成功
    public static final Integer STATUS_SUCCESSED = 1;
    // 失败
    public static final Integer STATUS_FAILED = 0;

    // 消息
    private String msg;

    // 数据
    private T data;

    public Result(Integer status) {
        this.status = status;
        this.msg = "请求成功！";
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
