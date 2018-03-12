package com.yc.entity;

import java.io.Serializable;
/**
 * 功能实体类
 * @author shuang
 *Created by shuang on 2016/11/20.
 */

public class Function implements Serializable{
	
	private static final long serialVersionUID = -8889995540070510045L;
	
	private int fid;//功能id
	private String fname;//功能名称
	private int parent_id;//父节点id
	private String url;//链接地址
	private Integer serialNum;//子节点排序
	private Integer accordion;//目录级别
	private String status;//状态
	private String buttonid;//按钮id
	
	
	public Function() {
	
	}
	
	public Function(int fid, String fname, String url,String status) {
		this.fid = fid;
		this.fname = fname;
		this.url = url;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
			this.status = status;
	}

	public int getFid() {
		return fid;
	}
	public int getid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public String getText() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}
	public Integer getAccordion() {
		return accordion;
	}
	public void setAccordion(Integer accordion) {
		this.accordion = accordion;
	}

	public String getButtonid() {
		return buttonid;
	}

	public void setButtonid(String buttonid) {
		this.buttonid = buttonid;
	}

	@Override
	public String toString() {
		return "Function [fid=" + fid + ", fname=" + fname + ", parent_id=" + parent_id + ", url=" + url
				+ ", serialNum=" + serialNum + ", accordion=" + accordion + ", status=" + status + ", buttonid="
				+ buttonid + "]";
	}

	
	
}
