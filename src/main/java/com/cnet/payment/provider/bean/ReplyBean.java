/**
 * 
 */
package com.cnet.payment.provider.bean;

/**
 * @author TAREQ SAAD
 *
 */
public class ReplyBean {
	private boolean success;
	private String message;
	/**
	 * @return the status
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param status the status to set
	 */
	public void setSuccess(boolean status) {
		this.success = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
