package com.leosoft.redesocial.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	private Integer valor = 99;
	Integer numeroAleatorio = 1;
	Integer numeroPrimo = 1;
	
	@RequestMapping("/valor")
	public String retornaValor() {
		return valor.toString();
	}
	
	@RequestMapping("/aleatorio")
	public String retornaAleatorio() {
		
		// Crie uma instância da classe Random
		Random random = new Random();

		// Gere um número aleatório inteiro entre 0 e 99
		Integer numeroAleatorio = random.nextInt(100);
		return numeroAleatorio.toString();
	}
	
	@RequestMapping("/sequencia")
	public String retornaNumSequencia() {	
		numeroAleatorio++;
		return numeroAleatorio.toString();
	}
	
	@RequestMapping("/sequenciaprimos")
	public String retornaprimos() {	
		
		if(numeroPrimo%1 == 1 && numeroPrimo%numeroPrimo == 1) {
			numeroPrimo++;
		}
		
		numeroPrimo--;		
		return numeroPrimo.toString();
	}

}
