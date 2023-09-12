package com.project.crs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.crs.entity.Complaint;
import com.project.crs.services.ComplaintService;

@RestController
@CrossOrigin
public class ComplaintController {
	
	@Autowired
	private ComplaintService complaintService;
	
	//only customer can register complaint
	//@PreAuthorize("hasRole('Customer')")
	@PostMapping(value= {"/add/new/complaint"})
	public Complaint addNewComplaint(@RequestBody Complaint complaint) {
		return complaintService.addNewComplaint(complaint);
		
	}
	
	//only admin can delete any complaint if as required
	//@PreAuthorize("hasRole('Admin')")
	@DeleteMapping(value= {"/delete/complaint/{complaintId}"})
	public void deleteComplaint(@PathVariable ("complaintId") Integer complaintId) {
		complaintService.deleteComplaint(complaintId);
	}
	
	//only admin and manager can get all complaints list
	//@PreAuthorize("hasAnyRole('Admin','Manager','Customer')")
	@GetMapping(value= {"/get/complaint/list"})
	public List<Complaint> getAllComplaints() {
		List<Complaint> complaints = complaintService.getAllComplaint();
//		System.out.println("Size of complaints list id: "+ complaints.size());
		
		return complaints;
	}
	
	
	
	//@PreAuthorize("hasAnyRole('Engineer')")
	@GetMapping(value= {"/get/complaint/list/engineer"})
	public List<Complaint> getAllComplaintsEngg() {
		return complaintService.getAllComplaintsForEngineer();
	}
	
	//@PreAuthorize("hasAnyRole('Engineer')")
	@GetMapping({"/markAsResolved/{complaintId}"})
	public void markAsResolved(@PathVariable(name = "complaintId")Integer complaintId ) {     
		complaintService.markAsResolved(complaintId);
	}
	
	
	//@PreAuthorize("hasAnyRole('Engineer')")
	@GetMapping({"/markAsWip/{complaintId}"})
	public void markAsWip(@PathVariable(name = "complaintId")Integer complaintId ) {     
		complaintService.markAsWip(complaintId);
	}
	
	//@PreAuthorize("hasAnyRole('Engineer')")
	@GetMapping({"/markAsInReview/{complaintId}"})
	public void markAsInReview(@PathVariable(name = "complaintId")Integer complaintId ) {     
		complaintService.markAsInReview(complaintId);
	}
	
	@GetMapping("/getComplaintById/{complaintId}")
	public Complaint getComplaintById(@PathVariable Integer complaintId) {
		return complaintService.getComplaintById(complaintId);

	}
	
	//@PreAuthorize("hasRole('Customer')")
	@GetMapping({"/get/mycomplaints"})
	public List<Complaint> getMyComplaintDetails() {
		return complaintService.getMyComplaintDetails();
	}
	@GetMapping({"/get/managercomplaintlist"})
	public List<Complaint> getManagerComplaintList(){
		return complaintService.getMangerComplaintList();
		
	}
	
	@PutMapping({"/update/complaint"})
	public Complaint updateComplaint(@RequestBody Complaint complaint) {
		return complaintService.updateComplaint(complaint);
	}
	
	@GetMapping({"/get/complaintforeng"})
	public List<Complaint>getcomforeng(){
		return this.complaintService.getComplaintforEngineer();
	}
	
	@PutMapping({"/update/complaintforeng"})
	public Complaint updateComplaintfroeng(@RequestBody Complaint complaint) {
		return complaintService.updateComplaintforEng(complaint);
	}
		
	


}
