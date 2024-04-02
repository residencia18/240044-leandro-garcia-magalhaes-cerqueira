package transporte;

import cadastros.CRUD;

import java.util.Scanner;

public class Menus {

	Scanner scanner = new Scanner(System.in);
	CRUD crud = new CRUD();

	public void runMenuCadastro() {

		int opcao;

		do {
			System.out.println("======= MENU DE CADASTROS =======");
			System.out.println("");
			System.out.println("[1] - Cadastro de veículo");
			System.out.println("[2] - Cadastro de motorista");
			System.out.println("[3] - Cadastro de cobrador");
			System.out.println("[4] - Cadastro de passageiro");
			System.out.println("[5] - Cadastro de ponto de parada");
			System.out.println("[6] - Cadastro de trajeto");
			System.out.println("[7] - Registro de jornada");
			System.out.println("[8] - Registro de início de trajeto");
			System.out.println("[9] - Registro de embarque com cartão");
			System.out.println("[10] - Registro de checkpoint");
			System.out.println("[0] - Voltar para o menu principal.");
			System.out.println("");
			System.out.println("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {

			case 1:
				try {
					crud.cadastrarVeiculo();
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 2:
				try {
					crud.cadastrarMotorista();
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 3:
				try {
					crud.cadastrarCobrador();
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 4:
				try {
					crud.cadastrarPassageiro();
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 5:
				try {
					crud.cadastrarPontosDeParada();
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 6:
				try {
					crud.cadastrarTrajeto();
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 7:
				try {
					crud.registraJornada();
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 8:
				crud.registraInicioDeTrajeto();
				break;

			case 9:
				try {
					crud.registraEmbarque();
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 10:
				try {
					crud.registraCheckpoint();
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 0:
				System.out.println("Voltando para o menu principal...");
				return;

			default:
				System.out.println("Opção Inválida.");
				break;
			}

		} while (opcao != 0);

		scanner.close(); // Fecha o scanner.
	}

	public void runMenuAlteracao() {

		int opcao;

		do {
			System.out.println("======= ALTERAÇÃO DE DADOS =======");
			System.out.println("");
			System.out.println("[1] - Alterar veículo");
			System.out.println("[2] - Alterar motorista");
			System.out.println("[3] - Alterar cobrador");
			System.out.println("[4] - Alterar passageiro");
			System.out.println("[5] - Alterar ponto de parada");
			System.out.println("[6] - Alterar trajeto");
			System.out.println("[7] - Alterar jornada");
			System.out.println("[8] - Alterar embarque");
			System.out.println("[9] - Alterar trechos");
			System.out.println("[0] - Voltar para o menu principal.");
			System.out.println("");
			System.out.println("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {

			case 1:
				try {
					crud.alterar("veiculos.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}
				break;

			case 2:
				try {
					crud.alterar("motoristas.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 3:
				try {
					crud.alterar("cobradores.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 4:
				try {
					crud.alterar("passageiros.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 5:
				try {
					crud.alterar("pontosDeParada.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 6:
				try {
					crud.alterar("trajetos.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 7:
				try {
					crud.alterar("jornadas.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

			case 8:
				try {
					crud.alterar("embarques.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;
				
			case 9:
				try {
					crud.alterar("trechos.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 0:
				System.out.println("Voltando pro menu principal...");
				return;

			default:
				System.out.println("Opção Inválida.");
				break;
			}

		} while (opcao != 0);

		scanner.close(); // Fecha o scanner.
	}

	public void runMenuExclusao() {

		int opcao;

		do {
			System.out.println("======= EXCLUSÃO DE DADOS =======");
			System.out.println("");
			System.out.println("[1] - Excluir veículo");
			System.out.println("[2] - Excluir motorista");
			System.out.println("[3] - Excluir cobrador");
			System.out.println("[4] - Excluir passageiro");
			System.out.println("[5] - Excluir ponto de parada");
			System.out.println("[6] - Excluir trajeto");
			System.out.println("[7] - Excluir jornada");
			System.out.println("[8] - Excluir embarque");
			System.out.println("[9] - Alterar trechos");
			System.out.println("[0] - Voltar para o menu principal.");
			System.out.println("");
			System.out.println("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {

			case 1:
				try {
					crud.excluir("veiculos.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 2:
				try {
					crud.excluir("motoristas.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 3:
				try {
					crud.excluir("cobradores.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 4:
				try {
					crud.excluir("passageiros.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 5:
				try {
					crud.excluir("pontosDeParada.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 6:
				try {
					crud.excluir("trajetos.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 7:
				try {
					crud.excluir("jornadas.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;

			case 8:
				try {
					crud.excluir("embarques.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;
			
			case 9:
				try {
					crud.excluir("trechos.txt");
				} catch (Exception e) {
					System.out.println("Erro ao abrir arquivo.");
				}

				break;
			
				
			case 0:
				System.out.println("Voltando pro menu principal...");
				return;

			default:
				System.out.println("Opção Inválida.");
				break;
			}

		} while (opcao != 0);

		scanner.close(); // Fecha o scanner.
	}

}
