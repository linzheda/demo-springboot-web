package com.yc.controllers;

import com.yc.entity.TempPage;
import com.yc.entity.UserRole;
import com.yc.service.UserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 林哲达
 * @Date: 2018/3/9 17:16
 */

@Controller
@RequestMapping("back/userRole")
public class UserRoleController {
    @Resource(name="userRoleServiceImpl")
    private UserRoleService userRoleservice;

    /**
     * 跳转到用户授权页面
     * @return
     */
    @RequestMapping("/toUserGrantManager.action")
    public String toUserGrantManager(){
        return "back/access/toUserGrantManager";
    }


    //获取分页信息
    @RequestMapping("/findUserRole.action")
    @ResponseBody
    public TempPage<UserRole> findUserRole(int page, int rows) {
        TempPage<UserRole> userRoles = userRoleservice.findUserRoles((page-1)*rows,rows);
        return userRoles;
    }

    //删除数据
    @ResponseBody
    @RequestMapping("/remove.action")
    public int remove(int id){
        int result=userRoleservice.deleteById(id);
        return result;
    }

    //删除数据
    @ResponseBody
    @RequestMapping("/delRoleGrant.action")
    public boolean delRoleGrantByUid(int uid){
        int result=userRoleservice.delRoleGrantByUid(uid);
        if(result>0){
            return true;
        }
        return false;
    }
    //根据传来的uid获取该用户的角色信息
    @RequestMapping("/findRoleByuid.action")
    @ResponseBody
    public List<UserRole> findRoleByuid(int user_id, Model model) {
        List<UserRole> userRoles = userRoleservice.findRoleByuid(user_id);
        return userRoles;
    }
    /**
     * 通过角色赋予用户权限
     * @param uid：用户Id
     * @param rids：角色id数组
     * @return
     */
    @RequestMapping("/grantUserRole.action")
    @ResponseBody
    public boolean grantUserRole(int uid ,int[] rids){
        if(userRoleservice.grantUserRole(uid,rids)>0){
            return true;
        }
        return false;

    }
}
