package configuracao;

public class Configuracao {
	
	private String nome;
	private String alfabeto;
	private int tamanhoSenha;
	private int maxTentativas;
	
	public Configuracao() {
		super();
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Configuracao(String nome, String alfabeto, int tamanhoSenha, int maxTentativas) {
		super();
		this.nome = nome;
		this.alfabeto = alfabeto;
		this.tamanhoSenha = tamanhoSenha;
		this.maxTentativas = maxTentativas;
	}
	
	public String getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(String alfabeto) throws Exception {
		
		if ((alfabeto == null) || (alfabeto.length() < 2)){
			Exception e = new Exception("O alfabeto deve possuir mais de 1 caracter");
			 throw e;
		}
				
		this.alfabeto = alfabeto;
	}
	
	public int getTamanhoSenha() {
		return tamanhoSenha;
	}

	public void setTamanhoSenha(int tamanhoSenha) throws Exception {
		
		if (tamanhoSenha <= 0) {
			Exception e = new Exception("Senha deve ter ao menos 1 caracter");
			throw e;
		}
			
		if (tamanhoSenha > alfabeto.length()) {
			Exception e = new Exception("Senha não pode ser maior que o alfabeto (10 caracteres)");
			throw e;
		}
							
		this.tamanhoSenha = tamanhoSenha;
	}
	
	public int getMaxTentativas() {
		return maxTentativas;
	}

	public void setMaxTentativas(int maxTentativas) throws Exception {
		
		if (maxTentativas <= 1) {
			Exception e = new Exception("O número de tentativas deve ser pelo menor maior que 1");
			throw e;
		}
		
		this.maxTentativas = maxTentativas;
	}
	
	
}
