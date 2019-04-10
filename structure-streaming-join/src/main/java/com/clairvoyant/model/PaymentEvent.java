package com.clairvoyant.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PaymentEvent  implements Serializable{
	    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String paymentUserId; 
	
	private Float amount; 
	
	private String location;

	private Timestamp paymenttimestamp;
	
	





	/**
	 * @return the paymenttimestamp
	 */
	public Timestamp getPaymenttimestamp() {
		return paymenttimestamp;
	}




	/**
	 * @param paymenttimestamp the paymenttimestamp to set
	 */
	public void setPaymenttimestamp(Timestamp paymenttimestamp) {
		this.paymenttimestamp = paymenttimestamp;
	}




	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	public PaymentEvent(String paymentUserId, Float amount, String location, Timestamp paymenttimestamp) {
		super();
		this.paymentUserId = paymentUserId;
		this.amount = amount;
		this.location = location;
		this.paymenttimestamp = paymenttimestamp;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentEvent [paymentUserId=" + paymentUserId + ", amount=" + amount + ", location=" + location
				+ ", paymenttimestamp=" + paymenttimestamp + "]";
	}




	/**
	 * @return the paymentUserId
	 */
	public String getPaymentUserId() {
		return paymentUserId;
	}




	/**
	 * @param paymentUserId the paymentUserId to set
	 */
	public void setPaymentUserId(String paymentUserId) {
		this.paymentUserId = paymentUserId;
	}




	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	
	
}
