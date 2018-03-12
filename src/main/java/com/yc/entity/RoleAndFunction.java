package com.yc.entity;

import java.io.Serializable;

public class RoleAndFunction implements Serializable {
	private static final long serialVersionUID = -3566166441693958855L;

    private int rid;//角色id
    private String rname;//角色名称
    private String addtime;//添加时间
    private String updatetime;//最后修改时间
    private String fname;//资源权限名称
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	@Override
	public String toString() {
		return "RoleAndFunction [rid=" + rid + ", rname=" + rname + ", addtime=" + addtime + ", updatetime="
				+ updatetime + ", fname=" + fname + "]";
	}
    
}
