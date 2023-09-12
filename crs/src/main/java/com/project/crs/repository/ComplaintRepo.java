package com.project.crs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.crs.entity.Complaint;
import com.project.crs.entity.User;

@Repository
public interface ComplaintRepo extends CrudRepository<Complaint, Integer> {
	public List<Complaint> findByCustomer(User user);
	
	public List<Complaint> findByPincode(String pincode);
	//public List<Complaint> findByEngineer(User user);
	public List<Complaint> findByEngusername(String engusername);
}
