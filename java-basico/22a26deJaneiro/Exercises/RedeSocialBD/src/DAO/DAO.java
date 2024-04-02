package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	private static final String URL = "jdbc:mysql://uufwlvzmayi7juyv:AGUNkLkN4KYg1BeQK3Ly@bitftm3y7d1epzb9ejoj-mysql.services.clever-cloud.com:3306/bitftm3y7d1epzb9ejoj";
	
	public static Connection criarConexao() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL);			
			
		} catch (SQLException e) {
			
			throw new RuntimeException("Falha ao conectar com o BD: ", e);
		}
		
		return con;
	}

}
