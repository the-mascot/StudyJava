package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL {
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		
		String connurl	= 	"jdbc:postgresql://localhost:5432/crud";
		String user		=	"curd";
		String password =	"tiger";
		
		try (Connection connection = DriverManager.getConnection(connurl, user, password);) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
