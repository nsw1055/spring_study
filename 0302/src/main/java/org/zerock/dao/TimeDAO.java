package org.zerock.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;
@Component
public class TimeDAO {

	public String getTime() throws Exception{
		
		  String driverName ="oracle.jdbc.driver.OracleDriver";
	      String jdbcURL= "jdbc:oracle:thin:@112.169.196.210:1523:XE";
	      String userName ="dclub";
	      String userPW ="dclub";
	      
	      //; 주의
	      String query ="select sysdate from dual";
	      
	      String result = null;
	      
	      Class.forName(driverName);
	      
	      try (Connection con = DriverManager.getConnection(jdbcURL, userName, userPW);
	          PreparedStatement pstmt = con.prepareStatement(query);
	            ResultSet rs = pstmt.executeQuery();
	         ){
	         
	         System.out.println(con);
	         
	         rs.next();
	         
	         result = rs.getString(1);
	         
	         
	      }catch(Exception e) {
	         throw e;
	      }
	      
	      return result;
	   }

		
	}
	
	

