package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import usuario.Usuario;

public class PostagemDAO {
	
	public static boolean create(Usuario usuario) {
	
	Connection conexao = DAO.criarConexao();
	String query = "INSERT INTO Usuario (Login, Senha, Email) VALUES (?,?,?)";
	
	try (PreparedStatement preparedStatement = conexao.prepareStatement(query)){
		preparedStatement.setString(1, usuario.getLogin());
		preparedStatement.setString(1, usuario.getSenha());
		preparedStatement.setString(1, usuario.getEmail());
		preparedStatement.execute();
		
		for (String post : usuario.getPostagens()) {
			PostagemDAO.create(usuario,post);
		}
		return true;
	} catch (SQLException e) {
		System.out.println("Erro ao adicionar o cliente: " + e);
		return false;
	
	}
	}
}

