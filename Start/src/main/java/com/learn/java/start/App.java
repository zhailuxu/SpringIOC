package com.learn.java.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.java.config.BeanConfig;
import com.learn.java.root.bean.RootBean;
import com.learn.java.subone.bean.SubOneBean;
import com.learn.java.subtwo.bean.SubTwoBean;

/**
 * Hello world!
 * 
 */
@RestController
@SpringBootApplication
@ComponentScan(basePackages = { "com.learn.java.config" })
@ImportResource("root-context.xml")
public class App {

	private static ConfigurableApplicationContext rootConext;
	@Autowired
	private BeanConfig beanConfig;

	@RequestMapping("/home")
	String home() {
		return "Hello World!";
	}

	/**
	 * 子容器访问根容器里面的bean
	 * 
	 * @return
	 */
	@RequestMapping("/testSubAccessRootBean")
	String testSubAccessRootBean() {
		ClassPathXmlApplicationContext subConext = beanConfig.getSubContextOne();
		RootBean rootBean = subConext.getBean(RootBean.class);
		if (null != rootBean) {
			return rootBean.getName();
		} else {
			return "can not found bean";

		}

	}

	/**
	 * 根容器访问子容器里面Bean
	 * 
	 * @return
	 */
	@RequestMapping("/testRootAccessSubBean")
	String testRootAccessSubBean() {
		try {
			SubOneBean sub = rootConext.getBean(SubOneBean.class);
			if (null != sub) {
				return sub.getName();
			} else {
				return "can not found bean";

			}
		}catch(Exception e) {
			
			return e.getLocalizedMessage();
		}
		

	}

	/**
	 * 子容器访问兄弟子容器Bean
	 * @return
	 */
	@RequestMapping("/testSubAccessSubBean")
	String testSubAccessSubBean() {
		try {
			ClassPathXmlApplicationContext subConext = beanConfig.getSubContextOne();
			SubTwoBean subTwoBean = subConext.getBean(SubTwoBean.class);
			if (null != subTwoBean) {
				return subTwoBean.getName();
			} else {
				return "can not found bean";

			}
		}catch(Exception e) {
			return e.getLocalizedMessage();
		}
		
	}

	public static void main(String[] args) {
		rootConext = SpringApplication.run(App.class, args);
	}
}
