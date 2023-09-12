package com.project.crs.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.crs.entity.Role;
import com.project.crs.entity.User;
import com.project.crs.repository.RoleRepo;
import com.project.crs.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired //(required=true)
	private UserRepo userRepo;
	
	@Autowired //(required=true)
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public void deleteUser(String userName) {
		userRepo.deleteById(userName);
	}

	public User RegisterNewUser(User user) {
		Role role = roleRepo.findById("Customer").get();
		
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRole(roles);
		
		user.setUserpassword(getEncodedPassword(user.getUserpassword()));
		
		return userRepo.save(user);
	}
	
	public User RegisterNewEngineer(User engg) {
		Role role = roleRepo.findById("Engineer").get();
		
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		engg.setRole(roles);
		
		engg.setUserpassword(getEncodedPassword(engg.getUserpassword()));
		
		return userRepo.save(engg);
	}
	
	public User RegisterNewManager(User manager) {
		Role role = roleRepo.findById("Manager").get();
		
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		manager.setRole(roles);
		
		manager.setUserpassword(getEncodedPassword(manager.getUserpassword()));
		
		return userRepo.save(manager);
	}
	
	public void initRolesAndUser() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Administrative role for the website");
		roleRepo.save(adminRole);
		
		Role customerRole = new Role();
		customerRole.setRoleName("Customer");
		customerRole.setRoleDescription("Default role for customers");
		roleRepo.save(customerRole);
		
		Role managerRole = new Role();
		managerRole.setRoleName("Manager");
		managerRole.setRoleDescription("Assigning complaints to suitable engineer to resolve it");
		roleRepo.save(managerRole);
		
		Role engineerRole = new Role();
		engineerRole.setRoleName("Engineer");
		engineerRole.setRoleDescription("Resolve the complaints and update the status");
		roleRepo.save(engineerRole);
		
		User adminUser = new User();
		adminUser.setFullName("admin");
		adminUser.setUserName("admin123");
		adminUser.setUserpassword(getEncodedPassword("pwd@123"));
		adminUser.setPincode("110223");
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		userRepo.save(adminUser);
		
		User managerUser = new User();
		managerUser.setFullName("ABC Manager1");
		managerUser.setUserName("manager1");
		managerUser.setUserpassword(getEncodedPassword("manager1@pwd"));
		managerUser.setPincode("248001");
		Set<Role> managerRoles = new HashSet<>();
		managerRoles.add(managerRole);
		managerUser.setRole(managerRoles);
		userRepo.save(managerUser);
	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
