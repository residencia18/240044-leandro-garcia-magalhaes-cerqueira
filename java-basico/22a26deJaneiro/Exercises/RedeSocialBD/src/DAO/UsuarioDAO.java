package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import usuario.Usuario;

public class UsuarioDAO {

	public static void create(Usuario usuario) {

		Connection conexao = DAO.criarConexao();

		String query = "INSERT INTO Usuario (Login,Senha,Email) VALUES (?,?,?)";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getEmail());

			preparedStatement.toString();

			preparedStatement.execute();

		} catch (SQLException e) {
			System.out.println("Não foi possível efetuar a criação do usuário");
			e.printStackTrace();
		}
	}

	public static void readAll() {
		
		ArrayList<Usuario> usuarios = new ArrayList<>();

		Connection conexao = DAO.criarConexao();

		String query = "SELECT Login, Senha, Email FROM Usuario";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Usuario user = new Usuario(resultSet.getString("Login"), resultSet.getString("Senha"),
						resultSet.getString("Email"));
				
				usuarios.add(user);
				
			}

			
			
		} catch (SQLException e) {
			System.out.println("Não foi possível carregar os dados dos usuários");
			e.printStackTrace();
		}
		
		for(Usuario usuario : usuarios) {
			System.out.println("Login: " + usuario.getLogin() + " | Senha: " + usuario.getSenha() + " | Email: " + usuario.getEmail());
		}
	}

	public static void readById(String Login) {
			
			Connection conexao = DAO.criarConexao();
	
			String query = "SELECT Login, Senha, Email FROM Usuario WHERE Login = ?";
	
			try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
				
				preparedStatement.setString(1, Login);
	
				ResultSet resultSet = preparedStatement.executeQuery();
	
				while (resultSet.next()) {
	
					Usuario usuario = new Usuario(resultSet.getString("Login"), resultSet.getString("Senha"),
							resultSet.getString("Email"));
					
					System.out.println("Login: " + usuario.getLogin() + " | Senha: " + usuario.getSenha() + " | Email: " + usuario.getEmail());
							
				}		
				
			} catch (SQLException e) {
				System.out.println("Não foi possível carregar os dados dos usuários");
				e.printStackTrace();
			}
					
		}

	public static void update(Usuario usuario) {
		
		Connection conexao = DAO.criarConexao();

		String query = "UPDATE Usuario SET Senha=?, Email=? WHERE Login=?";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
			
			preparedStatement.setString(1, usuario.getSenha());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getLogin());

			preparedStatement.execute();
						
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar os dados do usuário");
			e.printStackTrace();
		}				
	}
	
	public static void delete(Usuario usuario) {
			
			Connection conexao = DAO.criarConexao();
	
			String query = "DELETE FROM Usuario WHERE Login=?";
	
			try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
				
				preparedStatement.setString(1, usuario.getLogin());
			
				preparedStatement.execute();
							
			} catch (SQLException e) {
				System.out.println("Não foi possível alterar os dados do usuário");
				e.printStackTrace();
			}				
		}
	
	
	public static void main(String[] args) {

		Usuario usuario = new Usuario();

		usuario.setLogin("Tico");
		usuario.setSenha("xxx");
		usuario.setEmail("xxxx");
//
//		UsuarioDAO.create(usuario);
		
		UsuarioDAO.readAll();
		
		System.out.println(" ");
		
		UsuarioDAO.readById("bruna");
		
		System.out.println(" ");
		
		UsuarioDAO.update(usuario);
		
		System.out.println(" ");
		
		UsuarioDAO.delete(usuario);

	}
}
