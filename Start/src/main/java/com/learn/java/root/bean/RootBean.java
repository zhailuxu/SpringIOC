package com.learn.java.root.bean;

import org.springframework.context.annotation.Configuration;

public class RootBean {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
}
