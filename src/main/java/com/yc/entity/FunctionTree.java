package com.yc.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 结合easyui数据格式生成的功能树实体
 * @author shuang
 *Created by shuang on 2016/11/20.
 */
public class FunctionTree implements Serializable {

	private static final long serialVersionUID = -4041911414004494063L;
	
	private int id;//功能id
	private String text;//功能名称
	private int checked;//是否被选中
	private List<FunctionTree> children;//孩子节点集合
	
	public FunctionTree() {
		
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<FunctionTree> getChildren() {
		return children;
	}
	public void setChildren(List<FunctionTree> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "FunctionTree [id=" + id + ", text=" + text + ", checked=" + checked + ", children=" + children + "]";
	}
	
}
