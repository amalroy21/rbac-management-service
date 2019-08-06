package com.utd.rbac.data.dao;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.utd.rbac.data.model.User;


/**
 * This interface is used to perform DB operations on 'users' collection
 */
@Configuration("#{usersCollectionName}")
public interface IUsersDao extends MongoRepository<User, ObjectId> {

	User findByUserEmail(String userEmail);

}
