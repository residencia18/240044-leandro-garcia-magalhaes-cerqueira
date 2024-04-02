package transporte;

import java.util.Scanner;

import cadastros.CRUD;

public class App {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);		
		CRUD crud = new CRUD();		
		Menus menus = new Menus(crud);
		System.out.println("DADOS CARREGADOS: \n");
		crud.runVeiculos();
		System.out.println("----------------------");
		crud.runMotoristas();
		System.out.println("----------------------");
		crud.runCobradores();
		System.out.println("----------------------");
		crud.runPassageiros();
		System.out.println("----------------------");
		crud.runPontos();
		System.out.println("----------------------");
		crud.runTrechos();
		System.out.println("----------------------");
		crud.runTrajetos();
		System.out.println("----------------------");
		crud.runJornadas();
		System.out.println("----------------------");
		crud.runEmbarques();
		System.out.println("----------------------");
		crud.runCheckpoints();
		System.out.println("----------------------");
		
		
	
			
		int opcao;
				
		do {
			System.out.println("======= PAINEL DE CONTROLE - TRANSPORTE VIÁRIO =======");
			System.out.println("");
			System.out.println("[1] - Cadastrar dados");
			System.out.println("[2] - Alterar dados");
			System.out.println("[3] - Excluir dados");
			System.out.println("[0] - Sair.");
			System.out.println("");
			System.out.println("Escolha uma opção: ");
			opcao = scanner.nextInt();
				
			switch (opcao) {
				
			case 1:
				menus.runMenuCadastro();	
				break;
					
			case 2:
				menus.runMenuAlteracao();
				break;
				
			case 3:
				menus.runMenuExclusao();
				break;
						
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


