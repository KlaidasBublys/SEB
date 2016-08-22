package com.seb.tool.app.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.seb.tool.services.ServicesRootPackage;
import com.seb.tool.services.bundle.BundleService;
import com.seb.tool.services.bundle.BundleServiceImpl;
import com.seb.tool.web.service.controller.RestControllerMarker;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {RestControllerMarker.class, ServicesRootPackage.class})
public class SebToolBootApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SebToolBootApplication.class, args);
		((BundleServiceImpl) applicationContext.getBean(BundleService.class)).init();
	}
	
}
