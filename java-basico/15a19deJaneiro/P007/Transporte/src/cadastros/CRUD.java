package cadastros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import transporte.Cobrador;
import transporte.Embarque;
import transporte.Jornada;
import transporte.Motorista;
import transporte.Passageiro;
import transporte.PontoDeParada;
import transporte.Trajeto;
import transporte.Trecho;
import transporte.Veiculo;

public class CRUD {

	public CRUD() {
		super();
	}

	private Scanner scanner = new Scanner(System.in);

	private ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
	private ArrayList<Motorista> listaMotoristas = new ArrayList<>();
	private ArrayList<Cobrador> listaCobradores = new ArrayList<>();
	private ArrayList<Passageiro> listaPassageiros = new ArrayList<>();
	private ArrayList<PontoDeParada> listaPontosDeParada = new ArrayList<>();
	private ArrayList<Trecho> listaTrechos = new ArrayList<>();
	private ArrayList<Trecho> listaTrechosTrajeto = new ArrayList<>();
	private ArrayList<Trajeto> listaTrajetos = new ArrayList<>();
	private ArrayList<Trajeto> listaTrajetosJornada = new ArrayList<>();
	private ArrayList<Jornada> listaJornadas = new ArrayList<>();
	private ArrayList<Embarque> listaEmbarques = new ArrayList<>();

