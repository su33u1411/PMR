package com.PMR.Service.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bson.Document;

import com.PMR.DB.DBConnection;
import com.PMR.main.model.Property;
import com.PMR.main.model.Unit;
import com.PMR.main.model.Users;
import com.lambdaworks.crypto.SCryptUtil;

public class Servicehelper {
	DBConnection dbconnection = new DBConnection();

	public boolean addUser(Users request, String dbname) {
		dbconnection.MakeConnection(dbname);
		if (dbconnection.findData("users", "username", request.getUsername()).size() != 0) {
			dbconnection.closeConnection();
			return false;
		} else {
			HashMap<String, Object> documentMap = new HashMap<String, Object>();
			documentMap.put("username", request.getUsername());
			documentMap.put("password", SCryptUtil.scrypt(request.getPassword(), 8, 8, 8));
			documentMap.put("firstname", request.getFirstname());
			documentMap.put("lastname", request.getLastname());
			documentMap.put("middlename", request.getMiddlename());
			documentMap.put("dob", request.getDob());
			documentMap.put("mobilephone", request.getMobilephone());
			documentMap.put("homephone", request.getHomephone());
			documentMap.put("email", request.getEmail());
			documentMap.put("ssn", request.getSsn());
			HashMap<String, Object> maillingaddressmap = new HashMap<String, Object>();
			maillingaddressmap.put("addressline1", request.getMaillingaddress().getAddressline1());
			maillingaddressmap.put("addressline2", request.getMaillingaddress().getAddressline2());
			maillingaddressmap.put("city", request.getMaillingaddress().getCity());
			maillingaddressmap.put("state", request.getMaillingaddress().getState());
			maillingaddressmap.put("zipcode", request.getMaillingaddress().getZipcode());
			HashMap<String, Object> physicaladdressmap = new HashMap<String, Object>();
			physicaladdressmap.put("addressline1", request.getPhysicaladdress().getAddressline1());
			physicaladdressmap.put("addressline2", request.getPhysicaladdress().getAddressline2());
			physicaladdressmap.put("city", request.getPhysicaladdress().getCity());
			physicaladdressmap.put("state", request.getPhysicaladdress().getState());
			physicaladdressmap.put("zipcode", request.getPhysicaladdress().getZipcode());
			documentMap.put("maillingaddress", maillingaddressmap);
			documentMap.put("physicaladdress", physicaladdressmap);
			documentMap.put("previouspasswords", request.getPreviouspasswords());
			documentMap.put("usertype", request.getUsertype());
			request.setAccountStatus("active");
			request.setAccountCreated();
			documentMap.put("accountstatus", request.getAccountStatus());
			documentMap.put("accountcreated", request.getAccountCreated());
			dbconnection.AddData("users", documentMap);
			dbconnection.closeConnection();
			return true;
		}
	}

	public HashMap<String, Object> userLogin(HashMap<String, String> request, String dbname) {
		dbconnection.MakeConnection(dbname);
		String password = "";
		String accountstatus = "";
		String accountType = "";
		List<Document> data = dbconnection.findData("users", "username", request.get("username"));
		if (data.size() != 0) {
			for (Document doc : data) {
				password = doc.getString("password");
				accountstatus = doc.getString("accountstatus");
				accountType = doc.getString("usertype");
				if (SCryptUtil.check(request.get("password"), password) && accountstatus.equals("active")) {
					HashMap<String, Object> usermap = new HashMap<String, Object>();
					usermap.put("username", request.get("username"));
					usermap.put("accountstatus", accountstatus);
					usermap.put("accountType", accountType);
					dbconnection.closeConnection();
					return usermap;
				} else {
					dbconnection.closeConnection();
					return null;
				}
			}
		} else {
			dbconnection.closeConnection();
			return null;
		}
		dbconnection.closeConnection();
		return null;
	}

