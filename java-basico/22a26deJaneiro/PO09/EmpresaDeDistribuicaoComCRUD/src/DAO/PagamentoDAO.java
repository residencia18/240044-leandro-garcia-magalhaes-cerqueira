package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


import coelho.Fatura;
import coelho.Pagamento;
import coelho.Reembolso;

public class PagamentoDAO {

	Scanner scanner = new Scanner(System.in);

	public static void create(Pagamento pagamento, int idFatura) {

		Connection conexao = DAO.criarConexao();

		String query = "INSERT INTO Pagamento (IdFatura, Data, Valor) VALUES (?,?,?)";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setInt(1, idFatura);
			preparedStatement.setTimestamp(2, pagamento.getData());
			preparedStatement.setDouble(3, pagamento.getValor());

			preparedStatement.toString();

			preparedStatement.execute();

		} catch (SQLException e) {
			System.out.println("Não foi possível efetuar o pagamento.");
			e.printStackTrace();
		}
	}

	public void readbyFatura() {
		
		ArrayList<Pagamento> pagamentos = new ArrayList<>();
		
		FaturaDAO.readFaturasEmAberto();

		System.out.println("Informe o ID da fatura que deseja verificar os pagamentos feitos: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		Connection conexao = DAO.criarConexao();

		String query = "SELECT * FROM Pagamento WHERE IdFatura = ?";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Pagamento pagamento = new Pagamento(resultSet.getTimestamp("Data"), resultSet.getDouble("Valor"));
				pagamentos.add(pagamento);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possível carregar os dados dos usuários");
			e.printStackTrace();
		}
		
		for (Pagamento pagamento : pagamentos) {
			System.out.println(pagamento.toString());
		}
		
						
	}

	public void fazerPagamento() {

		FaturaDAO.readFaturasEmAberto();

		System.out.println("Informe o ID da fatura que deseja efetuar o pagamento: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		Connection conexao = DAO.criarConexao();

		String query = "SELECT * FROM Fatura WHERE Id = ?";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Fatura fatura = new Fatura(resultSet.getTimestamp("Data"), resultSet.getDouble("UltimaLeitura"),
						resultSet.getDouble("PenultimaLeitura"), resultSet.getDouble("Valor"),
						resultSet.getBoolean("Quitado"));

				if (fatura.isQuitado() == true) {
					System.out.println("Não é possível efetuar novos pagamento pois a fatura já foi quitada.");
					return;
				} else {
					System.out.println("Informe o valor que deseja pagar: ");
					double valor = scanner.nextDouble();
					scanner.nextLine();
					
					Pagamento pagamento = new Pagamento(valor);
					
					PagamentoDAO.create(pagamento, id);
					
					double soma = valor + fatura.getValor();
								
					if(valor >= fatura.getValor() || soma > fatura.getValor()) {
						fatura.setQuitado(true);
						double valorReembolso = valor - fatura.getValor();
						Reembolso reembolso = new Reembolso(valorReembolso);
						
						ReembolsoDAO.create(reembolso);
						FaturaDAO.update(id);
					}				
				}
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível carregar os dados dos usuários");
			e.printStackTrace();
		}
	}


}
