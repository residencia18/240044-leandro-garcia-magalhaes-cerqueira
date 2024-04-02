package dadosestatisticos;

import java.util.ArrayList;
import java.util.Date;

public class DatasDeNascimento implements DadosEstatisticos {
	
	private Date dataDeNascimento;
	private ArrayList<Date> listaDatas = new ArrayList();
	
	

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public ArrayList<Date> getListaDatas() {
		return listaDatas;
	}


	public Object max() {
		
		Date maior = listaDatas.get(0); 
		
		for (Date data : listaDatas){
			if (data.after(maior)) {
				maior = data;
			}
		}
		return maior;
		
	}
		

	public Object min() {
		return null;
	};
	public void ordernar() {

	};
	
	public Object buscar() {
		return null;
	};


}
