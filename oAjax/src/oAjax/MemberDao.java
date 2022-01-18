package oAjax;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {
	private static MemberDao instance;
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		if(instance==null) {
			instance=new MemberDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn=ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int confirm(String id) throws SQLException {
		int result=1;
		// id존재 	result=1
		// id존재 x 	result=0
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT ID FROM MEMBER1 WHERE ID='"+id+"'";
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) 
				result=1;
			else 
				result=0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)
			conn.close();
			if(stmt!=null)
			stmt.close();
			if(rs!=null)
			rs.close();
		}
		return result;
	}
	
}
