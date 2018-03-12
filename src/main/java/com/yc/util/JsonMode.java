package com.yc.util;

import java.io.Serializable;

public class JsonMode implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer code;
	private Object obj;
	private String error;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "JsonMode [code=" + code + ", obj=" + obj + ", error=" + error + "]";
	}
	
	
}
