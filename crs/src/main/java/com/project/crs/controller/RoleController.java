package com.project.crs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.crs.entity.Role;
import com.project.crs.services.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleservice;

	//@PreAuthorize("hasRole('Admin')")
	@PostMapping({"/createNewRole"})
	public Role createNewRole(@RequestBody Role role) {
		return roleservice.createNewRole(role);
		
	}

}
