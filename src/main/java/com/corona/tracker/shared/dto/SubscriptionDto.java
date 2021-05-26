package com.corona.tracker.shared.dto;

import java.io.Serializable;

public class SubscriptionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1474334799489374266L;

	
	private String name;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
