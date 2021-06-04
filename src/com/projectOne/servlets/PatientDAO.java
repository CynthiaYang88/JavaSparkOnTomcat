package com.projectOne.servlets;
import java.sql.*;

public class PatientDAO {
	
	static Connection conn;
	static PreparedStatement pst;
	
	public static int insertPatient(PatientBean u) { //PatientBean u
		int status=0;
		try {
			conn=ConnectionProvider.getCon();
			pst=conn.prepareStatement("insert into ratio values(?,?)");
			pst.setString(1, u.getPatientName());// u.getPatientName() "hi"
			pst.setString(2, u.getPatientResult());// u.isPatientResult() "false"
			status=pst.executeUpdate();
			conn.close();
			
		} catch(Exception ex){
			System.out.println(ex);
		}
		return status;
	}

	
	public int writeToServer(PatientBean u) {
		// Want to output to server:
		// String this.name
		// double b
		//System.out.println("bb" + b);
		
		
		/*// Create datasource.
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("DESKTOP-IE524RB/music");
        //ds.setPassword("<password>");
        ds.setServerName("DESKTOP-IE524RB");
        ds.setPortNumber(Integer.parseInt("5432"));
        ds.setDatabaseName("userNameDatabase"); */
		// Create a variable for the connection string.
        
	    // Create a variable for the connection string.
        //String connectionUrl = "jdbc:postgresql://localhost:5432/userNameDatabase;user=opsdb;password=opsdb";
		// "jdbc:postgresql://localhost:5432/userNameDatabase", "opsdb","opsdb" 
		int status = 0;
		String b = u.getPatientName();
		String bb = u.getPatientResult();
        String SQL = "insert into classificationResultsTWO(patientname,patientresult) values(?, ?)";//"insert into user_name(UserName, bankRoll) values(this.name, b)";
        
        try ( Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projectOne","postgres","lalala086"); PreparedStatement stmt = con.prepareStatement(SQL);) {
            //String SQL = "insert into user_name values(?, ?)";//"insert into user_name(UserName, bankRoll) values(this.name, b)";
            stmt.setString(1, b);
            stmt.setString(2, bb);
            stmt.addBatch();
            stmt.executeBatch(); // SQL
            status++;
            
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
            status = 4;
        }
        return status;
        
        
	}

}

