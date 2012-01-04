package org.ali.convert;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

abstract class LoadValues {
	public static Connection getConnection() throws Exception {
	    String driver = "oracle.jdbc.OracleDriver";
	    String url = "jdbc:oracle:thin:@//127.0.0.1:1521/xe";
	    String username = "system";
	    String password = "admin";
	    Class.forName(driver);
	    Connection conn = DriverManager.getConnection(url, username, password);
	    return conn;
	  }
	
	public static  void insertData(String id, Date pubdate )throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	   long dateRec = pubdate.getTime();
	    java.sql.Date lDate= new java.sql.Date(dateRec);
	  
	    Thread.sleep(200);
		try {

			conn = getConnection();
			//just clean up for now
			/*String query1 = "delete * from companyinfo";
			pstmt = conn.prepareStatement(query1); 
			pstmt.executeUpdate();*/
			
			String query = "insert into xml_maps2002(id, pubdate) values( ?, ?)";
			//String query = "insert into xml_maps(id, pubdate) values( ?, ?)";
			pstmt = conn.prepareStatement(query); // create a statement
			pstmt.setString(1, id);
			pstmt.setDate(2,  lDate); 
			
			pstmt.executeUpdate();
              
		} catch (Exception e) {
		      e.printStackTrace();
		    } finally {

			 pstmt.close();
		      conn.close();
		    }
	}

	public  ResultSet findAllIds(){
		ResultSet contactsRecords=null ;
		try {
			String query = "select * from xml_maps  ";
			PreparedStatement pstmt;
			Connection  con = getConnection();
			pstmt = con.prepareStatement(query);
			contactsRecords = pstmt.executeQuery();
			contactsRecords=pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contactsRecords;
	}
	
	
	
}
