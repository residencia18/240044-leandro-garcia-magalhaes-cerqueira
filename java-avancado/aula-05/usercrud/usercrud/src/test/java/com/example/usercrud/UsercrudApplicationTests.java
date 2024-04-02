package com.example.usercrud;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.usercrud.exceptions.ValorSomaExcedidoException;
import com.example.usercrud.model.Usuario;

@SpringBootTest
class UsercrudApplicationTests {

	@Test
	void contextLoads() {
	}
	
	 @Test
	    public void testCalculaSoma() {
	    	
	    	System.out.println("Foi chamado.");
	        Usuario usuario = new Usuario();
	        
	        // Teste para soma dentro do limite
	        try {
	            int resultado = usuario.calculaSoma(100, 200);
	            assertEquals(300, resultado);
	        } catch (ValorSomaExcedidoException e) {
	            // Se houver uma exceção, o teste falha
	            e.printStackTrace();
	        }
	        
	        // Teste para soma excedendo o limite
	        try {
	            usuario.calculaSoma(300, 300);
	            // Se não lançar a exceção, o teste falha
	        } catch (ValorSomaExcedidoException e) {
	            // Se lançar a exceção, o teste passa
	            assertEquals("A soma não pode ser superior a 500", e.getMessage());
	        }
	    }

}
