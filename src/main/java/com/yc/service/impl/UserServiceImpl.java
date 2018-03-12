package com.yc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yc.entity.TempPage;
import com.yc.entity.User;
import com.yc.service.UserService;
import com.yc.util.JsonMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yc.dao.BaseDao;
import com.yc.util.JsonMode;


/**
 * 用户业务
 * @author lzd
 * Created by shuang on 2016/11/20.
 */
@Service
public class UserServiceImpl implements UserService {

	private BaseDao baseDao;

	@Resource(name = "baseDaoImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public User getUser(String name, String pwd) {
		User user=new User();
		user.setUserName(name);
		user.setPassword(pwd);
		List<User> list=this.baseDao.findAll(user, "login");
		return 	list.get(0);
	}

	@Override
	public int save(User user) {
		int i=this.baseDao.add(user, "save");
		return i;
	}

	@Override
	public int deleteById(int uid) {
		User user=new User();
		user.setId(uid);
		int result=this.baseDao.delete(user, "deleteById");
		return result;
	}

	@Override
	public int update(User user) {
		int result=this.baseDao.update(user, "update");
		return result;
	}

	@Override
	public User fingById(int uid) {
		User user=new User();
		user.setId(uid);
		List<User>list=this.baseDao.findAll(user, "findById");
		return list.get(0);
	}

	@Override
	public TempPage<User> findPage(int page,int size) {
		Map<String,Integer>map=new HashMap<String,Integer>();
		map.put("oage", page);
		map.put("size", size);
		List<TempPage>list=this.baseDao.findAll(new User(), map, "findPage");
		return list.get(0);
	}

	@Override
	public JsonMode changepassword(User user, String oldpassword, String newpassword) {
		JsonMode jm=new JsonMode();
		
		if(user.getPassword().equals(oldpassword)){
			user.setPassword(newpassword);
			int result=this.baseDao.update(user, "update");
			if(result>0){
				jm.setCode(1);
				jm.setObj("修改密码成功");
			}else{
				jm.setCode(0);
				jm.setError("修改失败");
			}
		}else{
			jm.setCode(0);
			jm.setError("原密码错误");
		}
		return jm;
	}

	//查询权限啊按钮的id
	@Override
	public List<String> findButtonId(User user) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", user.getId());
		List<String>list=this.baseDao.findAll(new User(),map, "findButtonId");
		
		return list;
	}



}