	public HashMap<String, Object> fetchUserDetails(HashMap<String, String> request, String dbname) {
		dbconnection.MakeConnection(dbname);
		List<Document> userdata = dbconnection.findData("users", "username", request.get("username"));
		List<Document> propertydata = dbconnection.findData("properties", "propertylandlord", request.get("username"));
		if (userdata.size() != 0) {
			HashMap<String, Object> usermap = new HashMap<String, Object>();
			List<HashMap<String, Object>> Propertieslist = new ArrayList<HashMap<String, Object>>();
			for (Document doc : userdata) {
				usermap.put("username", doc.getString("username"));
				usermap.put("firstname", doc.getString("firstname"));
				usermap.put("middlename", doc.getString("middlename"));
				usermap.put("lastname", doc.getString("lastname"));
				usermap.put("mobilephone", doc.getString("mobilephone"));
				usermap.put("homephone", doc.getString("homephone"));
				usermap.put("email", doc.getString("email"));
				usermap.put("maillingaddress", doc.get("maillingaddress"));
				usermap.put("physicaladdress", doc.get("physicaladdress"));
			}
			for (Document doc : propertydata) {
				HashMap<String, Object> propertymap = new HashMap<String, Object>();
				propertymap.put("propertyID", doc.getString("propertyID"));
				propertymap.put("propertyname", doc.getString("propertyname"));
				propertymap.put("propertytype", doc.getString("propertytype"));
				propertymap.put("numberofunits", doc.getString("numberofunits"));
				propertymap.put("propertyaddress", doc.get("propertyaddress"));
				Propertieslist.add(propertymap);
			}
			usermap.put("Properties", Propertieslist);
			dbconnection.closeConnection();
			return usermap;
		} else {
			dbconnection.closeConnection();
			return null;
		}
	}

	public boolean addProperty(Property request, String dbname) {
		boolean Updated = false;
		dbconnection.MakeConnection(dbname);
		List<Document> userdata = dbconnection.findData("users", "username", request.getPropertylandlord());
		if (userdata.size() != 0) {
			for (Document doc : userdata) {
				if (doc.get("usertype").equals("landlord")) {
					HashMap<String, Object> documentMap = new HashMap<String, Object>();
					documentMap.put("propertyID", UUID.randomUUID().toString());
					documentMap.put("propertyname", request.getPropertyname());
					documentMap.put("propertytype", request.getPropertytype());
					HashMap<String, Object> propertyaddressmap = new HashMap<String, Object>();
					propertyaddressmap.put("addressline1", request.getPropertyaddress().getAddressline1());
					propertyaddressmap.put("addressline2", request.getPropertyaddress().getAddressline2());
					propertyaddressmap.put("city", request.getPropertyaddress().getCity());
					propertyaddressmap.put("state", request.getPropertyaddress().getState());
					propertyaddressmap.put("zipcode", request.getPropertyaddress().getZipcode());
					documentMap.put("propertyaddress", propertyaddressmap);
					request.setPropertystatus("active");
					request.setPropertycreated();
					documentMap.put("propertystatus", request.getPropertystatus());
					documentMap.put("propertycreated", request.getPropertycreated());
					documentMap.put("numberofunits", request.getNumberofunits());
					documentMap.put("propertylandlord", request.getPropertylandlord());
					documentMap.put("propertylandlord", request.getPropertylandlord());
					dbconnection.AddData("properties", documentMap);
					dbconnection.closeConnection();
					Updated = true;
				} else {
					dbconnection.closeConnection();
					return Updated;
				}
			}
		}
		dbconnection.closeConnection();
		return Updated;
	}
	
	
	public boolean validateUsername(HashMap<String, String> request, String dbname) {
		boolean isfound = false;
		dbconnection.MakeConnection(dbname);
		if (dbconnection.findData("users", "username", request.get("username")).size() != 0) {
			dbconnection.closeConnection();
			isfound = true;
		}
		dbconnection.closeConnection();
		return isfound;
	}

