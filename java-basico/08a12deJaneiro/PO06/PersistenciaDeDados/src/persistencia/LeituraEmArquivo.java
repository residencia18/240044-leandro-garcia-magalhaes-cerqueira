package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;

public class LeituraEmArquivo {

	public static void main(String[] args) {
			
		String arquivo = "entrada.txt";
		
		FileReader fr;
		try {
			fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			
			while (br.ready()) {
				String linha = br.readLine();
				System.out.println(linha);
			}
			
			br.close();
			fr.close();
			
		} catch (Exception e) {
			System.out.println("O arquivo não foi encontrado.");
		}
						
	}

}
