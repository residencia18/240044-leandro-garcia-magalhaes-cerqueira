package transporte;

import cadastros.Cadastro;

import java.util.Scanner;

public class App {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		Cadastro cadastros = new Cadastro();
		
		int opcao;
		
		do {
		System.out.println("======= PAINEL DE CONTROLE - TRANSPORTE VIÁRIO =======");
		System.out.println("");
		System.out.println("[1] - Cadastro de veículo");
		System.out.println("[2] - Cadastro de motorista");
		System.out.println("[3] - Cadastro de cobrador");
		System.out.println("[4] - Cadastro de passageiro");
		System.out.println("[5] - Cadastro de ponto de parada");
		System.out.println("[6] - Cadastro de trajeto");
		System.out.println("[7] - Registro de jornada");
		System.out.println("[8] - Registro de início de trajeto");
		System.out.println("[9] - Registro de passageiro embarcado com o cartão");
		System.out.println("[10] - Registro de checkpoint");
		System.out.println("[0] - Sair.");
		System.out.println("");
		System.out.println("Escolha uma opção: ");
		opcao = scanner.nextInt();
		
		switch (opcao) {
		
		case 1:	
			cadastros.cadastrarVeiculo();
			break;
			
		case 2:
			cadastros.cadastrarMotorista();	
			break;
		
		case 3:
			cadastros.cadastrarCobrador();	
			break;
			
		case 4:
			cadastros.cadastrarPassageiro();
			break;
			
		case 5:
			cadastros.cadastrarPontosDeParada();	
			break;
			
		case 6:
			cadastros.cadastrarTrajeto();
			break;
			
		case 7:
			cadastros.registraJornada();
			break;
		
		case 8:
			cadastros.registraInicioDeTrajeto();
			break;
		
		case 9:
			cadastros.registraEmbarque();
			break;
		
		case 10:
			cadastros.registraCheckpoint();
			
		case 0:
			System.out.println("Programa finalizado!");
			return;
									
		default:
			System.out.println("Opção Inválida.");
			break;
		}
		
		} while (opcao != 0);
					
		scanner.close(); // Fecha o scanner.
	}
	
}
