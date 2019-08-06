package com.utd.rbac.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Encapsulates MongoDB configuration
 */
@Configuration
public class MongoConfiguration {

	@Value("${mongodb.roles.collection.name:roles}")
	private String rolesCollectionName;

	@Value("${mongodb.users.collection.name:users}")
	private String usersCollectionName;

	/**
	 * Gets the roles collection name for MongoDB
	 *
	 * @return A String reflecting the MongoDb collection name
	 */
	@Bean
	public String rolesCollectionName() {
		return rolesCollectionName;
	}

	/**
	 * Gets the users collection name for MongoDB
	 *
	 * @return A String reflecting the MongoDb collection name
	 */
	@Bean
	public String usersCollectionName() {
		return usersCollectionName;
	}
}