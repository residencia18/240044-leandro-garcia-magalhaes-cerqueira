package loja;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Pedido {
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	ArrayList<Produto> produtos;
	
	private String numPedido;
	private String cpfCliente;
	
	
	
	public Pedido() {
		this.produtos = new ArrayList<>();
	}


	//METODOS

	public void adicionaItem(Produto produto) {
		produtos.add(produto);	
		System.out.println("Produto INDIVIDUAL adicionado com sucesso!");
	}
	
	
	public void calculaTotal(Pedido pedido) {
		double total = 0;
		for (Produto item : produtos) {		
			total += item.getValor();		
		}	
		System.out.println("Valor total do pedido: R$" + df.format(total));
	}
	
	public void exibeInformacoes(Pedido pedido) {
		for(Produto produto: produtos) {
			System.out.println("Produto: " + produto.getNome());
			System.out.println("Quantidade: " + produto.getQuantidade());
			System.out.println("Preço: " + df.format(produto.getValor()));
			System.out.println("----------------------------//");
		}
	}
	
	//SOBRECARGAS	
	public void adicionaItem(ArrayList<Produto> produtos){
		produtos.addAll(produtos);
		System.out.println("LISTA de produtos adicionada com sucesso!");
	}
	
	
	public void calculaTotal(Pedido pedido, double desconto) {
		double total = 0;
		for (Produto item : produtos) {
			total += item.getValor();
		}
		total = total - (total / 100) * desconto;
		System.out.println("Valor total do pedido (com desconto): R$" + df.format(total) );
	}
	
	public void calculaTotal(Pedido pedido, int prestacao, double juros) {
		double total = 0;
		for (Produto item : produtos) {
			total += item.getValor();
		}
		total = total + (prestacao * juros);
		System.out.println("Valor total do pedido (à prazo): R$" + df.format(total) );
	}
	
		
	
}
