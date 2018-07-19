package com.learn.java.subone.bean;

import org.springframework.context.annotation.Configuration;

public class SubOneBean {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
