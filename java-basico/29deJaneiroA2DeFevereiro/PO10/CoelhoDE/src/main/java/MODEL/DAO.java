package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	private static final String URL = "jdbc:mysql://udf8zrpkzu8ton5w:n6wO8mX3pmbP8APny97i@bfu2ibivhuetgnipve0p-mysql.services.clever-cloud.com:3306/bfu2ibivhuetgnipve0p";
	
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