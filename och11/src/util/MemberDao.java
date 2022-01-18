package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn=null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public int insertMember(MemberDto member) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO MEMBER1 VALUES(?,?,?,sysdate)";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)
				conn.close();
			if(pstmt!=null)
				pstmt.close();
		}
		return result;
	}
	
	public int check(String id, String password) throws SQLException {
		int result=0;
		MemberDto mdto=new MemberDto();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT ID, PASSWORD FROM MEMBER1 WHERE ID='"+id+"'";
		
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				mdto.setId(rs.getString(1));
				mdto.setPassword(rs.getString(2));
				if(mdto.getId().equals(id)&&mdto.getPassword().equals(password)) 
					result=1;
				else if(mdto.getPassword().equals(password))
					result=0;
			} else
				result=-1;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		}
			
		return result;
	}
	
}
