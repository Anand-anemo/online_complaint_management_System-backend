package com.project.crs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.crs.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User,String> {

}
