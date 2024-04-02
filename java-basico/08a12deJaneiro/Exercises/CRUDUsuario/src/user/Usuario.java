package user;

import java.util.List;

public class Usuario {
	
	private String Nome;
	private String Email;
	private String Senha;
	private List<String> ListaPostagens;
	
	//GETTERS AND SETTERS
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public List<String> getListaPostagens() {
		return ListaPostagens;
	}
	public void setListaPostagem(List<String> listaPostagem) {
		ListaPostagens = listaPostagem;
	}
	
	//CONSTRUCTOR
	
	public Usuario(String nome, String email) {
		super();
		Nome = nome;
		Email = email;
	}
	

	
	
	

}
