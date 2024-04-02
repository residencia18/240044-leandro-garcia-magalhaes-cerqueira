package com.example.usercrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.usercrud.model.Usuario;
import com.example.usercrud.repository.UsuarioRepository;

@SpringBootApplication
public class UsercrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UsercrudApplication.class, args);
	}
	
	@Autowired
    private UsuarioRepository usuarioRepository;

 
    @Override
    public void run(String... args) throws Exception {
    	
        // Criando um novo usuário
        Usuario usuario = new Usuario();
        usuario.setName("John Doe");
        usuario.setEmail("john@example.com");
        usuario.setSenha("123456");

        // Salvando o usuário no banco de dados
        usuarioRepository.save(usuario);

        // Buscando todos os usuários
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario u : usuarios) {
            System.out.println(u.getName() + " - " + u.getEmail());
        }
    }

}
