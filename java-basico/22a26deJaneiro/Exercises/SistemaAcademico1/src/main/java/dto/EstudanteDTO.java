package dto;

public class EstudanteDTO {
	
	private String nome;
	private String matricula;
	//adicionado depois
	private String nomeCurso;
	
	
	// Construtores
	public EstudanteDTO() {
		super();
	}

	public EstudanteDTO(String nome, String matricula) {
		super();
		this.nome = nome;
		this.matricula = matricula;
	}


	// Getters and Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	
	
	

}
