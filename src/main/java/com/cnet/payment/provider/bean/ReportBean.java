package com.cnet.payment.provider.bean;

import java.util.List;

public class ReportBean {
	
	private boolean error;
	private String errorMessage;
	private List<String> ReportList;
	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the reportList
	 */
	public List<String> getReportList() {
		return ReportList;
	}
	/**
	 * @param reportList the reportList to set
	 */
	public void setReportList(List<String> reportList) {
		ReportList = reportList;
	}

}
