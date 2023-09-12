package com.project.crs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crs.entity.Role;
import com.project.crs.repository.RoleRepo;

@Service
public class RoleService {
	
	@Autowired //(required=true)
	private RoleRepo roleRepo;
	
	public Role createNewRole(Role role) {
		return roleRepo.save(role);//will save this role and return the information saved.
		
	}

}
