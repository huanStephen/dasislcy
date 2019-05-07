package org.eocencle.dasislcy.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: huanStephen
 * @Date: 2019/3/21 10:39
 * @Description:
 */

@Controller
public class AppErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (401 == statusCode){
            return "error/401";
        } else if (403 == statusCode){
            return "error/403";
        } else if (404 == statusCode){
            return "error/404";
        }else{
            return "error/500";
        }
    }

}
