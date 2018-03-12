package com.yc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 林哲达
 * @Date: 2018/3/10 11:06
 */
@Controller
public class WelcomeController {

    /**
     * 进入登录页面
     * @return 跳转到login.html
     */
    @RequestMapping("/login")
    public String gotoLogin(){
        return "login";
    }

    @RequestMapping("/toyc.action")
    public String toyc(){
        return "welcome";
    }
}
