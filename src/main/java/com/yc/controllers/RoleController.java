package com.yc.controllers;

import com.yc.entity.Role;
import com.yc.entity.RoleAndFunction;
import com.yc.entity.TempPage;
import com.yc.entity.User;
import com.yc.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 林哲达
 * @Date: 2018/3/9 16:59
 */
@Controller
@RequestMapping("back/role")
@SessionAttributes("user")
public class RoleController {
    @Resource(name="roleServiceImpl")
    private RoleService roleservice;

    /**
     * 跳转到角色管理页面
     * @return
     */
    @RequestMapping("/toRoleManager.action")
    private String toRoleManager(){
        return "back/access/toRoleManager";
    }


    /**
     * 根据用户id查找他创建的角色
     * @param user ：登陆的用户
     * @param model
     * @return
     */
    @RequestMapping("/Roles.action")
    @ResponseBody
    public List<Role> Roles(User user, Model model) {
        List<Role> roles=roleservice.findTwoFunctionById(user.getId());
        return roles;
    }
    /**
     * 分页查找角色信息
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/findRolesByPages.action")
    @ResponseBody
    public TempPage<RoleAndFunction> findRolesByPages(int page, int rows){
        TempPage<RoleAndFunction>result= roleservice.findRolesByPages((page-1)*rows,rows);
        if(result==null){
            result=new TempPage<RoleAndFunction>();
            result.setTotal(0);
            result.setRows(new ArrayList<RoleAndFunction>());
        }
        return result;
    }
    /**
     * 根据角色id删除角色
     * @param rid
     * @return
     */
    @RequestMapping("/delRole.action")
    @ResponseBody
    public int delRole(int rid){
        int result= 0;
        try {
            result = roleservice.deleteRoleById(rid);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result=0;
        }
        return result;
    }
    /**
     * 增加角色，以及添加对于功能
     * @param user
     * @param fids
     * @param rname
     * @return
     */
    @RequestMapping("/addRole.action")
    @ResponseBody
    public boolean addRole(User user,String fids,String rname){
        boolean result1= roleservice.addRole(fids,rname,user.getId());
        return result1;
    }
    /**
     * 查询用户拥有的角色
     * @param user
     * @return
     */
    @RequestMapping("/selRoles.action")
    @ResponseBody
    public List<Role> selRoles(User user){
        List<Role> result=roleservice.selRoles(user.getId());
        if(result==null){
            return new ArrayList<Role>();
        }
        return result;
    }


}
