package com.findfound.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressDTO {

	@NotNull(message = "House Address is Mandatory")
	@NotEmpty(message = "House Address is Mandatory")
	private String houseAddress;

	@NotNull(message = "City is Mandatory")
	@NotEmpty(message = "City is Mandatory")
	private String city;

	@NotNull(message = "State is Mandatory")
	@NotEmpty(message = "State is Mandatory")
	private String state;
   
	@NotNull(message = "pinCode is Mandatory")
	private int pinCode;

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "AddressDTO [houseAddress=" + houseAddress + ", city=" + city + ", state=" + state + ", pinCode="
				+ pinCode + "]";
	}

}
