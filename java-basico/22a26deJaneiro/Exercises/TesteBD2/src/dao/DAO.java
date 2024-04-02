package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	private static final String URL = "jdbc:mysql://uufwlvzmayi7juyv:AGUNkLkN4KYg1BeQK3Ly@bitftm3y7d1epzb9ejoj-mysql.services.clever-cloud.com:3306/bitftm3y7d1epzb9ejoj";
	private static final String USER = "uufwlvzmayi7juyv";
	private static final String PASSWD = "AGUNkLkN4KYg1BeQK3Ly";
	
	public static Connection conectar() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWD);
			System.out.println("Conexão feita com sucesso!!!");
			
		} catch (SQLException e) {
			System.out.println("Erro de conexão.");
		}
		return con;
	}
	
	public static void main(String[] args) {
		
		DAO.conectar();
		
	}

}
