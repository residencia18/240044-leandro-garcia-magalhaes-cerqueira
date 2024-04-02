package persistencia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EscritaEmArquivo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        File arquivo = new File("saida.txt");

        try (FileWriter fw = new FileWriter(arquivo); BufferedWriter bw = new BufferedWriter(fw)) {
            String texto;

            do {
                System.out.println("Insira um texto (ou pressione ENTER para finalizar): ");
                texto = scanner.nextLine();

                bw.write(texto);
                bw.newLine();

            } while (!texto.isEmpty());
            
            arquivo.createNewFile();
            
            System.out.println("Programa finalizado!");

        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo.");
        } finally {
            scanner.close();
        }
    }
}




