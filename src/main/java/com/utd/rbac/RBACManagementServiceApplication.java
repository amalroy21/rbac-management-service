package com.utd.rbac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * This class represents the spring boot main class which starts up the Spring
 * Application Context
 */
@SpringBootApplication
@ComponentScan("com.utd")
@EnableMongoRepositories(basePackages = "com.utd")
public class RBACManagementServiceApplication {
	
	/**
	 * Represents logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(RBACManagementServiceApplication.class);

	/**
	 * Represents the spring boot starter method
	 * 
	 * @param args
	 * @return {@link Void}
	 */
	public static void main(String[] args) {
		SpringApplication.run(RBACManagementServiceApplication.class, args);
		logger.info("Role Based Access Control Management Service started successfully");
	}
}
