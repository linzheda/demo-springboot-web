package com.yc.service;

import java.util.List;

import com.yc.entity.Role;
import com.yc.entity.RoleAndFunction;
import com.yc.entity.TempPage;
import org.springframework.stereotype.Repository;


/**
 * 角色功能接口
 * @author shuang
 * Created by shuang on 2016/11/20.
 */
@Repository
public interface RoleService {
	
    /**
     * 根据用户id查找属于他角色
     * @param id id
     * @return
     */
	List<Role> findTwoFunctionById(int uid);

    /**
     *  保存角色 
     * @param role role角色
     */
    int saveRole(Role role);

    /**
     * 根据id删除角色
     * @param roleid    角色的id
     */
    int deleteRoleById(int rid) throws RuntimeException;
     

    /**
     * 根据role对象来更新Role
     * @param role role对象
     */
    int updateRole(Role role);
     

    /**
	 * 分页查询
	 * @param page
	 * @param size
	 * @return
	 */
    TempPage<RoleAndFunction> findRolesByPages(int page, int size);

    /**
     * 添加角色，同时给角色赋予指定的权限
     * @param fids
     * @param rname
    * @param uid
     * @return
     */
	boolean addRole(String fids, String rname, int uid);

	/**
	 * 根据用户id来查询属于它角色信息
	 * @param uid
	 * @return
	 */
	List<Role> selRoles(int uid);

}
