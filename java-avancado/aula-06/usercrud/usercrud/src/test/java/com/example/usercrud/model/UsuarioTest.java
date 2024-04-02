package com.example.usercrud.model;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.usercrud.exceptions.ValorSomaExcedidoException;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UsuarioTest {

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
