package com.project.crs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int complaintId;
	private String category;
	private String heading;
	@Column(length = 1000)
	private String details;
	private String address;
	private String pincode;
	private String fullname;
	private String complaintStatus;
	private String engusername;
	@OneToOne
	private User customer;
	
	
	public Complaint() {
		super();
		
	}
	
	


//public Complaint(int complaintId, String category, String heading, String details, String address, String fullname,
//			String complaintStatus, User customer) {
//		super();
//		this.complaintId = complaintId;
//		this.category = category;
//		this.heading = heading;
//		this.details = details;
//		this.address = address;
//		this.fullname = fullname;
//		this.complaintStatus = complaintStatus;
//		this.customer = customer;
//	}


	public Complaint(int complaintId, String category, String heading, String details, String address, String pincode,
			String fullname, String complaintStatus, String engusername, User customer) {
		super();
		this.complaintId = complaintId;
		this.category = category;
		this.heading = heading;
		this.details = details;
		this.address = address;
		this.pincode = pincode;
		this.fullname = fullname;
		this.complaintStatus = complaintStatus;
		this.engusername = engusername;
		this.customer = customer;
	}

	


	public int getComplaintId() {
		return complaintId;
	}


	




	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getHeading() {
		return heading;
	}


	public void setHeading(String heading) {
		this.heading = heading;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getComplaintStatus() {
		return complaintStatus;
	}


	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}


	public User getCustomer() {
		return customer;
	}


	public void setCustomer(User customer) {
		this.customer = customer;
	}




	public String getEngusername() {
		return engusername;
	}




	public void setEngusername(String engusername) {
		this.engusername = engusername;
	}
	
	
	
	
	
	
	

}
