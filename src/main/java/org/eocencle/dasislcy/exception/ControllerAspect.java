package org.eocencle.dasislcy.exception;

import org.eocencle.dasislcy.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制层异常捕获
 * @Auther: huanStephen
 * @Date: 2018/12/3 20:25
 * @Description:
 */
@ControllerAdvice
public class ControllerAspect {

    @ResponseBody
    @ExceptionHandler()
    public Result<?> testException(Exception e) {
        Result<?> result = new Result<>(Result.STATUS_FAILED);
        e.printStackTrace();
        result.setMsg("系统异常！");
        return result;
    }

}
