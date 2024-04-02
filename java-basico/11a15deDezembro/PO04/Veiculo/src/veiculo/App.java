package veiculo;

public class App {
	
	public static void main(String[] args) {
		
		//Instancia da classe garagem
		Garagem garagem1 = new Garagem(false);
		Garagem garagem2 = new Garagem(true);
				
			
		//Instancias das classes derivadas
		Carro carro = new Carro("Sedan", "Preto", 2005);
		Moto moto = new Moto("300", "Prata", 2021);
		
		
		
		//Chamada dos métodos sobrecarregados
		System.out.println("Chamando os métodos sobrecarregados das classes derivadas: \n");
		carro.ligar();
		carro.acelerar();
		carro.parar();
		System.out.println("");
		moto.ligar();
		moto.acelerar();
		moto.parar();
		System.out.println("");
		
		//NovasInstancias das classes derivadas
		Carro carro2 = new Carro("4x4", "Branco", 2015, false);
		Carro carro3 = new Carro("Ret", "Vermelho", 2022, true);
		Moto moto2 = new Moto("600", "Preta", 2019, false);
		
		System.out.println("Métodos de estacionamento: \n");
		
		//Apenas adicionando um carro em uma garagem
		garagem1.estacionar(carro2);
		System.out.println("");
		
		carro3.estacionar(garagem1);
		System.out.println("");
		carro2.estacionar(garagem2);
		System.out.println("");
		carro3.estacionar(garagem2);
		System.out.println("");
		moto2.estacionar(garagem2);
		
		
		
			
		
		
		
		
		
		
		
		
		
		
	}

}
