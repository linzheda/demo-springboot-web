package com.yc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yc.entity.RoleFunction;
import com.yc.service.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yc.dao.BaseDao;


/**
 * 角色功能业务
 * @author shuang
 * Created by shuang on 2016/11/20.
 */
@Service
public class RoleFunctionServiceImpl implements RoleFunctionService {

	private BaseDao baseDao;

	@Resource(name = "baseDaoImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}



	@Override
	public List<RoleFunction> findRoleFunctionsByRoleId(int rid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByRoleId(int rid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean saveRoleFunction(RoleFunction roleFunction) {
		// TODO Auto-generated method stub
		//return roleFunctionMapper.saveRoleFunction(roleFunction);
		return true;
	}

	@Override
	public int saveRoleFunction(List<RoleFunction> role_Function_list) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", role_Function_list);
		return this.baseDao.add(new RoleFunction(), map, "saveRoleFunction");
	}

	@Override
	public boolean updateRoleGrant(int rid, String fids) {
		RoleFunction roleFunctions=new RoleFunction();
		roleFunctions.setRole_id(rid);
		
		int result = this.baseDao.update(roleFunctions, "deleteByRoleId");
		if(result>0){
			List<RoleFunction> list=new ArrayList<RoleFunction>();
			RoleFunction roleFunction=null;
			String[] strs = fids.split(",");
			int j=strs.length;
			for(int i=0;i<j;i++){
				roleFunction=new RoleFunction(rid,Integer.parseInt( strs[i]));
				list.add(roleFunction);
			}
			int result3 = saveRoleFunction(list);//添加更新的角色权限关系
			if(j==result3){
				return true;
			}
		}
		return false;
	}

	@Override
	public RoleFunction findRoleFunctionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