	public boolean addUnit(Unit request, String dbname) {
		boolean Updated = false;
		dbconnection.MakeConnection(dbname);
		List<Document> propertydata = dbconnection.findData("properties", "propertyID", request.getPropertyID());
		if (propertydata.size() != 0) {
			HashMap<String, Object> documentMap = new HashMap<String, Object>();
			documentMap.put("unitID", UUID.randomUUID().toString());
			documentMap.put("unitname", request.getUnitname());
			documentMap.put("monthlyPayment", request.getMonthlypayment());
			documentMap.put("monthlyPaymentDate", request.getMonthlypayment());
			HashMap<String, Object> unitaddressmap = new HashMap<String, Object>();
			unitaddressmap.put("addressline1", request.getUnitaddress().getAddressline1());
			unitaddressmap.put("addressline2", request.getUnitaddress().getAddressline2());
			unitaddressmap.put("city", request.getUnitaddress().getCity());
			unitaddressmap.put("state", request.getUnitaddress().getState());
			unitaddressmap.put("zipcode", request.getUnitaddress().getZipcode());
			documentMap.put("unitaddress", unitaddressmap);
			documentMap.put("propertyID", request.getPropertyID());
			request.setUnitstatus("active");
			request.setAccountcreated();
			documentMap.put("unitStatus", request.getUnitstatus());
			documentMap.put("unitCreated", request.getAccountcreated());
			documentMap.put("currentTenent", request.getCurrenttenent());
			dbconnection.AddData("units", documentMap);
			dbconnection.closeConnection();
			Updated = true;
		} else {
			dbconnection.closeConnection();
			return Updated;
		}
		dbconnection.closeConnection();
		return Updated;
	}

	public HashMap<String, Object> fetchPropertyDetails(HashMap<String, String> request, String dbname) {
		dbconnection.MakeConnection(dbname);
		List<Document> propertydata = dbconnection.findData("properties", "propertyID", request.get("propertyID"));
		if (propertydata.size() != 0) {
			List<HashMap<String, Object>> Propertieslist = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> propertymap = new HashMap<String, Object>();
			for (Document doc : propertydata) {
				propertymap.put("propertyname", doc.getString("propertyname"));
				propertymap.put("propertytype", doc.getString("propertytype"));
				propertymap.put("numberofunits", doc.getString("numberofunits"));
				propertymap.put("propertyaddress", doc.get("propertyaddress"));
				propertymap.put("propertylandlord", doc.getString("propertylandlord"));
				Propertieslist.add(propertymap);
			}
			dbconnection.closeConnection();
			return propertymap;
		} else {
			dbconnection.closeConnection();
			return null;
		}
	}
	
	public List<HashMap<String, Object>> fetchUnits(HashMap<String, String> request, String dbname) {
		dbconnection.MakeConnection(dbname);
		List<Document> unitdata = dbconnection.findData("units", "propertyID", request.get("propertyID"));
		if (unitdata.size() !=0) {
			List<HashMap<String, Object>> Propertieslist = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> propertymap = new HashMap<String, Object>();
			for (Document doc : unitdata) {
				propertymap.put("unitname", doc.getString("unitname"));
				propertymap.put("monthlyPayment", doc.getString("monthlyPayment"));
				propertymap.put("unitaddress", doc.get("unitaddress"));
				propertymap.put("currentTenent", doc.getString("currentTenent"));
				propertymap.put("unitRequest", doc.get("unitRequest"));
				Propertieslist.add(propertymap);
			}
			dbconnection.closeConnection();
			return Propertieslist;
		} else {
			dbconnection.closeConnection();
			return null;
		}
	}
	
	public List<HashMap<String, Object>> TenantDetails(HashMap<String, String> request, String dbname) {
		dbconnection.MakeConnection(dbname);
		List<Document> unitdata = dbconnection.findData("units", "currentTenent", request.get("username"));
		if (unitdata.size() !=0) {
			List<HashMap<String, Object>> Propertieslist = new ArrayList<HashMap<String, Object>>();
			HashMap<String, Object> propertymap = new HashMap<String, Object>();
			for (Document doc : unitdata) {
				propertymap.put("unitname", doc.getString("unitname"));
				propertymap.put("monthlyPayment", doc.getString("monthlyPayment"));
				propertymap.put("unitaddress", doc.get("unitaddress"));
				propertymap.put("currentTenent", doc.getString("currentTenent"));
				Propertieslist.add(propertymap);
			}
			dbconnection.closeConnection();
			return Propertieslist;
		} else {
			dbconnection.closeConnection();
			return null;
		}
	}
}