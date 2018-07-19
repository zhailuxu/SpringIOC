package com.learn.java.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class BeanConfig implements ApplicationContextAware, DisposableBean {

	public ClassPathXmlApplicationContext getSubContextOne() {
		return subContextOne;
	}

	public void setSubContextOne(ClassPathXmlApplicationContext subContextOne) {
		this.subContextOne = subContextOne;
	}

	public ClassPathXmlApplicationContext getSubContextTwo() {
		return subContextTwo;
	}

	public void setSubContextTwo(ClassPathXmlApplicationContext subContextTwo) {
		this.subContextTwo = subContextTwo;
	}

	private ClassPathXmlApplicationContext subContextOne = null;
	private ClassPathXmlApplicationContext subContextTwo = null;

	@Override
	public void destroy() throws Exception {
		if (null != subContextOne) {
			subContextOne.destroy();
		}
		if (null != subContextTwo) {
			subContextTwo.destroy();
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		subContextOne = new ClassPathXmlApplicationContext(new String[] { "sub-context-one.xml" }, applicationContext);

		subContextTwo = new ClassPathXmlApplicationContext(new String[] { "sub-context-two.xml" }, applicationContext);

	}

}