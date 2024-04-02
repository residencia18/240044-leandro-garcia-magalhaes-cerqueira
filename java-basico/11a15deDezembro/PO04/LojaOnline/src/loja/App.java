package loja;


public class App {
	
	public static void main(String[] args) {
		
		Pedido pedido = new Pedido();
		
		Produto produto1 = new Produto("Gola rolê", 3, 49.99);
		Produto produto2 = new Produto("Toalha de banho", 2, 29.99);
		Produto produto3 = new Produto("Travesseiro", 3, 19.99);
						
		// TESTE DE MÉTODOS
		System.out.println("ADICIONANDO ITENS:");
		pedido.adicionaItem(produto1);
		pedido.adicionaItem(produto2);
		pedido.adicionaItem(produto3);
		System.out.println(" ");
		pedido.adicionaItem(pedido.produtos);
		System.out.println("--------------------------");
		
		System.out.println("CALCULANDO VALOR TOTAL DE ITENS:");
		pedido.calculaTotal(pedido);
		System.out.println(" ");
		pedido.calculaTotal(pedido, 10);
		System.out.println(" ");
		pedido.calculaTotal(pedido, 2, 10);
		System.out.println("--------------------------");
		
		System.out.println("EXIBINDO INFORMAÇÕES DOS ITENS:");
		pedido.exibeInformacoes(pedido);
		
		
		
		
		
		
		
		
		
	
		
	}

}
