package transporte;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);		
		Menus menus = new Menus();
				
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


