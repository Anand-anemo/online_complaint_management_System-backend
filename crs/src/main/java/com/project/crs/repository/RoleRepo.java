package com.project.crs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.crs.entity.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role,String>{

}
