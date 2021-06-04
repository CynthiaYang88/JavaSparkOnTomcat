package com.projectOne.servlets;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long linesCount = 90;
		PatientBean pBBB = new PatientBean();
        pBBB.setPatientName("ROI 1");
		pBBB.setPatientResult(linesCount + ": " + (2500-linesCount));
		PatientDAO pDD = new PatientDAO();
		pDD.writeToServer(pBBB);
		
		System.out.println(pBBB.toString());

	}
	
}
