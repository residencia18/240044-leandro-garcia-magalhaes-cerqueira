package cadastros;

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

public class Cadastro {

	private Scanner scanner = new Scanner(System.in);

	private ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
	private ArrayList<Motorista> listaMotoristas = new ArrayList<>();
	private ArrayList<Cobrador> listaCobradores = new ArrayList<>();
	private ArrayList<Passageiro> listaPassageiros = new ArrayList<>();
	private ArrayList<PontoDeParada> listaPontosDeParada = new ArrayList<>();
	private ArrayList<Trecho> listaTrechos = new ArrayList<>();
	private ArrayList<Trajeto> listaTrajetos = new ArrayList<>();
	private ArrayList<Trajeto> listaTrajetosJornada = new ArrayList<>();
	private ArrayList<Jornada> listaJornadas = new ArrayList<>();
	private ArrayList<Embarque> listaEmbarques = new ArrayList<>();

	public void cadastrarVeiculo() {
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
		}
	}

	public void cadastrarMotorista() {
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
		}
	}

	public void cadastrarCobrador() {
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
		}
	}

	public void cadastrarPassageiro() {
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
			int tipoCartao = scanner.nextInt();
			scanner.nextLine(); //Limpando o buffer
			
			Passageiro passageiro = new Passageiro(nome, cpf, tipoCartao);
			listaPassageiros.add(passageiro);

			System.out.println("Passageiro cadastrado com sucesso!");
		}
	}

	public void cadastrarPontosDeParada() {
		while (true) {
			System.out.println("Digite o nome do ponto: (ou digite 'x' para encerrar):");
			String nomePonto = scanner.nextLine();

			if (nomePonto.equalsIgnoreCase("x")) {
				break;
			}

			PontoDeParada pontoDeParada = new PontoDeParada(nomePonto);
			listaPontosDeParada.add(pontoDeParada);

			System.out.println("Ponto de parada cadastrado com sucesso!");
		}
	}

	public void cadastrarTrecho() {
	    while (true) {
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

	        System.out.println("Trecho cadastrado com sucesso!");
	        System.out.println(" ");

	        System.out.println("Digite 'x' para sair ou qualquer outra tecla para cadastrar outro trecho: ");
	        String sair = scanner.nextLine();

	        if (sair.equalsIgnoreCase("x")) {
	            break; // Sai do loop se o usuário digitar 'x'
	        }
	    }
	}

	

	public void cadastrarTrajeto() {	
		while (true) {
			cadastrarTrecho();
			
			System.out.println("Digite qualquer outra tecla para continuar ou 'x' para sair: ");

			String sair = scanner.nextLine();

			if (sair.equalsIgnoreCase("x")) {
				break;
			}

			System.out.println("----- Trechos disponíveis -----");

			// Exibindo os trechos disponíveis
			ArrayList<Trecho> trechos = getListaTrechos();
			for (int i = 0; i < trechos.size(); i++) {
				System.out.println(
						"[" + (i + 1) + "] " + trechos.get(i).getTrecho() + ", " + trechos.get(i).getIntervalo() + " minutos");
			}

			Trajeto trajeto = new Trajeto(trechos);
			listaTrajetos.add(trajeto);

			System.out.println("Trajeto cadastrado com sucesso!");
			System.out.println(" ");
		

		}

	}

	public void registraJornada() {

		// Exibindo os trajetos disponíveis
		ArrayList<Trajeto> trajetos = getListaTrajetos();
		for (int i = 0; i < trajetos.size(); i++) {
			System.out.println("----------------------------------");
			System.out.println("[" + (i + 1) + "] " + trajetos.get(i).getTrechos());
		}

		while (true) {

			System.out.println("Selecione o trajeto desejado (ou digite '0' para encerrar):");
			int trajeto = scanner.nextInt();
			scanner.nextLine();

			if (trajeto == 0) {
				break;
			}

			Trajeto trajetoJornada = trajetos.get(trajeto - 1);
			listaTrajetosJornada.add(trajetoJornada);

			// Exibindo os motoristas disponíveis
			ArrayList<Motorista> motoristas = getListaMotoristas();
			for (int i = 0; i < motoristas.size(); i++) {
				System.out.println("----------------------------------");
				System.out
						.println("[" + (i + 1) + "] " + motoristas.get(i).getNome() + "," + motoristas.get(i).getCpf());
			}

			System.out.println("Selecione o motorista desejado (ou digite '0' para encerrar):");
			int motorista = scanner.nextInt();
			scanner.nextLine();

			if (motorista == 0) {
				break;
			}

			Motorista motoristaJornada = motoristas.get(motorista - 1);

			// Exibindo os motoristas disponíveis
			ArrayList<Veiculo> veiculos = getListaVeiculos();
			for (int i = 0; i < veiculos.size(); i++) {
				System.out.println("----------------------------------");
				System.out.println(
						"[" + (i + 1) + "] " + veiculos.get(i).getModelo() + " - Placa: " + veiculos.get(i).getPlaca());
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
					System.out.println(
							"[" + (i + 1) + "] " + cobradores.get(i).getNome() + "," + cobradores.get(i).getCpf());
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

			} else {
				Jornada jornada = new Jornada(trajetos, motoristaJornada, veiculoJornada);
				listaJornadas.add(jornada);
			}

		}

	}

	public void registraInicioDeTrajeto() {
		// Exibindo as jornadas disponíveis
		ArrayList<Jornada> jornadas = getListaJornadas();
		for (int i = 0; i < jornadas.size(); i++) {
			System.out.println("----------------------------------");
			System.out.println("[" + (i + 1) + "] " + jornadas.get(i).getTrajetos());
		}

		while (true) {

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
						System.out.println("[" + (i + 1) + "] " + trajetos.get(i).getInicioTrajeto());
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
							System.out
									.println("Formato de data inválido. Por favor, insira a data no formato correto.");
						}
					} else {
						System.out.println("Opção inválida. Por favor, selecione um trajeto válido.");
						break;
					}
				} else {
					System.out.println("Esta jornada não possui trajetos.");
					break;
				}
			} else {
				System.out.println("Opção inválida. Por favor, selecione uma jornada válida.");
				break;
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

	public void registraEmbarque() {
		 
		// Exibindo os passageiros disponíveis
		ArrayList<Passageiro> passageiros = getListaPassageiros();
			for (int i = 0; i < passageiros.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + passageiros.get(i).getNome());
				System.out.println(passageiros.get(i).getCpf());
				System.out.println("Tipo do cartão: " +passageiros.get(i).getTipoCartao());
				}
	        
			
	        System.out.println("Selecione o passageiro:");
			int posicao = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer
			
			Passageiro passageiro = new Passageiro (listaPassageiros.get(posicao -1).getNome());
			Passageiro tipoCartao = new Passageiro (listaPassageiros.get(posicao -1).getTipoCartao());
			
			// Exibindo os passageiros disponíveis
			ArrayList<PontoDeParada> pontos = getListaPontosDeParada();
				for (int i = 0; i < pontos.size(); i++) {
					System.out.println("[" + (i + 1) + "] " + pontos.get(i).getPonto());
					}
		        	        
		    System.out.println("Selecione o ponto:");
		    int ponto = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer
				
			PontoDeParada pontoDeEmbarque = listaPontosDeParada.get(ponto -1);
	 	        
	        Embarque registro = new Embarque(passageiro, pontoDeEmbarque, tipoCartao);
	        listaEmbarques.add(registro);
	        
	      
	    
	        exibirInformacoes();
		}
	

	public void registraCheckpoint() {
		// Exibindo as jornadas disponíveis
				ArrayList<Jornada> jornadas = getListaJornadas();
				for (int i = 0; i < jornadas.size(); i++) {
					System.out.println("----------------------------------");
					System.out.println("[" + (i + 1) + "] " + jornadas.get(i).getTrajetos());
				}

				while (true) {

					System.out.println("Selecione a jornada desejada (ou digite '0' para encerrar):");
					int escolhaJornada = scanner.nextInt();

					if (escolhaJornada > 0 && escolhaJornada <= jornadas.size()) {
						Jornada jornadaSelecionada = jornadas.get(escolhaJornada - 1);
						ArrayList<Trajeto> trajetos = jornadaSelecionada.getTrajetos();

						// Verificando se há trajetos na jornada selecionada
						if (!trajetos.isEmpty()) {
							System.out.println("Selecione o trajeto desejado:");
							
							for (int i = 0; i < trajetos.size(); i++) {
								System.out.println("[" + (i + 1) + "] " + trajetos.get(i).getCheckpoint());
							}

							int escolhaTrajeto = scanner.nextInt();

							if (escolhaTrajeto > 0 && escolhaTrajeto <= trajetos.size()) {
								Trajeto trajetoSelecionado = trajetos.get(escolhaTrajeto - 1);

								// Aqui você pode modificar o Checkpoint do trajeto selecionado
								System.out.println("Digite o checkpoint do trajeto (formato: dd/MM/yyyy HH:mm:ss):");
								scanner.nextLine(); // Limpar o buffer
								String checkpoint = scanner.nextLine();

								// Parse da String para Date (supondo o formato dd/MM/yyyy HH:mm:ss)
								try {
									Date novaData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(checkpoint);
									trajetoSelecionado.setCheckpoint(novaData);
									System.out.println("Checkpoint cadastrado com sucesso para: " + novaData);
								} catch (ParseException e) {
									System.out.println("Formato de data inválido. Por favor, insira a data no formato correto.");
								}
							} else {
								System.out.println("Opção inválida. Por favor, selecione um trajeto válido.");
								break;
							}
						} else {
							System.out.println("Esta jornada não possui trajetos.");
							break;
						}
					} else {
						System.out.println("Opção inválida. Por favor, selecione uma jornada válida.");
						break;
					}
				}

			}
	
	
	// Métodos para outras funcionalidades de cadastro podem ser adicionados aqui

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