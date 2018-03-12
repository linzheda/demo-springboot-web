package com.yc.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 * @author shuang
 *Created by shuang on 2016/11/20.
 */
@Data
public class User implements Serializable {
  
	private static final long serialVersionUID = -7022019403547512027L;
	
	private int id;//用户id
    private String userName;//用户名称
    private String password;//用户密码
    private String registertime;//注册时间
    private String updatetime;//最后修改时间

	public User(int id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public User() {
	}
}
