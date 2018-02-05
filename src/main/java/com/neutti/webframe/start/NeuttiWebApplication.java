package com.neutti.webframe.start;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.neutti.webframe.config","com.neutti.webframe.servlet"})
@EnableAutoConfiguration(exclude = {MybatisAutoConfiguration.class})
public class NeuttiWebApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NeuttiWebApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(NeuttiWebApplication.class, args);
	}



}
