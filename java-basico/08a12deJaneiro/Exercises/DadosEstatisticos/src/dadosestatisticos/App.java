package dadosestatisticos;
import java.util.Date;

public class App {

	public static void main(String[] args) {
			
		// Objeto Data
		DatasDeNascimento datas = new DatasDeNascimento();		
		
		//OBS: O formato Date conta os meses de 0 até 11, sendo 0 Janeiro e 11 Dezembro (maluquice)
		Date data1 = new Date(2002,0,15);
		Date data2 = new Date(2003,11,06);
		Date data3 = new Date(2023,05,20);
		
		datas.listaDatas.add(data3);
		datas.listaDatas.add(data1);
		datas.listaDatas.add(data2);
			
		System.out.println("Maior data: ");
		System.out.println(datas.max());
		System.out.println(" ");
		
		System.out.println("Menor data: ");	
		System.out.println(datas.min());
		System.out.println(" ");
		
		datas.ordernar();
		
		datas.buscar(data3);
		
		
		
		
		
		
		

	}

}
