package com.PMR.main.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class Users {
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String firstname;
	@NotNull
	private String lastname;
	@NotNull
	private String dob;
	@NotNull
	private Address physicaladdress;
	@NotNull
	private Address maillingaddress;
	@NotNull
	private String mobilephone;
	@NotNull
	private String email;
	@NotNull
	private String ssn;
	@NotNull
	private String usertype;

	private String accountcreated;
	private String accountstatus;
	private Set<String> previouspasswords;
	private String middlename;
	private String homephone;

	public Users() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		Set<String> Passwords = new HashSet<String>(5);
		setPreviouspasswords(Passwords);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String age) {
		this.dob = age;
	}

	public Address getPhysicaladdress() {
		return physicaladdress;
	}

	public void setPhysicaladdress(Address physicaladdress) {
		this.physicaladdress = physicaladdress;
	}

	public Address getMaillingaddress() {
		return maillingaddress;
	}

	public void setMaillingaddress(Address maillingaddress) {
		this.maillingaddress = maillingaddress;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public Set<String> getPreviouspasswords() {
		return previouspasswords;
	}

	public void setPreviouspasswords(Set<String> previousPaswords) {
		previousPaswords.add(getPassword());
		previouspasswords = previousPaswords;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String userType) {
		usertype = userType;
	}

	public String getAccountCreated() {
		return accountcreated;
	}

	public void setAccountCreated() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.accountcreated = dateFormat.format(date);
	}

	public String getAccountStatus() {
		return accountstatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountstatus = accountStatus;
	}
}
