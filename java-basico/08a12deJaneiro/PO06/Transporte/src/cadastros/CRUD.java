package cadastros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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

	// Métodos de Cadastros

	public void cadastrarVeiculo() throws Exception {

		File arquivo = new File("veiculos.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

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

			bw.write("Modelo: " + modelo + " | " + "Placa: " + placa + ";");
			bw.newLine();

		}

		bw.close();
		fw.close();
	}

	public void cadastrarMotorista() throws Exception {

		File arquivo = new File("motoristas.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

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

			bw.write("Motorista: " + nome + " | " + "CPF: " + cpf + ";");
			bw.newLine();
		}

		bw.close();
		fw.close();
	}

	public void cadastrarCobrador() throws Exception {

		File arquivo = new File("cobradores.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

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

			bw.write("Cobrador: " + nome + " | " + "CPF: " + cpf + ";");
			bw.newLine();

			System.out.println("Cobrador cadastrado com sucesso!");
			System.out.println(" ");
		}

		bw.close();
		fw.close();
	}

	public void cadastrarPassageiro() throws Exception {

		File arquivo = new File("passageiros.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

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

			bw.write("Passageiro: " + nome + " | " + "CPF: " + cpf + " | " + "Tipo do cartão: " + tipoCartao);
			bw.newLine();

			System.out.println("Passageiro cadastrado com sucesso!");
			System.out.println(" ");
		}

		bw.close();
		fw.close();
	}

	public void cadastrarPontosDeParada() throws Exception {

		File arquivo = new File("pontosDeParada.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

		while (true) {
			System.out.println("Digite o nome do ponto: (ou digite 'x' para encerrar):");
			String nomePonto = scanner.nextLine();

			if (nomePonto.equalsIgnoreCase("x")) {
				break;
			}

			PontoDeParada pontoDeParada = new PontoDeParada(nomePonto);
			listaPontosDeParada.add(pontoDeParada);

			bw.write("Ponto de parada: " + nomePonto + ";");
			bw.newLine();

			System.out.println("Ponto de parada cadastrado com sucesso!");
			System.out.println(" ");
		}

		bw.close();
		fw.close();
	}

	public void cadastrarTrecho() throws Exception {

		File arquivo = new File("trechos.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

		while (true) {
			System.out.println("--------- Cadastro de trecho -----------");
			System.out.println("----- Pontos de Parada disponíveis -----");

			// Exibindo os pontos de parada disponíveis
			ArrayList<PontoDeParada> pontosDeParada = getListaPontosDeParada();
			for (int i = 0; i < pontosDeParada.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + pontosDeParada.get(i).getPonto());
			}

			System.out.println("Digite a opção do ponto de partida:");
			int pontoA = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			System.out.println("Digite a opção do ponto de chegada:");
			int pontoB = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			// Verificar se as opções são válidas
			if (pontoA < 1 || pontoA > pontosDeParada.size() || pontoB < 1 || pontoB > pontosDeParada.size()) {
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

			bw.write("Trecho: " + pontos + " | " + "Duração estimada: " + intervalo + "min");
			bw.newLine();

			System.out.println("Trecho cadastrado com sucesso!");
			System.out.println(" ");

			System.out.println("Digite 'x' para sair ou ENTER para cadastrar outro trecho: ");
			String sair = scanner.nextLine();

			if (sair.equalsIgnoreCase("x")) {
				break; // Sai do loop se o usuário digitar 'x'
			}
		}

		bw.close();
		fw.close();
	}

	public void cadastrarTrajeto() throws Exception {

		File arquivo = new File("trajetos.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

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

		bw.write("Trajeto: " + listaTrechosTrajeto + ";");
		bw.newLine();

		System.out.println("Trajeto cadastrado com sucesso!");
		System.out.println(" ");

		bw.close();
		fw.close();

	}

	public void registraJornada() throws Exception {

		File arquivo = new File("jornadas.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

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

				bw.write("Trajetos: " + trajetos + " | " + "Motorista: " + motoristaJornada.getNome() + " | " + "Cobrador: "
						+ cobradorJornada.getNome() + " | " + "Veículo: " + veiculoJornada.getModelo() + ";");
				bw.newLine();

			} else {
				Jornada jornada = new Jornada(trajetos, motoristaJornada, veiculoJornada);
				listaJornadas.add(jornada);

				bw.write("Trajetos: " + trajetos + " | " + "Motorista: " + motoristaJornada.getNome() + " | " + "Veículo: "
						+ veiculoJornada.getModelo() + ";");
				bw.newLine();
			}
		}

		System.out.println("Jornada cadastrada com sucesso!");
		System.out.println(" ");

		bw.close();
		fw.close();
	}

	public void registraInicioDeTrajeto(){
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

		File arquivo = new File("embarques.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

		// Exibindo os passageiros disponíveis
		ArrayList<Passageiro> passageiros = getListaPassageiros();
		for (int i = 0; i < passageiros.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + passageiros.get(i).getNome());
			System.out.println(passageiros.get(i).getCpf());
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

		bw.write("Passageiro: " + passageiro.getNome() + " | " + "CPF: " + passageiro.getCpf() + " | "
				+ "Tipo de cartão: " + passageiro.getTipoCartao() + " | " + "Ponto de embarque: "
				+ pontoDeEmbarque.getPonto() + " | " + "Horário de embarque: " + registro.getDataHoraEmbarque());
		bw.newLine();

		exibirInformacoes();

		bw.close();
		fw.close();
	}

	public void registraCheckpoint() throws Exception {

		File arquivo = new File("checkpoints.txt");

		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}

		FileWriter fw = new FileWriter(arquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

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

						bw.write("Jornada: " + jornadaSelecionada + " | " + "Trajeto: " + trajetoSelecionado + " | "
								+ "Checkpoint: " + novaData + ";");
						bw.newLine();

					} catch (ParseException e) {
						System.out.println("Formato de data inválido. Por favor, insira a data no formato correto.");
					}
				}
			}
		}

		bw.close();
		fw.close();
	}

	//Método para alterar dados
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

	//Métodos para excluir dados
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