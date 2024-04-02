package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import coelho.Reembolso;

public class ReembolsoDAO {
	
	Scanner scanner = new Scanner(System.in);

	public static void create(Reembolso reembolso) {

		Connection conexao = DAO.criarConexao();

		String query = "INSERT INTO Reembolso (Data,Valor) VALUES (?,?)";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
		
			preparedStatement.setTimestamp(1, reembolso.getData());
			preparedStatement.setDouble(2, reembolso.getValor());
			
			preparedStatement.toString();

			preparedStatement.execute();

		} catch (SQLException e) {
			System.out.println("Não foi possível gerar o reembolso.");
			e.printStackTrace();
		}
	}
}
