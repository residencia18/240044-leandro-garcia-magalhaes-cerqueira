package manipulacao;

import java.util.Scanner;
import java.util.Random;

public class ManipulaArray{
    
	public static int[] geraArrayPeloUsuario() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Informe o tamanho do array: ");
        int tamanho = scanner.nextInt();

        int[] arrayUsuario = new int[tamanho];

        System.out.println("Informe os elementos do array:");
        for (int i = 0; i < tamanho; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            arrayUsuario[i] = scanner.nextInt();
        }

        scanner.close();
        return arrayUsuario;
    }
	
    public static int[] geraArrayAleatoria(int tamanho, int minValue, int maxValue) {
        int[] arrayAleatoria = new int[tamanho];
        Random random = new Random();

        for (int i = 0; i < tamanho; i++) {
            arrayAleatoria[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }

        return arrayAleatoria;
    }
    
    public static int somaElementos(int [] array){
    	int soma = 0;
    	for (int i = 0; i < array.length; i++){
    		
    		soma += array[i];
    	}
    	
    	return soma;
   	
    }
    
    public static int MaiorElemento(int [] array){
    	int maior = 0;
    	for (int i = 0; i < array.length; i++){
    		
    		if(array[i] > maior) {
    			maior = array[i];
    		}
    	}
  	
    	return maior;
    }
    
    public static int MenorElemento(int [] array){
    	int menor = array[0];
    	for (int i = 0; i < array.length; i++){
    		
    		if(array[i] < menor) {
    			menor = array[i];
    		}
    	}	
    	return menor;
    }
    
    public static void main(String[] args) {
        int[] arrayAleatoria = geraArrayAleatoria(5, 1, 10); // Exemplo com array de tamanho 5, valores entre 1 e 10
        int[] arrayUsuario = geraArrayPeloUsuario();
       
        for (int n : arrayAleatoria) {
            System.out.print(n + " ");
        }
        
        System.out.println();
        
        for (int n : arrayUsuario) {
        	System.out.print(n + " ");
        }
        
        // Chamando as funções e exibindo os resultados
        int soma = somaElementos(arrayAleatoria);
        int maior = MaiorElemento(arrayUsuario);
        int menor = MenorElemento(arrayUsuario);

        System.out.println("Soma dos elementos da array aleatória: " + soma);
        System.out.println("Maior elemento da array criada pelo usuário: " + maior);
        System.out.println("Menor elemento da array criada pelo usuário: " + menor);
        
        
        
    }
    
}
    