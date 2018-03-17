package com.PMR.main.model;

public class Address {
	private String addressline1;
	private String addressline2;
	private String city;
	private String state;
	private String zipcode;
	
	public Address() {
	}
	
	public Address(String addressline1, String addressline2, String city, String state, String zipcode) {
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [addressline1=");
		builder.append(addressline1);
		builder.append(", addressline2=");
		builder.append(addressline2);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", zipcode=");
		builder.append(zipcode);
		builder.append("]");
		return builder.toString();
	}
}
