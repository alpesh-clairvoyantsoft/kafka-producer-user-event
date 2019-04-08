package com.clairvoyant.model;

import java.sql.Timestamp;

public class UserEvent{
	
	private String userId; 
	
	private String firstname; 
	
	private String lastname;
	
	private String phonenumber;
	
	private Timestamp usertimestamp;
	
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @return the usertimestamp
	 */
	public Timestamp getUsertimestamp() {
		return usertimestamp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserEvent [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", phonenumber="
				+ phonenumber + ", usertimestamp=" + usertimestamp + "]";
	}

	public UserEvent(String userId, String firstname, String lastname, String phonenumber, Timestamp usertimestamp) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.usertimestamp = usertimestamp;
	}

	/**
	 * @param usertimestamp the usertimestamp to set
	 */
	public void setUsertimestamp(Timestamp usertimestamp) {
		this.usertimestamp = usertimestamp;
	}



}