	// Método de inicialização
	public void runVeiculos() {
		System.out.println("Veiculos: \n");

		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("veiculos.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray veiculosArray = jsonRootObject.getJSONArray("veiculos");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < veiculosArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject veiculoObj = veiculosArray.getJSONObject(i);

				// Obter os dados do veículo
				String modelo = veiculoObj.getString("modelo");
				String placa = veiculoObj.getString("placa");

				// CONVERTENDO EM OBJETO JAVA
				Veiculo veiculo = new Veiculo(modelo, placa);
				listaVeiculos.add(veiculo);

				// Exemplo de uso dos dados
				System.out.println("Veículo " + (i + 1));
				System.out.println("Modelo: " + modelo);
				System.out.println("Placa: " + placa);
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Método de inicialização
	public void runMotoristas() {
		System.out.println("Motoristas: \n");
		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("motoristas.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray motoristasArray = jsonRootObject.getJSONArray("motoristas");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < motoristasArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject motoristaObj = motoristasArray.getJSONObject(i);

				// Obter os dados do veículo
				String nome = motoristaObj.getString("nome");
				String cpf = motoristaObj.getString("cpf");
				
				// CONVERTENDO EM OBJETO JAVA
				Motorista motorista = new Motorista(nome,cpf);
				listaMotoristas.add(motorista);
				
				// Exemplo de uso dos dados
				System.out.println("Motorista " + (i + 1));
				System.out.println("Nome: " + nome);
				System.out.println("CPF: " + cpf);
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Método de inicialização
	public void runCobradores() {
		System.out.println("Cobradores: \n");
		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("cobradores.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray cobradoresArray = jsonRootObject.getJSONArray("cobradores");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < cobradoresArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject cobradorObj = cobradoresArray.getJSONObject(i);

				// Obter os dados do veículo
				String nome = cobradorObj.getString("nome");
				String cpf = cobradorObj.getString("cpf");
				
				// CONVERTENDO EM OBJETO JAVA
				Cobrador cobrador = new Cobrador(nome,cpf);
				listaCobradores.add(cobrador);

				// Exemplo de uso dos dados
				System.out.println("Cobrador " + (i + 1));
				System.out.println("Nome: " + nome);
				System.out.println("CPF: " + cpf);
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Método de inicialização
	public void runPassageiros() {
		System.out.println("Passageiros: \n");
		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("passageiros.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray passageirosArray = jsonRootObject.getJSONArray("passageiros");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < passageirosArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject passageiroObj = passageirosArray.getJSONObject(i);

				// Obter os dados do veículo
				String nome = passageiroObj.getString("nome");
				String cpf = passageiroObj.getString("cpf");
				String tipoCartao = passageiroObj.getString("tipoCartao");
				
				// CONVERTENDO EM OBJETO JAVA
				Passageiro passageiro = new Passageiro(nome, cpf, tipoCartao);
				listaPassageiros.add(passageiro);

				// Exemplo de uso dos dados
				System.out.println("Passageiro " + (i + 1));
				System.out.println("Nome: " + nome);
				System.out.println("CPF: " + cpf);
				System.out.println("Tipo do cartão: " + tipoCartao);
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Método de inicialização
	public void runPontos() {
		System.out.println("Pontos de Parada: \n");
		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("pontosDeParada.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray pontosArray = jsonRootObject.getJSONArray("pontosDeParada");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < pontosArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject pontoObj = pontosArray.getJSONObject(i);

				// Obter os dados do veículo
				String nomePonto = pontoObj.getString("ponto");

				// CONVERTENDO EM OBJETO JAVA
				PontoDeParada ponto = new PontoDeParada(nomePonto);
				listaPontosDeParada.add(ponto);

				// Exemplo de uso dos dados
				System.out.println("Ponto de parada: " + (i + 1));
				System.out.println("Ponto: " + nomePonto);
				System.out.println();
			}


		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Método de inicialização
	public void runTrechos() {
		System.out.println("Trechos: \n");
		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("trechos.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray trechosArray = jsonRootObject.getJSONArray("trechos");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < trechosArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject trechoObj = trechosArray.getJSONObject(i);

				// Obter os dados do veículo
				String pontos = trechoObj.getString("pontos");
				String intervalo = trechoObj.getString("intervalo");
				
				// CONVERTENDO EM OBJETO JAVA
				Trecho trecho = new Trecho(pontos, Integer.parseInt(intervalo));
				listaTrechos.add(trecho);

				// Exemplo de uso dos dados
				System.out.println("Trecho " + (i + 1));
				System.out.println("Pontos: " + pontos);
				System.out.println("Intervalo estimado: " + intervalo);
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Método de inicialização
	public void runTrajetos() {
		System.out.println("Trajetos: \n");
		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("trajetos.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray trajetosArray = jsonRootObject.getJSONArray("trajetos");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < trajetosArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject trajetoObj = trajetosArray.getJSONObject(i);

				// Obter os dados do veículo
				String trechos = trajetoObj.getString("trechos");
				
				// CONVERTENDO EM OBJETO JAVA
				Trajeto trajeto = new Trajeto(trechos);
				listaTrajetos.add(trajeto);
				

				// Exemplo de uso dos dados
				System.out.println("Trajeto " + (i + 1));
				System.out.println("Trechos: " + trechos);
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Método de inicialização
	public void runJornadas() {
		System.out.println("Jornadas: \n");
		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("jornadas.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray jornadasArray = jsonRootObject.getJSONArray("jornadas");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < jornadasArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject jornadaObj = jornadasArray.getJSONObject(i);

				// Obter os dados do veículo
				String trajetos = jornadaObj.getString("trajetos");
				String motorista = jornadaObj.getString("motorista");
				String veiculo = jornadaObj.getString("veiculo");
				
				// CONVERTENDO EM OBJETO JAVA
				Jornada jornada = new Jornada(trajetos, motorista, veiculo);
				listaJornadas.add(jornada);

				// Exemplo de uso dos dados
				System.out.println("Jornada " + (i + 1));
				System.out.println("Trajetos: " + trajetos);
				System.out.println("Motorista: " + motorista);

				try {
					String cobrador = jornadaObj.getString("cobrador");
					System.out.println("Cobrador: " + cobrador);
				} catch (Exception e) {
					System.out.println("Sem cobrador");
				}

				System.out.println("Veiculo: " + veiculo);
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Método de inicialização
	public void runEmbarques() {
		System.out.println("Embarques: \n");
		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("embarques.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray embarquesArray = jsonRootObject.getJSONArray("embarques");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < embarquesArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject embarqueObj = embarquesArray.getJSONObject(i);

				// Obter os dados do veículo
				String passageiro = embarqueObj.getString("passageiro");
				String pontoDeEmbarque = embarqueObj.getString("pontoDeEmbarque");
				String horario = embarqueObj.getString("horario");
				
				// CONVERTENDO EM OBJETO JAVA
				Embarque embarque = new Embarque(passageiro, pontoDeEmbarque, horario);
				listaEmbarques.add(embarque);

				// Exemplo de uso dos dados
				System.out.println("Embarque " + (i + 1));
				System.out.println("Passageiro: " + passageiro);
				System.out.println("Ponto de Embarque: " + pontoDeEmbarque);
				System.out.println("Horário de embarque: " + horario);
			}

		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Método de inicialização
	public void runCheckpoints() {
		System.out.println("Checkpoints: \n");
		try {
			// Ler o conteúdo do arquivo JSON como uma string
			String jsonContent = new String(Files.readAllBytes(Paths.get("checkpoints.json")));

			// Criar um objeto JSONObject a partir da string JSON
			JSONObject jsonRootObject = new JSONObject(jsonContent);

			// Obter a matriz de veículos do objeto raiz
			JSONArray checkpointsArray = jsonRootObject.getJSONArray("checkpoints");

			// Iterar sobre os veículos na matriz
			for (int i = 0; i < checkpointsArray.length(); i++) {
				// Obter o objeto do veículo atual
				JSONObject checkpointObj = checkpointsArray.getJSONObject(i);

				// Obter os dados do veículo
				String trajetoSelecionado = checkpointObj.getString("trajetoSelecionado");
				String checkpoint = checkpointObj.getString("checkpoint");
				
				// Exemplo de uso dos dados
				System.out.println("Checkpoint " + (i + 1));
				System.out.println("Trajeto: " + trajetoSelecionado);
				System.out.println("Horário: " + checkpoint);
			}

		} catch (Exception e) {
			System.out.println("Não foi possível carregar os dados. O arquivo possívelmente está vazio.");
		}
	}

	// Métodos de Cadastros

	public void cadastrarVeiculo() throws Exception {

		File arquivo = new File("veiculos.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("veiculos", new JSONArray());
		}

		while (true) {
			System.out.println("Digite o modelo do veículo (ou digite 'x' para encerrar):");
			String modelo = scanner.nextLine();

			if (modelo.equalsIgnoreCase("x")) {
				break;
			}

			System.out.println("Digite a placa do veículo:");
			String placa = scanner.nextLine();

			Veiculo veiculo = new Veiculo(modelo, placa);
			listaVeiculos.add(veiculo);

			System.out.println("Veículo cadastrado com sucesso!");
			System.out.println(" ");

			// Adicionar o novo veículo ao array 'veiculos' no objeto JSON
			json.getJSONArray("veiculos").put(new JSONObject().put("modelo", modelo).put("placa", placa));
		}

		// Escrever o objeto JSON atualizado no arquivo
		try (FileWriter fw = new FileWriter(arquivo)) {
			fw.write(json.toString());
		}
	}

	public void cadastrarMotorista() throws Exception {

		File arquivo = new File("motoristas.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("motoristas", new JSONArray());
		}

		while (true) {
			System.out.println("Digite o nome: (ou digite 'x' para encerrar):");
			String nome = scanner.nextLine();

			if (nome.equalsIgnoreCase("x")) {
				break;
			}

			System.out.println("Digite o CPF:");
			String cpf = scanner.nextLine();

			Motorista motorista = new Motorista(nome, cpf);
			listaMotoristas.add(motorista);

			System.out.println("Motorista cadastrado com sucesso!");
			System.out.println(" ");

			// Adicionar o novo motorista ao array 'motoristas' no objeto JSON
			json.getJSONArray("motoristas").put(new JSONObject().put("nome", nome).put("cpf", cpf));
		}

		// Escrever o objeto JSON atualizado no arquivo
		try (FileWriter fw = new FileWriter(arquivo)) {
			fw.write(json.toString());
		}
	}

	public void cadastrarCobrador() throws Exception {

		File arquivo = new File("cobradores.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("cobradores", new JSONArray());
		}

		while (true) {
			System.out.println("Digite o nome: (ou digite 'x' para encerrar):");
			String nome = scanner.nextLine();

			if (nome.equalsIgnoreCase("x")) {
				break;
			}

			System.out.println("Digite o CPF:");
			String cpf = scanner.nextLine();

			Cobrador cobrador = new Cobrador(nome, cpf);
			listaCobradores.add(cobrador);

			System.out.println("Cobrador cadastrado com sucesso!");
			System.out.println(" ");

			// Adicionar o novo motorista ao array 'motoristas' no objeto JSON
			json.getJSONArray("cobradores").put(new JSONObject().put("nome", nome).put("cpf", cpf));
		}

		// Escrever o objeto JSON atualizado no arquivo
		try (FileWriter fw = new FileWriter(arquivo)) {
			fw.write(json.toString());
		}
	}

	public void cadastrarPassageiro() throws Exception {

		File arquivo = new File("passageiros.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("passageiros", new JSONArray());
		}

		while (true) {

			System.out.println("Digite o nome: (ou digite 'x' para encerrar):");
			String nome = scanner.nextLine();

			if (nome.equalsIgnoreCase("x")) {
				break;
			}

			System.out.println("Digite o CPF:");
			String cpf = scanner.nextLine();

			System.out.println("----- Tipo de cartão -----");
			System.out.println("[1] - Cartão Estudantil");
			System.out.println("[2] - Cartão do Idoso");
			System.out.println("[3] - Cartão de Transporte");
			System.out.println("--------------------------");

			System.out.println("Escolha uma opção: ");
			String tipoCartao = scanner.nextLine();
			scanner.nextLine(); // Limpando o buffer

			Passageiro passageiro = new Passageiro(nome, cpf, tipoCartao);
			listaPassageiros.add(passageiro);

			// Adicionar o novo passageiro ao array 'passageiros' no objeto JSON
			json.getJSONArray("passageiros")
					.put(new JSONObject().put("nome", nome).put("cpf", cpf).put("tipoCartao", tipoCartao));

			System.out.println("Passageiro cadastrado com sucesso!");
			System.out.println(" ");
		}

		// Escrever o objeto JSON atualizado no arquivo
		try (FileWriter fw = new FileWriter(arquivo)) {
			fw.write(json.toString());
		}
	}

	public void cadastrarPontosDeParada() throws Exception {

		File arquivo = new File("pontosDeParada.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("pontosDeParada", new JSONArray());
		}

		while (true) {

			System.out.println("Digite o nome do ponto: (ou digite 'x' para encerrar):");
			String nomePonto = scanner.nextLine();

			if (nomePonto.equalsIgnoreCase("x")) {
				break;
			}

			PontoDeParada pontoDeParada = new PontoDeParada(nomePonto);
			listaPontosDeParada.add(pontoDeParada);

			// Adicionar o novo motorista ao array 'motoristas' no objeto JSON
			json.getJSONArray("pontosDeParada").put(new JSONObject().put("ponto", nomePonto));

			System.out.println("Ponto de parada cadastrado com sucesso!");
			System.out.println(" ");
		}

		// Escrever o objeto JSON atualizado no arquivo
		try (FileWriter fw = new FileWriter(arquivo)) {
			fw.write(json.toString());
		}

	}

	public void cadastrarTrecho() throws Exception {

		File arquivo = new File("trechos.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("trechos", new JSONArray());
		}

		while (true) {
			System.out.println("--------- Cadastro de trecho -----------");
			System.out.println("----- Pontos de Parada disponíveis -----");

			// Exibindo os pontos de parada disponíveis
			ArrayList<PontoDeParada> pontosDeParada = getListaPontosDeParada();

			for (int i = 0; i < listaPontosDeParada.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + pontosDeParada.get(i).getPonto());
			}

			System.out.println("Digite a opção do ponto de partida:");
			int pontoA = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			System.out.println("Digite a opção do ponto de chegada:");
			int pontoB = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			// Verificar se as opções são válidas
			if (pontoA < 1 || pontoA > pontosDeParada.size() || pontoB < 1 || pontoB > listaPontosDeParada.size()) {
				System.out.println("Opção inválida.");
				break; // Sai do loop se a opção for inválida
			}

			PontoDeParada pontoPartida = pontosDeParada.get(pontoA - 1);
			PontoDeParada pontoChegada = pontosDeParada.get(pontoB - 1);

			String pontos = pontoPartida.getPonto() + " - " + pontoChegada.getPonto();

			System.out.println("Digite o tempo de duração estimada do trecho (em minutos):");
			int intervalo = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			Trecho trecho = new Trecho(pontos, intervalo);
			listaTrechos.add(trecho);

			// Adicionar o novo passageiro ao array 'passageiros' no objeto JSON
			json.getJSONArray("trechos")
					.put(new JSONObject().put("pontos", pontos).put("intervalo", String.valueOf(intervalo)));

			System.out.println("Trecho cadastrado com sucesso!");
			System.out.println(" ");

			System.out.println("Digite 'x' para sair ou ENTER para cadastrar outro trecho: ");
			String sair = scanner.nextLine();

			if (sair.equalsIgnoreCase("x")) {
				break; // Sai do loop se o usuário digitar 'x'
			}
		}

		// Escrever o objeto JSON atualizado no arquivo
		try (FileWriter fw = new FileWriter(arquivo)) {
			fw.write(json.toString());
		}
	}

	public void cadastrarTrajeto() throws Exception {

		File arquivo = new File("trajetos.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("trajetos", new JSONArray());
		}

		try {
			cadastrarTrecho();
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar trecho.");
		}

		System.out.println(" ");
		System.out.println("----- CADASTRO DE TRAJETO -----");
		System.out.println(" ");

		ArrayList<Trecho> trechos = getListaTrechos();

		while (true) {

			System.out.println("----- Trechos disponíveis -----");

			// Exibindo os trechos disponíveis

			for (int i = 0; i < trechos.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + trechos.get(i).getTrecho() + ", "
						+ trechos.get(i).getIntervalo() + " minutos");
			}

			System.out.println("Escolha o trecho: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			listaTrechosTrajeto.add(trechos.get(opcao - 1));

			System.out.println("Pressione ENTER" + " para continuar ou 'x' para sair: ");
			String sair = scanner.nextLine();

			if (sair.equalsIgnoreCase("x")) {
				break;
			}
		}

		Trajeto trajeto = new Trajeto(listaTrechosTrajeto);

		listaTrajetos.add(trajeto);

		// Adicionar o novo passageiro ao array 'passageiros' no objeto JSON
		json.getJSONArray("trajetos").put(new JSONObject().put("trechos", String.valueOf(listaTrechosTrajeto)));

		System.out.println("Trajeto cadastrado com sucesso!");
		System.out.println(" ");

		// Escrever o objeto JSON atualizado no arquivo
		try (FileWriter fw = new FileWriter(arquivo)) {
			fw.write(json.toString());
		}
	}

	public void registraJornada() throws Exception {

		File arquivo = new File("jornadas.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("jornadas", new JSONArray());
		}

		// Exibindo os trajetos disponíveis
		ArrayList<Trajeto> trajetos = getListaTrajetos();
		for (int i = 0; i < trajetos.size(); i++) {
			System.out.println("----------------------------------");
			System.out.println("Trajeto: (" + (i + 1) + ") - " + trajetos.get(i).getTrechos());
		}

		while (true) {

			System.out.println("Selecione o trajeto desejado (ou digite '0' para encerrar):");
			int trajeto = scanner.nextInt();
			scanner.nextLine();
			System.out.println(" ");

			if (trajeto == 0) {
				break;
			}

			Trajeto trajetoJornada = trajetos.get(trajeto - 1);
			listaTrajetosJornada.add(trajetoJornada);

			// Exibindo os motoristas disponíveis
			ArrayList<Motorista> motoristas = getListaMotoristas();
			for (int i = 0; i < motoristas.size(); i++) {
				System.out.println("Motorista [" + (i + 1) + "] " + motoristas.get(i).getNome() + ","
						+ motoristas.get(i).getCpf());
				System.out.println("----------------------------------");
			}

			System.out.println("Selecione o motorista desejado (ou digite '0' para encerrar):");
			int motorista = scanner.nextInt();
			scanner.nextLine();

			if (motorista == 0) {
				break;
			}

			Motorista motoristaJornada = motoristas.get(motorista - 1);

			// Exibindo os veiculos disponíveis
			ArrayList<Veiculo> veiculos = getListaVeiculos();
			for (int i = 0; i < veiculos.size(); i++) {
				System.out.println("----------------------------------");
				System.out.println("Ônibus [" + (i + 1) + "] " + veiculos.get(i).getModelo() + " - Placa: "
						+ veiculos.get(i).getPlaca());
			}

			System.out.println("Selecione o ônibus desejado (ou digite '0' para encerrar):");
			int veiculo = scanner.nextInt();
			scanner.nextLine();

			if (veiculo == 0) {
				break;
			}

			Veiculo veiculoJornada = veiculos.get(veiculo - 1);

			// Questiona se haverá cobrador ou não
			System.out.println("A jornada terá cobrador? (s/n) ");
			String escolha = scanner.nextLine();

			if (escolha.equalsIgnoreCase("s")) {

				// Exibindo os motoristas disponíveis
				ArrayList<Cobrador> cobradores = getListaCobradores();
				for (int i = 0; i < cobradores.size(); i++) {
					System.out.println("----------------------------------");
					System.out.println("Cobrador [" + (i + 1) + "] " + cobradores.get(i).getNome() + ","
							+ cobradores.get(i).getCpf());
				}

				System.out.println("Selecione o cobrador desejado (ou digite '0' para encerrar):");
				int cobrador = scanner.nextInt();
				scanner.nextLine();

				if (cobrador == 0) {
					break;
				}

				Cobrador cobradorJornada = cobradores.get(cobrador - 1);

				Jornada jornada = new Jornada(trajetos, motoristaJornada, cobradorJornada, veiculoJornada);
				listaJornadas.add(jornada);

				// Adicionar o novo passageiro ao array 'passageiros' no objeto JSON
				json.getJSONArray("jornadas")
						.put(new JSONObject().put("trajetos", String.valueOf(trajetoJornada))
								.put("motorista", motoristaJornada.getNome()).put("cobrador", cobradorJornada.getNome())
								.put("veiculo", veiculoJornada.getModelo()));

			} else {
				Jornada jornada = new Jornada(trajetos, motoristaJornada, veiculoJornada);
				listaJornadas.add(jornada);

				// Adicionar o novo passageiro ao array 'passageiros' no objeto JSON
				json.getJSONArray("jornadas").put(new JSONObject().put("trajetos", String.valueOf(trajetoJornada))
						.put("motorista", motoristaJornada.getNome()).put("veiculo", veiculoJornada.getModelo()));
			}
		}

		System.out.println("Jornada cadastrada com sucesso!");
		System.out.println(" ");

		// Escrever o objeto JSON atualizado no arquivo
		try (FileWriter fw = new FileWriter(arquivo)) {
			fw.write(json.toString());
		}
	}

	public void registraInicioDeTrajeto() {
		// Exibindo as jornadas disponíveis
		ArrayList<Jornada> jornadas = getListaJornadas();
		for (int i = 0; i < jornadas.size(); i++) {
			System.out.println("----------------------------------");
			System.out.println("Jornada [" + (i + 1) + "] " + jornadas.get(i).getTrajetos());
			System.out.println("Motorista: " + jornadas.get(i).getMotorista().getNome());
			try {
				System.out.println("Cobrador: " + jornadas.get(i).getCobrador().getNome());
			} catch (NullPointerException e) {
				System.out.println("Esta jornada não possui cobrador.");
			}
			System.out.println("veículo: " + jornadas.get(i).getVeiculo().getModelo());
		}

		System.out.println("Selecione a jornada desejada: ");
		int escolhaJornada = scanner.nextInt();
		scanner.nextLine();

		if (escolhaJornada > 0 && escolhaJornada <= jornadas.size()) {
			Jornada jornadaSelecionada = jornadas.get(escolhaJornada - 1);
			ArrayList<Trajeto> trajetos = jornadaSelecionada.getTrajetos();

			// Verificando se há trajetos na jornada selecionada
			if (!trajetos.isEmpty()) {
				System.out.println("Selecione o trajeto desejado:");
				for (int i = 0; i < trajetos.size(); i++) {
					System.out.println("[" + (i + 1) + "] " + trajetos.get(i));
				}

				int escolhaTrajeto = scanner.nextInt();

				if (escolhaTrajeto > 0 && escolhaTrajeto <= trajetos.size()) {
					Trajeto trajetoSelecionado = trajetos.get(escolhaTrajeto - 1);

					// Aqui você pode modificar o inicioTrajeto do trajeto selecionado
					System.out.println("Digite a data de início do trajeto (formato: dd/MM/yyyy HH:mm:ss):");
					scanner.nextLine(); // Limpar o buffer
					String dataInicio = scanner.nextLine();

					// Parse da String para Date (supondo o formato dd/MM/yyyy HH:mm:ss)
					try {
						Date novaData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dataInicio);
						trajetoSelecionado.setInicioTrajeto(novaData);
						System.out.println("Data de início do trajeto modificada com sucesso para: " + novaData);
					} catch (ParseException e) {
						System.out.println("Formato de data inválido. Por favor, insira a data no formato correto.");
					}
				}
			}
		}
	}

	public void exibirInformacoes() {
		System.out.println("Conteúdo da lista de embarques:");
		for (Embarque embarque : listaEmbarques) {
			System.out.println("Passageiro: " + embarque.getPassageiro().getNome());
			System.out.println("Ponto de Embarque: " + embarque.getPontoDeEmbarque().getPonto());
			System.out.println("Tipo de Cartão: " + embarque.getPassageiro().getTipoCartao());
			System.out.println("Data e Hora de Embarque: " + embarque.getDataHoraEmbarque());
			System.out.println("----------------------------------");
		}
	}

	public void registraEmbarque() throws Exception {

		File arquivo = new File("embarques.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("embarques", new JSONArray());
		}

		// Exibindo os passageiros disponíveis
		ArrayList<Passageiro> passageiros = getListaPassageiros();
		for (int i = 0; i < passageiros.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + passageiros.get(i).getNome());
			System.out.println("CPF: " + passageiros.get(i).getCpf());
			System.out.println("Tipo do cartão: " + passageiros.get(i).getTipoCartao());
		}

		System.out.println("Selecione o passageiro:");
		int posicao = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer

		Passageiro passageiro = new Passageiro(listaPassageiros.get(posicao - 1).getNome(),
				listaPassageiros.get(posicao - 1).getCpf(), listaPassageiros.get(posicao - 1).getTipoCartao());

		// Exibindo os passageiros disponíveis
		ArrayList<PontoDeParada> pontos = getListaPontosDeParada();
		for (int i = 0; i < pontos.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + pontos.get(i).getPonto());
		}

		System.out.println("Selecione o ponto:");
		int ponto = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer

		PontoDeParada pontoDeEmbarque = listaPontosDeParada.get(ponto - 1);

		Embarque registro = new Embarque(passageiro, pontoDeEmbarque);
		listaEmbarques.add(registro);

		// Adicionar o novo passageiro ao array 'passageiros' no objeto JSON
		json.getJSONArray("embarques")
				.put(new JSONObject().put("passageiro", passageiro.getNome())
						.put("pontoDeEmbarque", pontoDeEmbarque.getPonto())
						.put("horario", registro.getDataHoraEmbarque().toString()));

		exibirInformacoes();

		// Escrever o objeto JSON atualizado no arquivo
		try (FileWriter fw = new FileWriter(arquivo)) {
			fw.write(json.toString());
		}
	}

	public void registraCheckpoint() throws Exception {

		File arquivo = new File("checkpoints.json");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		JSONObject json;

		// Verificar se o arquivo já contém informações
		if (arquivo.length() > 0) {
			String conteudoArquivo = new String(Files.readAllBytes(arquivo.toPath()));
			json = new JSONObject(conteudoArquivo);
		} else {
			json = new JSONObject();
			json.put("checkpoints", new JSONArray());
		}

		// Exibindo as jornadas disponíveis
		ArrayList<Jornada> jornadas = getListaJornadas();
		for (int i = 0; i < jornadas.size(); i++) {
			System.out.println("----------------------------------");
			System.out.println("[" + (i + 1) + "] " + jornadas.get(i).getTrajetos());
		}

		System.out.println("Selecione a jornada desejada (ou digite '0' para encerrar):");
		int escolhaJornada = scanner.nextInt();
		scanner.nextLine();

		if (escolhaJornada > 0 && escolhaJornada <= jornadas.size()) {
			Jornada jornadaSelecionada = jornadas.get(escolhaJornada - 1);
			ArrayList<Trajeto> trajetos = jornadaSelecionada.getTrajetos();

			// Verificando se há trajetos na jornada selecionada
			if (!trajetos.isEmpty()) {
				System.out.println("Selecione o trajeto desejado:");

				for (int i = 0; i < trajetos.size(); i++) {
					System.out.println("[" + (i + 1) + "] " + trajetos.get(i));
				}

				int escolhaTrajeto = scanner.nextInt();
				scanner.nextLine();

				if (escolhaTrajeto > 0 && escolhaTrajeto <= trajetos.size()) {
					Trajeto trajetoSelecionado = trajetos.get(escolhaTrajeto - 1);

					// Aqui você pode modificar o Checkpoint do trajeto selecionado

					System.out.println("Digite o checkpoint do trajeto (formato: dd/MM/yyyy HH:mm:ss):");
					String checkpoint = scanner.nextLine();

					// Parse da String para Date (supondo o formato dd/MM/yyyy HH:mm:ss)
					try {
						System.out.println(checkpoint);
						Date novaData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(checkpoint);
						trajetoSelecionado.setCheckpoint(novaData);
						System.out.println("Checkpoint cadastrado com sucesso para: " + novaData);

						// Adicionar o novo passageiro ao array 'passageiros' no objeto JSON
						json.getJSONArray("checkpoints").put(new JSONObject()
								.put("trajetoSelecionado", trajetoSelecionado).put("checkpoint", novaData));

						// Escrever o objeto JSON atualizado no arquivo
						try (FileWriter fw = new FileWriter(arquivo)) {
							fw.write(json.toString());
						}

					} catch (ParseException e) {
						System.out.println("Formato de data inválido. Por favor, insira a data no formato correto.");
					}
				}
			}
		}

	}

	// Método para alterar dados
	public void alterar(String nomeDoArquivo) throws Exception {

		// Criar um ArrayList para armazenar as strings
		ArrayList<String> dados = new ArrayList<>();

		// Leitura de dados
		String arquivo = nomeDoArquivo;
		FileReader fr;

		fr = new FileReader(arquivo);
		BufferedReader br = new BufferedReader(fr);

		while (br.ready()) {
			String linha = br.readLine();
			dados.add(linha);
		}

		br.close();
		fr.close();

		int i = 0;

		for (String dado : dados) {
			System.out.println("(" + (i + 1) + ") " + dado);
			i++;
		}

		// Alteracao do arquivo
		// Escrita de dados
		FileWriter fw = new FileWriter(arquivo);
		BufferedWriter bw = new BufferedWriter(fw);

		System.out.println("Escolha qual item deseja alterar: ");
		int opcao = scanner.nextInt();
		scanner.nextLine();

		dados.remove(opcao - 1);

		for (String dado : dados) {
			bw.write(dado);
			bw.newLine();
		}

		bw.close();
		fw.close();

		// Verifica se o arquivo selecionado é equivalente a alguma das situações e roda
		// o método correspondente.
		if (arquivo.equals("veiculos.txt")) {
			cadastrarVeiculo();
		}

		else if (arquivo.equals("motoristas.txt")) {
			cadastrarMotorista();
		}

		else if (arquivo.equals("cobradores.txt")) {
			cadastrarCobrador();
		}

		else if (arquivo.equals("passageiros.txt")) {
			cadastrarPassageiro();
		}

		else if (arquivo.equals("pontosDeParada.txt")) {
			cadastrarPontosDeParada();
		}

		else if (arquivo.equals("trechos.txt")) {
			cadastrarTrecho();
		}

		else if (arquivo.equals("trajetos.txt")) {
			cadastrarTrajeto();
		}

		else if (arquivo.equals("jornadas.txt")) {
			registraJornada();
		}

		else if (arquivo.equals("embarques.txt")) {
			registraEmbarque();
		}

		else if (arquivo.equals("checkpoints.txt")) {
			registraCheckpoint();
		}

		else {
			System.out.println("O arquivo que você está tentando abrir não foi encontrado no diretório.");
		}

		System.out.println("Alteração realizada com sucesso!");

	}

	// Métodos para excluir dados
	public void excluir(String nomeDoArquivo) throws Exception {

		// Criar um ArrayList para armazenar as strings
		ArrayList<String> dados = new ArrayList<>();

		// Leitura de dados
		String arquivo = nomeDoArquivo;
		FileReader fr;

		fr = new FileReader(arquivo);
		BufferedReader br = new BufferedReader(fr);

		while (br.ready()) {
			String linha = br.readLine();
			dados.add(linha);
		}

		br.close();
		fr.close();

		int i = 0;

		for (String dado : dados) {
			System.out.println("(" + (i + 1) + ") " + dado);
			i++;
		}

		// Alteracao do arquivo
		// Escrita de dados
		FileWriter fw = new FileWriter(arquivo);
		BufferedWriter bw = new BufferedWriter(fw);

		System.out.println("Escolha qual item deseja excluir: ");
		int opcao = scanner.nextInt();
		scanner.nextLine();

		dados.remove(opcao - 1);

		for (String dado : dados) {
			bw.write(dado);
			bw.newLine();
		}

		bw.close();
		fw.close();

		System.out.println("Exclusão realizada com sucesso!");

	}

	public void fecharScanner() {
		scanner.close();
	}

	// Getters para acessar a lista de veículos
	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	// Getters para acessar a lista de motoristas
	public ArrayList<Motorista> getListaMotoristas() {
		return listaMotoristas;
	}

	// Getters para acessar a lista de cobradores
	public ArrayList<Cobrador> getListaCobradores() {
		return listaCobradores;
	}

	// Getters para acessar a lista de passageiros
	public ArrayList<Passageiro> getListaPassageiros() {
		return listaPassageiros;
	}

	// Getters para acessar a lista de pontos de parada
	public ArrayList<PontoDeParada> getListaPontosDeParada() {
		return listaPontosDeParada;
	}

	// Getters para acessar a lista de trechos
	public ArrayList<Trecho> getListaTrechos() {
		return listaTrechos;
	}

	// Getters para acessar a lista de trajetos
	public ArrayList<Trajeto> getListaTrajetos() {
		return listaTrajetos;
	}

	// Getters para acessar a lista de trajetos
	public ArrayList<Jornada> getListaJornadas() {
		return listaJornadas;
	}

	// Getters para acessar a lista de trajetos
	public ArrayList<Embarque> getListaEmbarques() {
		return listaEmbarques;
	}

}