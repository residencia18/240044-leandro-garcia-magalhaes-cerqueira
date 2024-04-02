package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import coelho.Imovel;

public class ImovelDAO {

	Scanner scanner = new Scanner(System.in);

	public static void create(Imovel imovel) {

		Connection conexao = DAO.criarConexao();

		String query = "INSERT INTO Imovel (Matricula, Endereco, ultimaLeitura, penultimaLeitura) VALUES (?,?,?,?)";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setString(1, imovel.getMatricula());
			preparedStatement.setString(2, imovel.getEndereco());
			preparedStatement.setDouble(3, imovel.getUltimaLeitura());
			preparedStatement.setDouble(4, imovel.getPenultimaLeitura());

			preparedStatement.toString();

			preparedStatement.execute();

		} catch (SQLException e) {
			System.out.println("Não foi possível efetuar a criação do imóvel.");
			e.printStackTrace();
		}
	}

	public static void readAll() {

		ArrayList<Imovel> imoveis = new ArrayList<>();

		Connection conexao = DAO.criarConexao();

		String query = "SELECT Matricula, Endereco, ultimaLeitura, penultimaLeitura FROM Imovel";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Imovel imovel = new Imovel(resultSet.getString("Matricula"), resultSet.getString("Endereco"),
						resultSet.getDouble("ultimaLeitura"), resultSet.getDouble("penultimaLeitura"));

				imoveis.add(imovel);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível carregar os dados dos imóveis");
			e.printStackTrace();
		}

		System.out.println("Lista de imóveis: ");

		for (Imovel imovel : imoveis) {
			System.out.println("Matricula: " + imovel.getMatricula() + " | Endereço: " + imovel.getEndereco()
					+ " | Última leitura: " + imovel.getUltimaLeitura() + " | Penúltima leitura: "
					+ imovel.getPenultimaLeitura());
		}
	}

	public static Imovel readByMatricula(String matricula) {

		Connection conexao = DAO.criarConexao();

		String query = "SELECT Matricula, Endereco, ultimaLeitura, penultimaLeitura FROM Imovel WHERE Matricula = ?";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setString(1, matricula);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) { //Verifica se há algum resultado
			Imovel imovel = new Imovel(resultSet.getString("Matricula"), resultSet.getString("Endereco"),
					resultSet.getDouble("ultimaLeitura"), resultSet.getDouble("penultimaLeitura"));

			System.out.println("Imóvel encontrado: ");
			System.out.println("Matricula: " + imovel.getMatricula() + " | Endereço: " + imovel.getEndereco()
					+ " | Última leitura: " + imovel.getUltimaLeitura() + " | Penúltima leitura: "
					+ imovel.getPenultimaLeitura());
			System.out.println("");

			return imovel;
			} else {
				System.out.println("Imóvel não encontrado para a matrícula fornecida.");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível carregar os dados dos imóveis");
			e.printStackTrace();
		}

		return null;
	}

	public static void update(Imovel imovel) {

		Connection conexao = DAO.criarConexao();

		String query = "UPDATE Imovel SET Endereco=?, UltimaLeitura=?, PenultimaLeitura=? WHERE Matricula=?";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setString(1, imovel.getEndereco());
			preparedStatement.setDouble(2, imovel.getUltimaLeitura());
			preparedStatement.setDouble(3, imovel.getPenultimaLeitura());
			preparedStatement.setString(4, imovel.getMatricula());

			preparedStatement.execute();

		} catch (SQLException e) {
			System.out.println("Não foi possível alterar os dados do imóvel");
			e.printStackTrace();
		}
	}

	public static void delete(Imovel imovel) {

		Connection conexao = DAO.criarConexao();

		String query = "DELETE FROM Imovel WHERE Matricula=?";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setString(1, imovel.getMatricula());

			preparedStatement.execute();

		} catch (SQLException e) {
			System.out.println("Não foi possível alterar os dados do usuário");
			e.printStackTrace();
		}
	}

	// CRUD

	public void criaImovel() {

		System.out.println("Insira a matrícula do imóvel: ");
		String matricula = scanner.nextLine();

		System.out.println("Insira o endereço do imóvel: ");
		String endereco = scanner.nextLine();

		Double ultimaLeitura = 0.0;
		Double penultimaLeitura = 0.0;

		Imovel imovel = new Imovel(matricula, endereco, ultimaLeitura, penultimaLeitura);

		ImovelDAO.create(imovel);

	}

	public void consultaImovel() {

		System.out.println("Insira a matricula do imóvel que está buscando: ");
		String matricula = scanner.nextLine();

		ImovelDAO.readByMatricula(matricula);

	}

	public void alteraImovel() {

		System.out.println("Insira a matricula do imóvel que você deseja alterar: ");
		String matricula = scanner.nextLine();

		System.out.println("Agora insira o novo endereço: ");
		String endereco = scanner.nextLine();

		Imovel imovel = new Imovel(matricula, endereco);

		ImovelDAO.update(imovel);

	}

	public void excluiImovel() {

		System.out.println("Insira a matricula do imóvel que você deseja excluir: ");
		String matricula = scanner.nextLine();

		Imovel imovel = new Imovel(matricula);

		ImovelDAO.delete(imovel);
	}

}
