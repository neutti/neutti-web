package com.neutti.web.config;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;


@Configuration
public class DefaultConfig {
	@Bean
	public ErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes() {
			@Override
			public Map<String, Object> getErrorAttributes(
					RequestAttributes requestAttributes,
					boolean includeStackTrace) {
				Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
				Throwable error = getError(requestAttributes);
				if (error instanceof Exception) {
					errorAttributes.put("errorCode", ((Exception)error).getMessage());
					errorAttributes.put("error", error);
				}
				return errorAttributes;
			}

		};
	}

	@Bean
	public ErrorProperties errorProperties(){
		return new ErrorProperties();
	}


}
