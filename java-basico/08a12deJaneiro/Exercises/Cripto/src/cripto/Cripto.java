package cripto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cripto {
	
	public static void proccess (String file1, String file2) throws IOException {
		
		FileInputStream entrada = new FileInputStream(file1);
		FileOutputStream saida = new FileOutputStream(file2);
		
		//Leitura em um arquivo e escrita em outra 
		
		try {		
			boolean eof = false; int count = 0;
			while (!eof) {
				int input = entrada.read();
				if (input != -1) {
					saida.write(input);
					count++;
				} else eof = true;
				
			}
			entrada.close();
			saida.close();
			System.out.println("\nBytes lidos: " + count);
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		}		
	}
	
//	//Etapa 2, sem utilizar senha
//	public static void proccess(String file1, String file2, String senha) throws IOException {
//		
//		FileInputStream entrada = new FileInputStream(file1);
//		FileOutputStream saida = new FileOutputStream(file2);
//			
//		try {		
//			boolean eof = false; int count = 0;
//			while (!eof) {
//				
//				int input = entrada.read();
//				
//				//255 = 11111111
//				byte c = (byte) (input ^ 255);
//				if (input != -1) {
//					saida.write(c);
//					count++;
//				} else eof = true;
//				
//			}
//			entrada.close();
//			saida.close();
//			System.out.println("\nQuantidade de bytes lidos: " + count);
//		} catch (IOException e) {
//			System.out.println("Error -- " + e.toString());
//		}		
//	}
	
	//Etapa 3, utilizando senha
	public static void proccess(String file1, String file2, String senha) throws IOException {
		
		FileInputStream entrada = new FileInputStream(file1);
		FileOutputStream saida = new FileOutputStream(file2);
			
		try {		
			boolean eof = false; int count = 0;
			int i = 0;
			while (!eof) {
				
				int input = entrada.read();			
				
				byte b = (byte) senha.charAt(i);
				byte c = (byte) (input ^ b);
				
				i++;	
				if (i >= senha.length()) {
					i = 0;
				}
				
				if (input != -1) {
					saida.write(c);
					count++;
				} else eof = true;
			
			}
			
			entrada.close();
			saida.close();
			System.out.println("\nQuantidade de bytes lidos: " + count);
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		}		
	}
		
	}
	

