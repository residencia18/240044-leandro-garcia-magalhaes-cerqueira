package dadosestatisticos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class DatasDeNascimento implements DadosEstatisticos {

	private Date dataDeNascimento;
	ArrayList<Date> listaDatas = new ArrayList<>();

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public Object max() {

		Date maior = listaDatas.get(0);

		for (Date data : listaDatas) {
			if (data.after(maior)) {
				maior = data;
			}
		}
		return maior;

	}

	public Object min() {

		Date menor = listaDatas.get(0);

		for (Date data : listaDatas) {
			if (data.before(menor)) {
				menor = data;
			}
		}
		return menor;
	};

	public void ordernar() {

		System.out.println("Datas antes da ordenação: ");
		System.out.println("");

		for (Date data : listaDatas) {
			System.out.println(data);
		}
		Collections.sort(listaDatas);
		System.out.println("");
		System.out.println("Datas depois da ordenação: ");
		System.out.println("");

		for (Date data : listaDatas) {
			System.out.println(data);
		}
		System.out.println("");
	};

	public Object buscar(Object dataProcurada) {
		for (Date data : listaDatas) {
			if (data.equals(dataProcurada)) {
				System.out.println("Data encontrada! A data procurada: " + dataProcurada + " existe na lista! ");
				return data;
			}
		}
		System.out.println("Data não encontrada.");
		return null;
	};

}
