package com.yc.controllers;

import com.yc.service.RoleFunctionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: 林哲达
 * @Date: 2018/3/9 17:07
 */
@Controller
@RequestMapping("back/roleFunction")
public class RoleFunctionController {

    @Resource(name="roleFunctionServiceImpl")
    private RoleFunctionService roleFunctionservice;

    /**
     * 修改角色的权限功能，（想法是先删除原有的权限再进行添加）
     * @param rid：角色id
     * @param fids功能id字符串数组
     * @return
     */
    @RequestMapping("/updateRoleGrant.action")
    @ResponseBody
    public boolean updateRoleGrant(int rid ,String fids){
        return roleFunctionservice.updateRoleGrant(rid,fids);
    }


}
