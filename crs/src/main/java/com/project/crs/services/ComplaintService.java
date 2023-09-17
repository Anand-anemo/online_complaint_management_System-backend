package com.project.crs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crs.configuration.JwtRequestFilter;
import com.project.crs.entity.Complaint;
import com.project.crs.entity.User;
import com.project.crs.repository.ComplaintRepo;
import com.project.crs.repository.UserRepo;

@Service
public class ComplaintService {
	
	@Autowired
	public ComplaintRepo complaintRepo;
	
	@Autowired
	public UserRepo userRepo;
	
	private static final String COMPLAINT_RAISED = "RAISED";
	
//	//mark status as resolved
//	public void markAsResolved(Integer complaintId) {
//		Complaint complaint = complaintRepo.findById(complaintId).get();
//
//		if (complaint != null) {
//			complaint.setComplaintStatus("RESOLVED");
//			complaintRepo.save(complaint);
//		}else {
//			//complaint.setComplaintStatus("Error");
//			complaintRepo.save(complaint);
//		}
//	}
//	
	
	//mark status as in progress
//	public void markAsWip(Integer complaintId) {
//		Complaint complaint = new Complaint();
//		 complaint =  complaintRepo.findById(complaintId).get();
//
//		if (complaint != null) {
//			complaint.setComplaintStatus("WORK IN PROGRESS");
//			complaintRepo.save(complaint);
//		}else {
//			//complaint.setComplaintStatus("Error");
//			complaintRepo.save(complaint);
//		}
//	}
	
	
	//mark status as in review
//	public void markAsInReview(Integer complaintId) {
//		Complaint complaint = complaintRepo.findById(complaintId).get();
//
//		if (complaint != null) {
//			complaint.setComplaintStatus("ESCALATED");
//			complaintRepo.save(complaint);
//		}else {
//			//complaint.setComplaintStatus("Error");
//			complaintRepo.save(complaint);
//		}
//	}
	
	
	
	//adding new complaint feature for customer
	public Complaint addNewComplaint(Complaint c) {
		String currentUser = JwtRequestFilter.CURRENT_USER;
		User user = userRepo.findById(currentUser).get();
		c.setCustomer(user);
		
		
		

		
		c.setComplaintStatus(COMPLAINT_RAISED);
		
		return complaintRepo.save(c);
	}
	
	//getting all complaints list 
	public List<Complaint> getAllComplaint(){
		return (List<Complaint>)complaintRepo.findAll();
	}
	
	public List<Complaint> getAllComplaintsForEngineer(){
		Complaint c = new Complaint();
		String pin = c.getPincode();
		if(pin.equals("110025")) {
			return (List<Complaint>)complaintRepo.findAll();
		}
		else {
			return null;
		}
		
	}
	
	//deleting complaint
	public void deleteComplaint(Integer complaintId) {
		complaintRepo.deleteById(complaintId);
		
	}
	
	//getting compaints by Id
	public Complaint getComplaintById(Integer complaintId) {
		return complaintRepo.findById(complaintId).get();
	}
	
	public List<Complaint> getMyComplaintDetails() {
		String currentUser = JwtRequestFilter.CURRENT_USER;// will get the current username
		User user = userRepo.findById(currentUser).get(); // by this we will get all the userdetails

		return complaintRepo.findByCustomer(user);
		// this method find the complaints for current user
		// that particular user
	}
	
	public List<Complaint> getMangerComplaintList(){
		String currentUser = JwtRequestFilter.CURRENT_USER;
		User user = userRepo.findById(currentUser).get();
		String pincode = user.getPincode();
		System.out.println(pincode);
		return complaintRepo.findByPincode(pincode);
	
	}
	
	public Complaint updateComplaint(Complaint c) {
		
		
		return complaintRepo.save(c);
	}
	public List<Complaint> getComplaintforEngineer(){
		String currentUser = JwtRequestFilter.CURRENT_USER;
		User user = userRepo.findById(currentUser).get();
		String uname=user.getUserName();
		return complaintRepo.findByEngusername(uname);
	}
	
public Complaint updateComplaintforEng(Complaint c) {
		
		
		return complaintRepo.save(c);
	}


}
