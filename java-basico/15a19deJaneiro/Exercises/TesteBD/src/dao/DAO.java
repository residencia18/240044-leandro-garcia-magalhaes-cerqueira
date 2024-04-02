package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	private static final String URL = "jdbc:mysql://u7afqwl9m3yl3mcq:jfOUziPmbdH0J1RsWoid@bpcnr9txoeuciivb7vi3-mysql.services.clever-cloud.com:3306/bpcnr9txoeuciivb7vi3";
	private static final String USER = "u7afqwl9m3yl3mcq";
	private static final String PASSWD = "jfOUziPmbdH0J1RsWoid";
	
	public static Connection conectar() {
		
		
		
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL,USER, PASSWD);
			System.out.println("Conexao Feita com sucesso!!!");
		} catch (SQLException e) {
			System.out.println("Erro de conexao");
		}
		return con;
	}
	
	public static void main(String[] args) {
		
		DAO.conectar();
	}


}
