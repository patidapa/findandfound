package com.findfound.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class NGO {
	@Id
	@GeneratedValue
private int id;
	@NotNull
private String ngo_name;
	@NotNull
private long account_no;
	@NotNull
private String bank_name;
	@NotNull
private String account_name;
	@NotNull
private String account_type;
	@NotNull
private double amount;

public NGO() {
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNgo_name() {
	return ngo_name;
}
public void setNgo_name(String ngo_name) {
	this.ngo_name = ngo_name;
}
public long getAccount_no() {
	return account_no;
}
public void setAccount_no(long account_no) {
	this.account_no = account_no;
}
public String getBank_name() {
	return bank_name;
}
public void setBank_name(String bank_name) {
	this.bank_name = bank_name;
}
public String getAccount_name() {
	return account_name;
}
public void setAccount_name(String account_name) {
	this.account_name = account_name;
}
public String getAccount_type() {
	return account_type;
}
public void setAccount_type(String account_type) {
	this.account_type = account_type;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
@Override
public String toString() {
	return "NGO [id=" + id + ", ngo_name=" + ngo_name + ", account_no=" + account_no + ", bank_name=" + bank_name
			+ ", account_name=" + account_name + ", account_type=" + account_type + ", amount=" + amount + "]";
}

}
