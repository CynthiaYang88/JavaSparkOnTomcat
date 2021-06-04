package com.projectOne.servlets;

public class PatientBean {
	String patientName;
	String patientResult;
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String pName) {
		this.patientName = pName;
	}
	public String getPatientResult() {
		return patientResult;
	}
	public void setPatientResult(String patientResult) {
		this.patientResult = patientResult;
	}
	public String toString() {
		String r = "Name: " + patientName + ", Results: " + patientResult;
		return r;
	}
	
}
