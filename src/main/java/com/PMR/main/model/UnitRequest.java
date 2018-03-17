package com.PMR.main.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UnitRequest {
	private String requesttype;
	private String requestdate;
	private String requestAuthor;
	private String status;
	private String comments;

	public UnitRequest() {

	}

	public UnitRequest(String requesttype, String requestdate, String requestAuthor, String status, String comments) {
		super();
		this.requesttype = requesttype;
		this.requestdate = requestdate;
		this.requestAuthor = requestAuthor;
		this.status = status;
		this.comments = comments;
	}

	public String getRequesttype() {
		return requesttype;
	}

	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}

	public String getRequestdate() {
		return requestdate;
	}

	public void setRequestdate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.requestdate = dateFormat.format(date);
	}

	public String getRequestAuthor() {
		return requestAuthor;
	}

	public void setRequestAuthor(String requestAuthor) {
		this.requestAuthor = requestAuthor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnitRequest [requesttype=");
		builder.append(requesttype);
		builder.append(", requestdate=");
		builder.append(requestdate);
		builder.append(", requestAuthor=");
		builder.append(requestAuthor);
		builder.append(", status=");
		builder.append(status);
		builder.append(", comments=");
		builder.append(comments);
		builder.append("]");
		return builder.toString();
	}
}
