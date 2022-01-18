package och12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {
	private static MemberDao instance;
	
	private MemberDao() { }
	
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
	
	public int check(String id, String passwd) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT PASSWD FROM MEMBER2 WHERE ID=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(passwd))
					result=1;
				else
					result=0;
			} else
				result=-1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)
				conn.close();
			if(pstmt!=null)
				pstmt.close();
			if(rs!=null)
				rs.close();
		}
		return result;
	}
	
	public int insert(Member member) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="INSERT INTO MEMBER2 VALUES(?,?,?,?,?,sysdate)";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getTel());
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
	
	public List<Member> list() throws SQLException {
		List<Member> list=new ArrayList<Member>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM MEMBER2";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					Member member=new Member();
					member.setId(rs.getString(1));
					member.setPasswd(rs.getString(2));
					member.setName(rs.getString(3));
					member.setAddress(rs.getString(4));
					member.setTel(rs.getString(5));
					member.setReg_date(rs.getDate(6));
					list.add(member);
				} while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)
				conn.close();
			if(pstmt!=null)
				pstmt.close();
			if(rs!=null)
				rs.close();
		}
		return list;
	}
	
	public Member select(String id) throws SQLException {
		Member member=new Member();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM MEMBER2 WHERE ID=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				member.setId(rs.getString(1));
				member.setPasswd(rs.getString(2));
				member.setName(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setTel(rs.getString(5));
				member.setReg_date(rs.getDate(6));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)
				conn.close();
			if(pstmt!=null)
				pstmt.close();
			if(rs!=null)
				rs.close();
		}
		return member;
	}
	
	public int update(Member member) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="UPDATE MEMBER2 SET PASSWD=?, NAME=?, ADDRESS=?, TEL=? WHERE ID=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getTel());
			pstmt.setString(5, member.getId());
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
	
	public int delete(String id, String passwd) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="DELETE FROM MEMBER2 WHERE ID=?";
		result=check(id, passwd);
		if(result!=1)
			return result;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null)
				conn.close();
			if(pstmt!=null)
				pstmt.close();
			if(rs!=null)
				rs.close();
		}
		return result;
	}
	
	public int confirm(String id) throws SQLException {
		int result=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT ID FROM MEMBER2 WHERE ID='"+id+"'";
		
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
