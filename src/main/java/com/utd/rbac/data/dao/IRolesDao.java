package com.utd.rbac.data.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.utd.rbac.data.model.Role;


/**
 * This interface is used to perform DB operations on 'roles' collection
 */
@Configuration("#{rolesCollectionName}")
public interface IRolesDao extends MongoRepository<Role, String> {
	
	Role findByRoleName(String roleName);
	
	Role findByRoleID(String roleID);
}