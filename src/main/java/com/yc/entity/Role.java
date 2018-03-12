package com.yc.entity;


import java.io.Serializable;

/**
 * 角色实体类
 * @author shuang
 *Created by shuang on 2016/11/20.
 */
public class Role  implements Serializable {

	private static final long serialVersionUID = -3566166441693958855L;

    private int rid;//角色id
    private String rname;//角色名称
    private String addtime;//添加时间
    private String updatetime;//最后修改时间
    
    
	public Role() {
	}

	public Role(String rname) {
		super();
		this.rname = rname;
	}

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
		/*if(updatetime==null||updatetime==""){
			return "未修改过";
		}*/
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + ", addtime=" + addtime
				+ ", updatetime=" + updatetime + "]";
	}
    
}