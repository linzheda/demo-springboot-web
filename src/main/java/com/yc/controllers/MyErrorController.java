package com.yc.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 林哲达
 * @Date: 2018/3/6 20:32
 * 错误页面的拦截404、500等
 */
@Controller
public class MyErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "errorpage/error";
    }
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
