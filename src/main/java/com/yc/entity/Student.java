package com.yc.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String age;

	
	
}
