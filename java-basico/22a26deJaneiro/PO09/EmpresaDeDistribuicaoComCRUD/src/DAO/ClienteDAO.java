package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import coelho.Cliente;

public class ClienteDAO {
	
	Scanner scanner = new Scanner(System.in);
	
	public static void create(Cliente cliente) {

		Connection conexao = DAO.criarConexao();

		String query = "INSERT INTO Cliente (CPF,Nome) VALUES (?,?)";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setString(1, cliente.getCPF());
			preparedStatement.setString(2, cliente.getNome());
			
			preparedStatement.toString();

			preparedStatement.execute();

		} catch (SQLException e) {
			System.out.println("Não foi possível efetuar a criação do cliente.");
			e.printStackTrace();
		}
	}

	public static void readAll() {
		
		ArrayList<Cliente> clientes = new ArrayList<>();

		Connection conexao = DAO.criarConexao();

		String query = "SELECT CPF, Nome FROM Cliente";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Cliente cliente = new Cliente(resultSet.getString("CPF"), resultSet.getString("Nome"));
				
				clientes.add(cliente);			
			}		
			
		} catch (SQLException e) {
			System.out.println("Não foi possível carregar os dados dos clientes");
			e.printStackTrace();
		}
		
		System.out.println("Lista de clientes: ");
		
		for(Cliente cliente : clientes) {
			System.out.println("CPF: " + cliente.getCPF() + " | Nome: " + cliente.getNome());
		}
	}

	public static void readByCPF(String CPF) {
			
			Connection conexao = DAO.criarConexao();
	
			String query = "SELECT CPF, Nome FROM Cliente WHERE CPF = ?";
	
			try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
				
				preparedStatement.setString(1, CPF);
	
				ResultSet resultSet = preparedStatement.executeQuery();
	
				while (resultSet.next()) {
	
					Cliente cliente = new Cliente(resultSet.getString("CPF"), resultSet.getString("Nome"));
					
					System.out.println("Cliente encontrado: ");
					System.out.println("CPF: " + cliente.getCPF() + " | Nome: " + cliente.getNome());					
				}		
				
			} catch (SQLException e) {
				System.out.println("Não foi possível carregar os dados dos usuários");
				e.printStackTrace();
			}				
		}

	public static void update(Cliente cliente) {
		
		Connection conexao = DAO.criarConexao();

		String query = "UPDATE Cliente SET Nome=? WHERE CPF=?";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
			
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getCPF());
		
			preparedStatement.execute();
						
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar os dados do cliente");
			e.printStackTrace();
		}				
	}
	
	public static void delete(Cliente cliente) {
			
			Connection conexao = DAO.criarConexao();
	
			String query = "DELETE FROM Cliente WHERE CPF=?";
	
			try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
				
				preparedStatement.setString(1, cliente.getCPF());
			
				preparedStatement.execute();
							
			} catch (SQLException e) {
				System.out.println("Não foi possível alterar os dados do usuário");
				e.printStackTrace();
			}				
		}
	
	//CRUD
	
	public void criaCliente() {
			
		System.out.println("Insira o nome do cliente: ");
		String nome = scanner.nextLine();
		
		System.out.println("Insira o CPF do cliente: ");
		String cpf = scanner.nextLine();
		
		Cliente cliente = new Cliente(cpf,nome);
		
		ClienteDAO.create(cliente);
				
	}

	public void consultaCliente() {
			
		System.out.println("Insira o CPF do cliente que está buscando: ");
		String cpf = scanner.nextLine();
		
		ClienteDAO.readByCPF(cpf);
		
	}
	
	public void alteraCliente() {
	
		System.out.println("Insira o CPF do cliente que você deseja alterar: ");
		String cpf = scanner.nextLine();
		
		System.out.println("Agora insira o novo nome: ");
		String nome = scanner.nextLine();
		
		Cliente cliente = new Cliente(cpf, nome);
		
		ClienteDAO.update(cliente);
		
	}
	
	public void excluiCliente() {
				
			System.out.println("Insira o CPF do cliente que você deseja excluir: ");
			String cpf = scanner.nextLine();
					
			Cliente cliente = new Cliente(cpf);
			
			ClienteDAO.delete(cliente);		
	}
	
}
