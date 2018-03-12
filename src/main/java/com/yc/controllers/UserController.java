package com.yc.controllers;

import com.yc.entity.TempPage;
import com.yc.entity.User;
import com.yc.service.UserService;
import com.yc.util.Encrypt;
import com.yc.util.SessionAttributeKey;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *  @Author: 林哲达
 *  @Date: 2018/3/6 19:30
 *
 */
@Controller
@RequestMapping("/back/user")
@SessionAttributes("user")
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userservice;



    /**
     * 登录
     * @param user
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/login.action")
    public String doLogin(User user, Model model, HttpSession session){

        if(user.getUserName()==null&&user.getPassword()==null){
            model.addAttribute("errorMsg", "用户名或密码不能为空...");
            return "back/error";
        }
        String md5Password= Encrypt.md5AndSha(user.getPassword());
        try {
            user = userservice.getUser(user.getUserName(), md5Password);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "用户名或密码错误...");
            return "back/error";
        }
        if (user!=null) {
            model.addAttribute(SessionAttributeKey.LOGIN_ADMIN, user);
            session.setAttribute(SessionAttributeKey.LOGIN_ADMIN, user);
            //TODO:这里把用户有哪些按钮查出来存到session
            //这里查出所有的按钮列表
            List<String> list=userservice.findButtonId(user);
            String[] array=new String[list.size()];
            for(int i=0,len=list.size();i<len;i++){
                array[i]=list.get(i);
            }
            session.setAttribute(SessionAttributeKey.USER_BUTTON,list);
            return "back/mainManager";
        } else {
            model.addAttribute("errorMsg", "用户名或密码错误...");
            return "back/error";
        }
    }

    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping("/toUserManager.action")
    public String toUserManager(){
        return "back/access/toUserManager";
    }


    //获取分页信息
    @RequestMapping("/findUser.action")
    @ResponseBody
    public TempPage<User> findUser(int page, int rows) {
        TempPage<User> users = userservice.findPage((page-1)*rows,rows);
        if(users==null){
            users=new TempPage<User>();
            users.setRows(new ArrayList<User>());
            users.setTotal(0);
        }
        return users;
    }

    //添加数据
    @ResponseBody
    @RequestMapping("/addUser.action")
    public String  addUser(String userName,String password) {
        User user=new User();
        user.setUserName(userName);
        user.setPassword(Encrypt.md5AndSha(password));
        int result = userservice.save(user);
        return String.valueOf(result);
    }

    //删除数据
    @ResponseBody
    @RequestMapping("/removeUser.action")
    public int removeUser(int id){
        //System.out.println("要删除的id"+uid);
        int result=userservice.deleteById(id);
        return result;
    }

    //修改数据
    @ResponseBody
    @RequestMapping(value= "/updateUser.action",method= RequestMethod.POST)
    public int updataUser(int id,String userName,String password) {
        String passwords=Encrypt.md5AndSha(password);
        User user=new User(id,userName,passwords);
        int result=userservice.update(user);
        return result;
    }

    //获取某一个用户
    @ResponseBody
    @RequestMapping(value= "/fingById.action",method=RequestMethod.POST)
    public User fingById(int id) {
        User user=userservice.fingById(id);
        return user;
    }



}
