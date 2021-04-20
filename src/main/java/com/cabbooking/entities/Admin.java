package com.cabbooking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin4_table")
public class Admin extends AbstractUser {

	@Id
	@GeneratedValue
	private int adminId;

	public Admin() {
		super();
	}

	public Admin(String username, String password, String mobileNumber, String email) {
		super(username, password, mobileNumber, email);
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

}