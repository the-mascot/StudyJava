package dao;

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

import vo.Board;

public class BoardDao {
	
	private static BoardDao instance;
	private BoardDao() {}
	
	public static BoardDao getInstance() {
		if(instance == null)
			instance = new BoardDao();
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn=null;
		
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/postgreSql");
			conn=ds.getConnection();
		} catch (Exception e) {
			System.out.println("BoardDao getConnection Exception->"+e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	public List<Board> list() throws SQLException {
		
		List<Board> boardList = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY b_num DESC) AS RN, * FROM board) T";
		//String sql = "SELECT VERSION()";
		//String sql = "select b_num from board";
		
		try {
			conn = getConnection();
			//pstmt = conn.prepareStatement(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Board board = new Board();
				board.setRn(rs.getInt(1));
				board.setB_num(rs.getInt(2));
				board.setNickname(rs.getString(3));
				board.setPassword(rs.getString(4));
				board.setTitle(rs.getString(5));
				board.setContent(rs.getString(6));
				//board.setDate(rs.getDate(7));
				boardList.add(board);
			}
			
		} catch (Exception e) {
			System.out.println("BoardDao list Exception->"+e.getMessage());
		} finally {
			if(conn != null)	conn.close();
			if(stmt != null)	stmt.close();
			if(rs != null)		rs.close();
		}
		
		return boardList;
	}

	public int getTotCnt() throws SQLException {
		
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from board";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				totCnt = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("BoardDao getTotCnt Exception->"+e.getMessage());
		} finally {
			if(conn != null)	conn.close();
			if(stmt != null)	stmt.close();
			if(rs != null)		rs.close();
		}
		return totCnt;
	}
	
	
}
