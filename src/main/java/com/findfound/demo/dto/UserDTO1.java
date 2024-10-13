package com.findfound.demo.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO1 {
	@NotNull
	@NotEmpty(message = "fullName is mandatory")
	@Size(min = 4,message = " In fullName Atleast 4 character required")
	private String fullName;
	@NotNull
	@Email(message = "Email should be valid")
	private String email;
	// @Pattern(message="mobile no should be valid", regexp="[789]{1}[0-9]{9}")
   
	@NotNull(message = "Mobile no is mandatory")
	//@Size(min=10,max=10,message="mobile no should be 10 digit no")
	private long phoneNo;
	@NotEmpty(message = "Gender is mandatory")
	@NotNull(message = "Gender is required")
	private String gender;
	private String password;
	@NotNull
	@Valid
	private AddressDTO address;

	public AddressDTO getAddress() {
		return address;
	}
	@JsonIgnore
public void setPassword(String password)
{
	this.password=password;
}
	@JsonIgnore
	public String getPassword()
	{
		return password;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
		
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
