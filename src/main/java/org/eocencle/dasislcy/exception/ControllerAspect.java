package org.eocencle.dasislcy.exception;

import org.eocencle.dasislcy.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: shizh26250
 * @Date: 2018/12/3 20:25
 * @Description:
 */
@ControllerAdvice
public class ControllerAspect {

    @ResponseBody
    @ExceptionHandler()
    public Result<?> testException(Exception e) {
        Result<?> result = new Result<>(Result.STATUS_FAILED);
        result.setMsg("系统异常！");
        return result;
    }

}
