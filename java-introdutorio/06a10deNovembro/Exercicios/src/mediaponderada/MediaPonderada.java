package mediaponderada;

import java.util.Scanner;

public class MediaPonderada {
	
	private float nota1;
	private float nota2;
	private float nota3;
	
	private float peso1;
	private float peso2;
	private float peso3;
	
	public float getNota1() {
		return nota1;
	}
	public void setNota1(float nota1) {
		this.nota1 = nota1;
	}
	public float getNota2() {
		return nota2;
	}
	public void setNota2(float nota2) {
		this.nota2 = nota2;
	}
	public float getNota3() {
		return nota3;
	}
	public void setNota3(float nota3) {
		this.nota3 = nota3;
	}
	public float getPeso1() {
		return peso1;
	}
	public void setPeso1(float peso1) {
		this.peso1 = peso1;
	}
	public float getPeso2() {
		return peso2;
	}
	public void setPeso2(float peso2) {
		this.peso2 = peso2;
	}
	public float getPeso3() {
		return peso3;
	}
	public void setPeso3(float peso3) {
		this.peso3 = peso3;
	}
	
	public float calculaMediaPonderada(float n1,float n2, float n3, float p1, float p2, float p3) {
	
	float somaNotas = n1 * p1 + n2 * p2 + n3 * p3;
	float somaPesos = p1 + p2 + p3;
	
	float mediaPonderada = somaNotas / somaPesos;
	
	return mediaPonderada;

}
	
	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		
		MediaPonderada mediaPonderada = new MediaPonderada();
		
		System.out.println("Insira a nota 1: ");
		float nota1 = scanner.nextFloat();
		mediaPonderada.setNota1(nota1);
		
		System.out.println("Insira a nota 2 ");
		float nota2 = scanner.nextFloat();
		mediaPonderada.setNota2(nota2);
		
		System.out.println("Insira a nota 3: ");
		float nota3 = scanner.nextFloat();
		mediaPonderada.setNota3(nota3);
		
		System.out.println("Insira a peso 1: ");
		float peso1 = scanner.nextFloat();
		mediaPonderada.setPeso1(peso1);
		
		System.out.println("Insira a peso 2: ");
		float peso2 = scanner.nextFloat();
		mediaPonderada.setPeso2(peso2);
		
		System.out.println("Insira a peso 3: ");
		float peso3 = scanner.nextFloat();
		mediaPonderada.setPeso3(peso3);
		
		float resultado = mediaPonderada.calculaMediaPonderada(nota1,nota2,nota3,peso1,peso2,peso3);
		
		System.out.println("Resultado: " + resultado);
	
		scanner.close();
	}
}