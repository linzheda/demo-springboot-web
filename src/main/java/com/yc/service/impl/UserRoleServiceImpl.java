package com.yc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yc.entity.TempPage;
import com.yc.entity.UserRole;
import com.yc.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yc.dao.BaseDao;


/**
 * 用户角色业务
 * @author shuang
 * Created by shuang on 2016/11/20.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{
	private BaseDao baseDao;

	@Resource(name = "baseDaoImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int updateUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public UserRole findUserRoleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<UserRole> findUserRoleByUserId(int uid) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean saveUserRole(UserRole userRole) {
		int result = this.baseDao.add(userRole, "saveUserRole");

		if(result>0){
			return true;
		}
		return false;
	}


	@Override
	public boolean saveUserRole(UserRole userRole, int[] role_id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public TempPage<UserRole> findUserRoles(int page, int size) {
		Map<String ,Object > map=new HashMap<String ,Object>();
		List<TempPage> list=this.baseDao.findAll(new UserRole() ,map, "findUserRoles");
		return list.get(0);
	}


	@Override
	public int deleteById(int id) {
		UserRole userRole=new UserRole();
		userRole.setId(id);
		return this.baseDao.delete(userRole, "deleteById");
	}


	@Override
	public List<UserRole> findRoleByuid(int user_id) {
		UserRole userRole=new UserRole();
		userRole.setUser_id(user_id);
		return this.baseDao.findAll(userRole, "findRoleByuid");
	}


	@Override
	public int grantUserRole(int uid, int[] rids) {
		List<UserRole> userRoles=new ArrayList<UserRole>();
		UserRole userRole=null;
		for(int rid:rids){
			userRole=new UserRole();
			userRole.setUser_id(uid);
			userRole.setRole_id(rid);
			userRoles.add(userRole);
			this.baseDao.add(userRole, "grantUserRole");
		}
		if(userRoles!=null&&userRoles.size()>0){
			return  1;
		}else{
			return 0;
		}	
	}


	@Override
	public int delRoleGrantByUid(int uid) {
		UserRole userRole=new UserRole();
		userRole.setUser_id(uid);
		return this.baseDao.delete(userRole, "delRoleGrantByUid");
	}

}
