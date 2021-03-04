package org.zerock.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTest {
	
	@Test
	public void testConnection() throws Exception {
		
//		JDBC 드라이버 확인
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		log.info("1------------------------");
		
		String url = "jdbc:mysql://localhost:3306/dclass?serverTimezone=UTC";
		String username = "springuser";
		String password = "springuser";
		
//		커넥션 확인
		Connection con = DriverManager.getConnection(url, username, password);
		
		log.info(con);
		
		con.close();
	}
	
}
