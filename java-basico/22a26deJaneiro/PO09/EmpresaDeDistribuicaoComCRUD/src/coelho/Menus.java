package coelho;

import java.util.Scanner;

import DAO.*;

public class Menus {
	
	ClienteDAO cliente = new ClienteDAO();
	ImovelDAO imovel = new ImovelDAO();
	FaturaDAO fatura = new FaturaDAO();
	PagamentoDAO pagamento = new PagamentoDAO();
	
	Scanner scanner = new Scanner(System.in);

	
	public void runMenuClientes() {
	    int escolha = -1;

	    do {
	        System.out.println("--- Gestão de Clientes ---");
	        System.out.println(" ");
	        System.out.println("[1] - Incluir cliente");
	        System.out.println("[2] - Consultar cliente");
	        System.out.println("[3] - Listar clientes");
	        System.out.println("[4] - Alterar cliente");
	        System.out.println("[5] - Excluir cliente");
	        System.out.println("[0] - Voltar para o menu principal");
	        System.out.println(" ");
	        System.out.println("Escolha uma opção: ");
	        escolha = scanner.nextInt();
	        scanner.nextLine(); // Limpar o buffer

	        switch (escolha) {
	            case 1:
	                cliente.criaCliente();
	                break;

	            case 2:
	                cliente.consultaCliente();
	                break;

	            case 3:
	                ClienteDAO.readAll();
	                break;

	            case 4:
	                cliente.alteraCliente();
	                break;

	            case 5:
	                cliente.excluiCliente();
	                break;

	            case 0:
	                System.out.println("Voltando para o menu principal...");
	                System.out.println("");
	                return;

	            default:
	                System.out.println("Entrada inválida. Digite novamente.");
	                break;
	        }

	    } while (escolha != 0);
	}

	public void runMenuImoveis() {

		int escolha = -1;

		do {
			System.out.println("--- Gestão de Imóveis ---");
			System.out.println(" ");
			System.out.println("[1] - Incluir imóvel");
			System.out.println("[2] - Consultar imóvel");
			System.out.println("[3] - Listar imóveis");
			System.out.println("[4] - Alterar imóvel");
			System.out.println("[5] - Excluir imóvel");
			System.out.println("[0] - Voltar para o menu principal");
			System.out.println(" ");
			System.out.println("Escolha uma opção: ");
			escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
			case 1:
				imovel.criaImovel();
				break;

			case 2:
				imovel.consultaImovel();
				break;

			case 3:
				ImovelDAO.readAll();
				break;

			case 4:
				imovel.alteraImovel();
				break;

			case 5:
				imovel.excluiImovel();
				break;

			case 0:
				System.out.println("Voltando para o menu principal...");
				System.out.println("");
				return;

			default:
				System.out.println("Opção inválida.");
				break;
			}

		} while (escolha != 0);
		
	}
	
	public void runMenuFaturas() {

		int escolha;

		do {
			System.out.println("--- Faturas ---");
			System.out.println(" ");
			System.out.println("[1] - Gerar fatura");
			System.out.println("[2] - Listar faturas em aberto");
			System.out.println("[0] - Voltar para o menu principal");
			System.out.println(" ");
			System.out.println("Escolha uma opção: ");
			escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
			case 1:
				fatura.gerarFatura();
				break;

			case 2:
				FaturaDAO.readFaturasEmAberto();
				break;

			case 0:
				System.out.println("Voltando para o menu principal...");
				return;

			default:
				System.out.println("Opção inválida.");
				break;
			}

		} while (escolha != 0);

	}
	
	public void runMenuPagamentos() {	

		int escolha;

		do {
			System.out.println("--- Pagamentos ---");
			System.out.println(" ");
			System.out.println("[1] - Efetuar pagamento");
			System.out.println("[2] - Listar pagamentos");
			System.out.println("[0] - Voltar para o menu principal");
			System.out.println(" ");
			System.out.println("Escolha uma opção: ");
			escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
			case 1:
				pagamento.fazerPagamento();
				break;
				
			case 2:
				pagamento.readbyFatura();
				break;
				
			case 0:
				System.out.println("Voltando para o menu principal...");
				return;

			default:
				System.out.println("Opção inválida.");
				break;
			}

		} while (escolha != 0);	
	}
		
}
