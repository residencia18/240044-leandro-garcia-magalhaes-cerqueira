package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CopiaDeArquivos {
	
	public static void main(String[] args) throws Exception {
	
		String arquivoOrigem = "origem.txt";
			
		try {
			
			
			FileReader fr = new FileReader(arquivoOrigem);
			BufferedReader br = new BufferedReader(fr);
					
		ArrayList<String> linhas = new ArrayList<>();
		
		while(br.ready()) {
			linhas.add(br.readLine());
		}
			
			fr.close();
			br.close();
			
			File arquivoDestino = new File("destino.txt");
			
			if (!arquivoDestino.exists()) {
			    arquivoDestino.createNewFile();
			}
			
			
			FileWriter fw = new FileWriter(arquivoDestino);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(String linha : linhas) {
				bw.write(linha);
				bw.newLine();
				System.out.println(linha);
			}
			
			System.out.println("Conteúdo copiado com sucesso!");
			
			
			//O bw precisa ser fechado antes do fw.
			bw.close();
			fw.close();

					
			
		} catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo.");
        }
					
		
	}

}
