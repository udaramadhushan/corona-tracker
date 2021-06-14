package com.corona.tracker.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity(name="subscription")
@Table
public class SubscriptionEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3263546119053019232L;

	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=100, nullable=false)
	private String subId;
	
	@Column(length=100, nullable=false)
	private String email;
	
	@Column(length=100 )
	private String name = "Subscriber";
	
	@Column
	Boolean subscribed = true;

	public Boolean getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String id) {
		this.subId = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
