package filmes;

import java.util.ArrayList;


import org.json.JSONObject;

public class Filme {

	private String titulo;
	private int ano;
	private String genero;
	private ArrayList<String> elenco;

	// GETTERS AND SETTERS
	public ArrayList<String> getElenco() {
		return elenco;
	}

	public void setElenco(ArrayList<String> elenco) {
		this.elenco = elenco;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	// MAIN
	public static void main(String[] args) {

		// é necessário usar o caractere de escape "\" para poder inserir aspas em uma string sem fechar
		String jsonString = "{\"titulo\":\"Os Arquivos JSON\",\"ano\":\"1998\",\"genero\":\"ficção\"}";
			
		//Criando um objeto JSON
		JSONObject jsonObj = new JSONObject(jsonString);
		
		System.out.println(jsonObj.toString());
			
		//Adicionando arquivos 
		Filme filme = new Filme();

		filme.setTitulo(jsonObj.getString("titulo"));
		filme.setAno(Integer.parseInt(jsonObj.getString("ano")));
		filme.setGenero(jsonObj.getString("genero"));

		System.out.println(filme.getTitulo());
		System.out.println(filme.getAno());
		System.out.println(filme.getGenero());
		
		//Acrescentando atributos e valores
		jsonObj.put("sinopse", "Neste filme, o protagonista está começando a entender como manipular arquivos .json!");
		
		//Verificando como ficou o objeto JSON após a introdução de um novo atributo com valor
		System.out.println(jsonObj.toString());
		
		
		
				

	}

}
