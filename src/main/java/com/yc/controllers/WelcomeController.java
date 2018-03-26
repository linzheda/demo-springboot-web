package com.yc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    @RequestMapping("/login.action")
    public String gotoLogin(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }
    //安全退出
    @RequestMapping(value="/loginOut.action")
    public String loginOut( HttpSession session){
        session.removeAttribute("user");
        return "index";
    }

    @RequestMapping("/toyc.action")
    public String toyc(){
        return "back/welcome";
    }


}
