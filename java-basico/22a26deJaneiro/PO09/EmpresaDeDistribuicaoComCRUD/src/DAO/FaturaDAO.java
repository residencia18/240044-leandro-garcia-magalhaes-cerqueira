package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

import coelho.Fatura;
import coelho.Imovel;

public class FaturaDAO {
	
	Scanner scanner = new Scanner(System.in);

	public static void create(Fatura fatura) {

		Connection conexao = DAO.criarConexao();

		String query = "INSERT INTO Fatura (Data, UltimaLeitura, PenultimaLeitura, Valor, Quitado) VALUES (?,?,?,?,?)";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setTimestamp(1, fatura.getData());
			preparedStatement.setDouble(2, fatura.getUltimaLeitura());
			preparedStatement.setDouble(3, fatura.getPenultimaLeitura());
			preparedStatement.setDouble(4, fatura.getValor());
			preparedStatement.setBoolean(5, fatura.isQuitado());

			preparedStatement.toString();

			preparedStatement.execute();

		} catch (SQLException e) {
			System.out.println("Não foi possível efetuar a criação da fatura.");
			e.printStackTrace();
		}
	}

	public static void update(int id) {
			
			Connection conexao = DAO.criarConexao();
	
			String query = "UPDATE Fatura SET Quitado=1 WHERE Id=?";
	
			try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
				
				preparedStatement.setInt(1, id);
				
				preparedStatement.execute();
							
			} catch (SQLException e) {
				System.out.println("Não foi possível alterar os dados da fatura");
				e.printStackTrace();
			}				
		}
	
	public static void readFaturasEmAberto() {

		Connection conexao = DAO.criarConexao();

		String query = "SELECT * FROM Fatura WHERE Quitado = false";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			ResultSet resultSet = preparedStatement.executeQuery();
			
			System.out.println("Faturas em aberto: ");
			
			while (resultSet.next()) {

				Fatura fatura = new Fatura(resultSet.getTimestamp("Data"), resultSet.getDouble("UltimaLeitura"),resultSet.getDouble("PenultimaLeitura"), resultSet.getDouble("Valor"),resultSet.getBoolean("Quitado"));
	
				System.out.println("ID: " + resultSet.getInt("Id") + " | " + "Data de criação da fatura: " + fatura.getData() + " | Última leitura: "
						+ fatura.getUltimaLeitura() + " | Penúltima leitura: " + fatura.getPenultimaLeitura()
						+ " | Valor: " + fatura.getValor());
				System.out.println(" ");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível carregar os dados das faturas");
			e.printStackTrace();
		}
	}

	public void gerarFatura() {
		
		System.out.println("Informe a matrícula do imóvel: ");
		String matricula = scanner.nextLine();
		
		Imovel imovel = ImovelDAO.readByMatricula(matricula);
		
		System.out.println("Última leitura: " + imovel.getUltimaLeitura());
		
		System.out.println("Informe a leitura atual: ");
		double leituraAtual = scanner.nextDouble();
		
		long horarioAtual = System.currentTimeMillis();
        Timestamp hora = new Timestamp(horarioAtual);
		
		imovel.setPenultimaLeitura(imovel.getUltimaLeitura());
		imovel.setUltimaLeitura(leituraAtual);
		
		ImovelDAO.update(imovel);
		
		double valorFatura = (imovel.getUltimaLeitura() - imovel.getPenultimaLeitura()) * 10;
		
		Fatura fatura = new Fatura(hora,leituraAtual,imovel.getPenultimaLeitura(),valorFatura,false);
		
		FaturaDAO.create(fatura);
		
	}
}
