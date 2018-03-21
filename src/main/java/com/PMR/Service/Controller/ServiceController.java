package com.PMR.Service.Controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.PMR.DB.DBConnection;
import com.PMR.Service.helper.Servicehelper;
import com.PMR.main.model.Property;
import com.PMR.main.model.ResponseStatus;
import com.PMR.main.model.Unit;
import com.PMR.main.model.Users;

@RestController
@CrossOrigin
public class ServiceController {
	DBConnection dbcon = new DBConnection();
	ResponseStatus responseStatus = new ResponseStatus();
	Servicehelper servicehelper = new Servicehelper();


	@RequestMapping(value = "/api/v1/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> login(@RequestBody HashMap<String, String> request) {
		ResponseEntity<Object> response = null;
		if ((request.containsKey("username") && request.containsKey("password"))
				&& (request.get("username") != null && request.get("password") != null)
				&& (!request.get("username").equals("") && !request.get("password").equals(""))) {
			HashMap<String, Object> userLogin = servicehelper.userLogin(request, "PMR");
			if (userLogin != null) {
				response = new ResponseEntity<>(userLogin, HttpStatus.OK);
			} else {
				responseStatus.setStatus("Failed");
				responseStatus.setMessage("Login Falied, Please try again");
				response = new ResponseEntity<>(responseStatus, HttpStatus.NOT_FOUND);
			}
		} else {
			responseStatus.setStatus("Failed");
			responseStatus.setMessage("Invalid Request/Bad Request");
			response = new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@RequestMapping(value = "/api/v1/validateUsername", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> validateUsername(@RequestBody HashMap<String, String> request) {
		ResponseEntity<Object> response = null;
		if (!request.containsKey("username") || request.get("username") == null
				|| request.get("username").equals("")) {
			responseStatus.setStatus("Failed");
			responseStatus.setMessage("Invalid Request/Bad Request");
			response = new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
		} else {
			if (!servicehelper.validateUsername(request, "PMR")) {
				responseStatus.setStatus("Success");
				responseStatus.setMessage("Username is avaliable");
				response = new ResponseEntity<>(responseStatus, HttpStatus.OK);
			} else {
				responseStatus.setStatus("Failed");
				responseStatus.setMessage("Invalid username, Please try again");
				response = new ResponseEntity<>(responseStatus, HttpStatus.NOT_FOUND);
			}
		}
		return response;
	}
	
	@RequestMapping(value = "/api/v1/addUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addUser(@Valid @RequestBody Users request, Errors error) {
		ResponseEntity<Object> response = null;
		if (error.hasErrors()) {
			responseStatus.setStatus("Failed");
			responseStatus.setMessage("Invalid Request/Bad Request");
			response = new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
		} else {
			if (servicehelper.addUser(request, "PMR")) {
				responseStatus.setStatus("Success");
				responseStatus.setMessage("A new account has been successfully created");
				response = new ResponseEntity<>(responseStatus, HttpStatus.OK);
			} else {
				responseStatus.setStatus("Failed");
				responseStatus.setMessage("username already exists, Please try again choosing another username.");
				response = new ResponseEntity<>(responseStatus, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}

	@RequestMapping(value = "/api/v1/addProperty", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addProperty(@Valid @RequestBody Property request, Errors error) {
		ResponseEntity<Object> response = null;
		if (error.hasErrors()) {
			responseStatus.setStatus("Failed");
			responseStatus.setMessage("Invalid Request/Bad Request");
			response = new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
		} else {
			if (servicehelper.addProperty(request, "PMR")) {
				responseStatus.setStatus("Success");
				responseStatus.setMessage("A new property has been successfully created");
				response = new ResponseEntity<>(responseStatus, HttpStatus.OK);
			} else {
				responseStatus.setStatus("Failed");
				responseStatus.setMessage("Invalid Request/Bad Request");
				response = new ResponseEntity<>(responseStatus, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return response;
	}

	@RequestMapping(value = "/api/v1/addUnit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addUnit(@Valid @RequestBody Unit request, Errors error) {
		ResponseEntity<Object> response = null;
		System.out.println(error.toString());
		if (error.hasErrors()) {
			responseStatus.setStatus("Failed");
			responseStatus.setMessage("Invalid Request/Bad Request");
			response = new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
		} else {
			if(servicehelper.addUnit(request, "PMR")) {
				responseStatus.setStatus("Success");
				responseStatus.setMessage("Units for property has been successfully updated/added");
				response = new ResponseEntity<>(responseStatus, HttpStatus.OK);
			}
			else {
				responseStatus.setStatus("Failed");
				responseStatus.setMessage("Invalid PropertyID");
				response = new ResponseEntity<>(responseStatus, HttpStatus.NOT_FOUND);
			}
		}
		return response;
	}

	@RequestMapping(value = "/api/v1/getUserdetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> getUserdetails(@RequestBody HashMap<String, String> request) {
		ResponseEntity<Object> response = null;
		if (!request.containsKey("username") || request.get("username") == null || request.get("username").equals("")) {
			responseStatus.setStatus("Failed");
			responseStatus.setMessage("Invalid Request/Bad Request");
			response = new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
		} else {
			HashMap<String, Object> details = servicehelper.fetchUserDetails(request, "PMR");
			if (details != null) {
				response = new ResponseEntity<>(details, HttpStatus.OK);
			} else {
				responseStatus.setStatus("Failed");
				responseStatus.setMessage("Invalid username, Please try again");
				response = new ResponseEntity<>(responseStatus, HttpStatus.NOT_FOUND);
			}
		}
		return response;
	}
	
	@RequestMapping(value = "/api/v1/getPropertydetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> getPropertydetails(@RequestBody HashMap<String, String> request) {
		ResponseEntity<Object> response = null;
		if (!request.containsKey("propertyID") || request.get("propertyID") == null
				|| request.get("propertyID").equals("")) {
			responseStatus.setStatus("Failed");
			responseStatus.setMessage("Invalid Request/Bad Request");
			response = new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
		} else {
			HashMap<String, Object> details = servicehelper.fetchPropertyDetails(request, "PMR");
			if (details != null) {
				response = new ResponseEntity<>(details, HttpStatus.OK);
			} else {
				responseStatus.setStatus("Failed");
				responseStatus.setMessage("Invalid propertyID, Please try again");
				response = new ResponseEntity<>(responseStatus, HttpStatus.NOT_FOUND);
			}
		}
		return response;
	}
		
	@RequestMapping(value = "/api/v1/getUnits", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> getUnits(@RequestBody HashMap<String, String> request) {
		ResponseEntity<Object> response = null;
		if (!request.containsKey("propertyID") || request.get("propertyID") == null
				|| request.get("propertyID").equals("")) {
			responseStatus.setStatus("Failed");
			responseStatus.setMessage("Invalid Request/Bad Request");
			response = new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
		} else {
			List<HashMap<String, Object>> details = servicehelper.fetchUnits(request, "PMR");
			if (details != null) {
				response = new ResponseEntity<>(details, HttpStatus.OK);
			} else {
				responseStatus.setStatus("Failed");
				responseStatus.setMessage("Invalid propertyID, Please try again");
				response = new ResponseEntity<>(responseStatus, HttpStatus.NOT_FOUND);
			}
		}
		return response;
	}
	
	@RequestMapping(value = "/api/v1/getTenantDetails", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> getTenantDetails(@RequestBody HashMap<String, String> request) {
		ResponseEntity<Object> response = null;
		if (!request.containsKey("username") || request.get("username") == null
				|| request.get("username").equals("")) {
			responseStatus.setStatus("Failed");
			responseStatus.setMessage("Invalid Request/Bad Request");
			response = new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
		} else {
			List<HashMap<String, Object>> details = servicehelper.TenantDetails(request, "PMR");
			if (details != null) {
				response = new ResponseEntity<>(details, HttpStatus.OK);
			} else {
				responseStatus.setStatus("Failed");
				responseStatus.setMessage("Invalid propertyID, Please try again");
				response = new ResponseEntity<>(responseStatus, HttpStatus.NOT_FOUND);
			}
		}
		return response;
	}
}
