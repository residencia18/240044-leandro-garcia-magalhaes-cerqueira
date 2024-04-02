package menus;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import MODEL.ClienteDAO;
import MODEL.FaturaDAO;
import MODEL.ImovelDAO;
import MODEL.PagamentoDAO;

public class Menus {
	
	ClienteDAO cliente = new ClienteDAO();
	ImovelDAO imovel = new ImovelDAO();
	FaturaDAO fatura = new FaturaDAO();
	PagamentoDAO pagamento = new PagamentoDAO();
	
	Scanner scanner = new Scanner(System.in);
	
	public void runMenuClientes() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_coelho");
		EntityManager em = emf.createEntityManager();
		
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
	                cliente.criaCliente(em);
	                break;

	            case 2:
	                cliente.listaClientePorCPF(em);
	                break;

	            case 3:
	                cliente.listaClientes(em);
	                break;

	            case 4:
	                cliente.alteraCliente(em);
	                break;

	            case 5:
	                cliente.excluiCliente(em);
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
	    
	    em.close();
		emf.close();
	}

	public void runMenuImoveis() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_coelho");
		EntityManager em = emf.createEntityManager();

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
				imovel.criaImovel(em);
				break;

			case 2:
				imovel.buscaImovelPorMatricula(em);
				break;

			case 3:
				imovel.listaImoveis(em);
				break;

			case 4:
				imovel.alteraImovel(em);
				break;

			case 5:
				imovel.excluiImovel(em);
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
		
		 em.close();
		 emf.close();	
	}
	
	public void runMenuFaturas() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_coelho");
		EntityManager em = emf.createEntityManager();

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
				fatura.listarFaturasEmAberto(em);
				break;

			case 0:
				System.out.println("Voltando para o menu principal...");
				return;

			default:
				System.out.println("Opção inválida.");
				break;
			}

		} while (escolha != 0);
		
		em.close();
		emf.close();
	}
	
	public void runMenuPagamentos() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_coelho");
		EntityManager em = emf.createEntityManager();

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
				pagamento.efetuarPagamento();
				break;
				
			case 2:
				pagamento.buscaPagamentoPorFatura();
				break;
				
			case 0:
				System.out.println("Voltando para o menu principal...");
				return;

			default:
				System.out.println("Opção inválida.");
				break;
			}

		} while (escolha != 0);	
		
		em.close();
		emf.close();		
	}
	
}
