package com.yujia.rest.webservices.restfulwebservices.supermarket;

import java.util.Date;

public class Customer {
	private Integer id;
	private String name;
	private Date birthDate;
	private Double walletBalance;
	
	protected Customer() {
		
	}
	public Customer(Integer id, String name, Date birthDate, Double walletBalance) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.walletBalance = walletBalance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(Double walletBalance) {
		this.walletBalance = walletBalance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", Wallet Balance" + walletBalance + "]";
	}

}
