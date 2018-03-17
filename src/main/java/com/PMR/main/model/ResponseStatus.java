package com.PMR.main.model;

public class ResponseStatus {
	private String Status;
	private String Message;
	
	public ResponseStatus() {
	}

	public ResponseStatus(String status, String message) {
		super();
		Status = status;
		Message = message;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseStatus [Status=");
		builder.append(Status);
		builder.append(", Message=");
		builder.append(Message);
		builder.append("]");
		return builder.toString();
	}
}
