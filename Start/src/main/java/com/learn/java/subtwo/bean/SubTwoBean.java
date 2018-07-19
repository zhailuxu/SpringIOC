package com.learn.java.subtwo.bean;

import org.springframework.context.annotation.Configuration;

public class SubTwoBean {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
