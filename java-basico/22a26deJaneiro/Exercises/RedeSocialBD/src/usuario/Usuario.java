package usuario;

import java.util.ArrayList;

public class Usuario {
	
	private String login, senha, email;
	private ArrayList<String> postagens;

	//Constructors
	public Usuario() {
		postagens = new ArrayList<>();
	}

	public Usuario(String login, String senha, String email) {
		super();
		this.login = login;
		this.senha = senha;
		this.email = email;
		postagens = new ArrayList<>();
	}
	
	
	
	




	//GETTERS AND SETTERS
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public ArrayList<String> getPostagens() {
		return postagens;
	}
	

	public void setPostagens(ArrayList<String> postagens) {
		this.postagens = postagens;
	}
	
	public void addPostagem(String postagem) {
		postagens.add(postagem);
	}
	
	
}
