package com.PMR.main.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class Property {
	@NotNull
	private String propertyname;
	@NotNull
	private String propertytype;
	@NotNull
	private Address propertyaddress;
	@NotNull
	private String numberofunits;
	@NotNull
	private String propertylandlord;

	private String propertyID;
	private String propertystatus;
	private String propertycreated;	

	public Property() {
	}


	public Property(String propertyname, String propertytype, Address propertyaddress, String numberofunits,
			String propertylandlord, String propertyID, String propertystatus, String propertycreated) {
		super();
		this.propertyname = propertyname;
		this.propertytype = propertytype;
		this.propertyaddress = propertyaddress;
		this.numberofunits = numberofunits;
		this.propertylandlord = propertylandlord;
		this.propertyID = propertyID;
		this.propertystatus = propertystatus;
		this.propertycreated = propertycreated;
	}


	public String getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	public String getPropertyname() {
		return propertyname;
	}

	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}

	public String getPropertytype() {
		return propertytype;
	}

	public void setPropertytype(String propertytype) {
		this.propertytype = propertytype;
	}

	public Address getPropertyaddress() {
		return propertyaddress;
	}

	public void setPropertyaddress(Address propertyaddress) {
		this.propertyaddress = propertyaddress;
	}

	public String getNumberofunits() {
		return numberofunits;
	}

	public void setNumberofunits(String numberofunits) {
		this.numberofunits = numberofunits;
	}

	public String getPropertylandlord() {
		return propertylandlord;
	}

	public void setPropertylandlord(String propertylandlord) {
		this.propertylandlord = propertylandlord;
	}

	public String getPropertystatus() {
		return propertystatus;
	}

	public void setPropertystatus(String propertystatus) {
		this.propertystatus = propertystatus;
	}

	public String getPropertycreated() {
		return propertycreated;
	}

	public void setPropertycreated() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.propertycreated = dateFormat.format(date);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Property [propertyname=");
		builder.append(propertyname);
		builder.append(", propertytype=");
		builder.append(propertytype);
		builder.append(", propertyaddress=");
		builder.append(propertyaddress);
		builder.append(", numberofunits=");
		builder.append(numberofunits);
		builder.append(", propertylandlord=");
		builder.append(propertylandlord);
		builder.append(", propertyID=");
		builder.append(propertyID);
		builder.append(", propertystatus=");
		builder.append(propertystatus);
		builder.append(", propertycreated=");
		builder.append(propertycreated);
		builder.append("]");
		return builder.toString();
	}

}
