package com.yc.entity;

import java.io.Serializable;

public class UserAndRole implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;//用户id
    private String userName;//用户名称
    private String password;//用户密码
    private String registertime;//注册时间
    private String updatetime;//最后修改时间
    private String rname;//角色名
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegistertime() {
		return registertime;
	}
	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "UserAndRole [id=" + id + ", userName=" + userName + ", password=" + password + ", registertime="
				+ registertime + ", updatetime=" + updatetime + ", rname=" + rname + "]";
	}
    
    
}
