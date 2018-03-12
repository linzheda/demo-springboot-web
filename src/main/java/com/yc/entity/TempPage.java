package com.yc.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 分页实体类
 * @author shuang
 * @param <T>
 * Created by shuang on 2016/11/20.
 */
public class TempPage<T> implements Serializable {
	
	private static final long serialVersionUID = 8397451586805905035L;
	
	private int total;//总跳四
	private List<T> rows;//要显示的数据
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "TempPage [total=" + total + ", rows=" + rows + "]";
	}
	
	
}
