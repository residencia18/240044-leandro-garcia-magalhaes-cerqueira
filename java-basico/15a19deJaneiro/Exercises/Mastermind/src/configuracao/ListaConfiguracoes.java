package configuracao;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class ListaConfiguracoes {

	public static final String CAMINHO = "." + File.separator + "arquivos" + File.separator;

	private String nomeArquivo;
	
	private ArrayList<Configuracao> configs;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public ArrayList<Configuracao> getconfigs() {
		return configs;
	}

	public void setLista(ArrayList<Configuracao> configs) {
		this.configs = configs;
	}

	public ListaConfiguracoes() {
		super();
		nomeArquivo = CAMINHO + "configs.json";
		configs = new ArrayList<Configuracao>();
	}

	public void novaConfig(Configuracao configuracao) throws Exception {

		for(Configuracao config : configs) {
			if (config.getNome() == configuracao.getNome()) {
				Exception e = new Exception("Já existe uma configuração chamada " + configuracao.getNome());
				throw e;
			}
		}
		
		configs.add(configuracao);		
	}

	public void salvarConfigs() {
		//CONTINUA DPS
		
		//Instancia um novo objeto json
		JSONObject myObj = new JSONObject();
				
		
	
	}
	
}
