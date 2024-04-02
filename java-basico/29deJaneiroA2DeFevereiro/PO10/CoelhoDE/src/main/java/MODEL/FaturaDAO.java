package MODEL;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class FaturaDAO {
	
	Scanner scanner = new Scanner(System.in);
	ImovelDAO imovelDAO = new ImovelDAO();
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_coelho");
	EntityManager em = emf.createEntityManager();

	public void gerarFatura() {
			
		Imovel imovel = imovelDAO.buscaImovelPorMatricula(em);
		
		System.out.println("Última leitura: " + imovel.getUltimaLeitura());
		
		System.out.println("Informe a leitura atual: ");
		double leituraAtual = scanner.nextDouble();
		
		long horarioAtual = System.currentTimeMillis();
        Timestamp hora = new Timestamp(horarioAtual);
		
        em.getTransaction().begin();
        imovel.setPenultimaLeitura(imovel.getUltimaLeitura());
		imovel.setUltimaLeitura(leituraAtual);
		em.persist(imovel);
		    	
		double valorFatura = (imovel.getUltimaLeitura() - imovel.getPenultimaLeitura()) * 10;
		
		Fatura fatura = new Fatura(hora,leituraAtual,imovel.getPenultimaLeitura(),valorFatura,false,imovel);
		
		em.persist(fatura);
		em.getTransaction().commit();
	}

	public void listarFaturasEmAberto(EntityManager em) {

		String jpql = "select f from Fatura f where quitado=0";
		
		TypedQuery<Fatura> typedQuery = em.createQuery(jpql, Fatura.class);
		
		List<Fatura> faturas = typedQuery.getResultList();
		
		System.out.println("Faturas em aberto: ");
		
		for(Fatura f : faturas) {
			System.out.println(f);
		}	
	}
}
