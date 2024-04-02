package app;

import java.util.Scanner;
import coelho.Menus;

public class App {
	
	public static void main(String[] args) {

	    Scanner scanner = new Scanner(System.in);
	    Menus menus = new Menus();

	    int escolha;

	    do {
	        System.out.println("COELHO - Distribuidora de energia");
	        System.out.println(" ");
	        System.out.println("[1] - Menu de Clientes");
	        System.out.println("[2] - Menu de Imóveis");
	        System.out.println("[3] - Menu de Faturas");
	        System.out.println("[4] - Menu de Pagamentos");
	        System.out.println("[0] - Encerrar programa");
	        System.out.println(" ");
	        System.out.println("Escolha uma opção: ");

	        while (!scanner.hasNextInt()) {
	            System.out.println("Opção inválida. Digite novamente: ");
	            scanner.nextLine(); // Limpar o buffer
	        }

	        escolha = scanner.nextInt();
	        scanner.nextLine(); // Limpar o buffer

	        switch (escolha) {
	            case 1:
	                menus.runMenuClientes();
	                break;

	            case 2:
	                menus.runMenuImoveis();
	                break;

	            case 3:
	                menus.runMenuFaturas();
	                break;

	            case 4:
	                menus.runMenuPagamentos();
	                break;

	            case 0:
	                System.out.println("Encerrando programa...");
	                break;

	            default:
	                System.out.println("Opção inválida.");
	                break;
	        }

	    } while (escolha != 0);

	    scanner.close();
	}


}
