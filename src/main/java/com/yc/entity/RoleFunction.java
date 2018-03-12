package com.yc.entity;


import java.io.Serializable;

/**
 * 角色功能关系实体类
 * @author shuang
 *Created by shuang on 2016/11/20.
 */
public class RoleFunction implements Serializable {

	private static final long serialVersionUID = -6331073813620528316L;
	
	private int id;//主键id
    private int role_id;//角色id
    private int function_id;//功能id
    private int status;//状态1可用；0不可用
    
	
	public RoleFunction() {
		
	}
	public RoleFunction(int role_id, int function_id) {
		this.role_id = role_id;
		this.function_id = function_id;
	}
	
	public int getId() {
		return id;

	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public int getFunction_id() {
		return function_id;
	}
	public void setFunction_id(int function_id) {
		this.function_id = function_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "RoleFunction [id=" +id + ", role_id=" + role_id
				+ ", function_id=" + function_id + ", status=" + status + "]";
	}

}
