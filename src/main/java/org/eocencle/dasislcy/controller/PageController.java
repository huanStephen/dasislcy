package org.eocencle.dasislcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 * @Auther: huanStephen
 * @Date: 2019/4/25 08:27
 * @Description:
 */
@Controller
public class PageController {

    @RequestMapping(value = "/to{page}")
    public String toPage(@PathVariable String page) {
        return page.substring(0, 1).toLowerCase() + page.substring(1);
    }

}
