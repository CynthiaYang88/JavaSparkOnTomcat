package com.projectOne.servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;


/**
 * Servlet implementation class Display
 */
//@WebServlet("/Display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		
		//Business Logic
		SparkConf conf = new SparkConf();
		conf.setAppName("Spark Process");
		conf.setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> lines = sc.textFile("C:/uploaded_files/input.csv"); // multipart form extraction index.jsp
		JavaRDD<String> filteredLines = lines.filter(new Function<String, Boolean>(){
				public Boolean call(String line) throws Exception{
					int lineInt = Integer.parseInt(line);
					return lineInt >= 80;//line.contains("spark");
				}
		});
		
		long linesCount = filteredLines.count();
		out.println("<h1> Results </h1>");
		out.println("<h2> Percentage of Gray Matter in Region of Interest: " + linesCount/25 + "</h2>");
		out.println("<h2>Percentage of White Matter in Region of Interest: " + (100-(linesCount/25)) + "</h2>");
		// Load results into patient
		PatientBean pBBB = new PatientBean(); //write to server non-static
        pBBB.setPatientName("Brain"); // multipart form extraction index.jsp
		pBBB.setPatientResult(linesCount + ": " + (2500-linesCount)); ////
		
		sc.close();

		PatientDAO.insertPatient(pBBB);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		doGet(request, response);
	}

}
