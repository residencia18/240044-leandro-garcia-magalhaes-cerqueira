package com.leosoft.racewinners.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class F1Controller {

	private List<Vencedor> vencedores = new ArrayList<>();

	// Construtor que iniciar o método de carregar o arquivo .csv
	public F1Controller() {
		carregarDadosCSV();
	}

	private void carregarDadosCSV() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("pilotos.csv");
			if (inputStream != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				String linha;
				while ((linha = reader.readLine()) != null) {
					String[] dados = linha.split(";");
					if (dados.length >= 3) {
						vencedores.add(new Vencedor(dados[0], dados[1], Integer.parseInt(dados[2])));
					} else {
						System.err.println("Formato inválido da linha: " + linha);
					}
				}
			} else {
				System.err.println("Arquivo pilotos.csv não encontrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Lista todos os vencedores
	@GetMapping("/todos")
	public List<Vencedor> listarTodosVencedores() {
		return vencedores;
	}

	// Lista todos os vencedores brasileiros
	@GetMapping("/brasileiros")
	public List<Vencedor> listarVencedoresBrasileiros() {
		List<Vencedor> brasileiros = new ArrayList<>();
		for (Vencedor v : vencedores) {
			if (v.getPais().equalsIgnoreCase("Brasil")) {
				brasileiros.add(v);
			}
		}
		return brasileiros;

	}

	// Lista dos top 5 vencedores
	@GetMapping("/top5")
	public ArrayList<Vencedor> listarTop5() {

		List<Vencedor> lista = vencedores;

		// Crie um Comparator para comparar objetos com base no atributoInteiro
		Comparator<Vencedor> comparador = Comparator.comparingInt(Vencedor::getVitorias);

		// Ordene a lista usando o Comparator
		Collections.sort(lista, comparador.reversed());

		ArrayList<Vencedor> top5 = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			top5.add(lista.get(i));
		}

		return top5;
	}

	// Lista dos top 10 vencedores
	@GetMapping("/top10")
	public ArrayList<Vencedor> listarTop10() {

		List<Vencedor> lista = vencedores;

		// Crie um Comparator para comparar objetos com base no atributoInteiro
		Comparator<Vencedor> comparador = Comparator.comparingInt(Vencedor::getVitorias);

		// Ordene a lista usando o Comparator
		Collections.sort(lista, comparador.reversed());

		ArrayList<Vencedor> top10 = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			top10.add(lista.get(i));
		}

		return top10;
	}

	@GetMapping("/porpais")
	public Map<String, Integer> listarPorPais() {
		// Cria um mapa para armazenar o número de vitórias por país
		Map<String, Integer> vitoriasPorPais = new HashMap<>();

		// Itera sobre a lista de vencedores
		for (Vencedor vencedor : vencedores) {
			// Obtém o país e o número de vitórias do vencedor atual
			String pais = vencedor.getPais();
			int vitorias = vencedor.getVitorias();

			// Adiciona o número de vitórias do vencedor ao total acumulado para o país
			// correspondente no mapa
			vitoriasPorPais.put(pais, vitoriasPorPais.getOrDefault(pais, 0) + vitorias);
		}

		// Cria uma lista de entradas (par chave-valor) a partir do mapa vitoriasPorPais
		List<Map.Entry<String, Integer>> listaOrdenada = new ArrayList<>(vitoriasPorPais.entrySet());

		// Ordena a lista com base no valor (número de vitórias) em ordem decrescente
		listaOrdenada.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

		// Cria um novo mapa para armazenar o resultado final ordenado
		Map<String, Integer> resultadoFinal = new LinkedHashMap<>();

		// Copia os elementos ordenados da lista de entradas para o mapa resultadoFinal
		for (Map.Entry<String, Integer> entrada : listaOrdenada) {
			resultadoFinal.put(entrada.getKey(), entrada.getValue());
		}

		// Retorna o mapa resultadoFinal, que contém o número de vitórias por país,
		// ordenado em ordem decrescente
		return resultadoFinal;
	}

	@GetMapping("/mediaporpais")
    public Map<String, Double> listarMediaPorPais() {
        Map<String, Double> mediaPorPais = new HashMap<>();

        // Cria um mapa para armazenar o número de vencedores por país
        Map<String, Integer> vencedoresPorPais = new HashMap<>();
        for (Vencedor vencedor : vencedores) {
            String pais = vencedor.getPais();
            vencedoresPorPais.put(pais, vencedoresPorPais.getOrDefault(pais, 0) + 1);
        }

        // Calcula a média de vitórias por país
        for (Map.Entry<String, Integer> entry : vencedoresPorPais.entrySet()) {
            String pais = entry.getKey();
            int totalVitorias = 0;
            for (Vencedor vencedor : vencedores) {
                if (vencedor.getPais().equals(pais)) {
                    totalVitorias += vencedor.getVitorias();
                }
            }
            
            int totalVencedores = entry.getValue();
            double media = (double) totalVitorias / totalVencedores;
            
            // Formata a média para uma casa decimal
            
            // Formata a média para uma casa decimal
            DecimalFormat df = new DecimalFormat("#.#");
            df.setMaximumFractionDigits(1);
            double mediaFormatada = Double.parseDouble(df.format(media));
            mediaPorPais.put(pais, mediaFormatada);
        }

        // Ordena o mapa por valor (média de vitórias), em ordem decrescente
        mediaPorPais = mediaPorPais.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return mediaPorPais;
    }
	
	
}
