package com.together.semiprj.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {

	private static Connection conn = null;
	
	// DB 연결을 위한 커넥션 반환 메소드
	public static Connection getConnection() {
		
		try {

	         Context initContext = new InitialContext();
	         Context envContext  = (Context)initContext.lookup("java:/comp/env");  

	         DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
	         
	         conn = ds.getConnection(); 
	         conn.setAutoCommit(false);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	// Connection 반환 메소드
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	// Statement 반환 메소드 + (다형성을 이용하여 PreparedStatement도 같이 반환 가능)
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ResultSet 반환 메소드 
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// commit용 메소드
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// rollback용 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}





