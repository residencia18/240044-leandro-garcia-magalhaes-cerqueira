package MODEL;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ImovelDAO {

	Scanner scanner = new Scanner(System.in);
	
	// CRUD de Imóvel

	public void criaImovel(EntityManager em) {
		
		System.out.println("Insira a matrícula do imóvel: ");
		String matricula = scanner.nextLine();

		System.out.println("Insira o endereço do imóvel: ");
		String endereco = scanner.nextLine();

		Double ultimaLeitura = 0.0;
		Double penultimaLeitura = 0.0;

		Imovel imovel = new Imovel(matricula, endereco, ultimaLeitura, penultimaLeitura);
		
		em.getTransaction().begin();
		em.persist(imovel);
		em.getTransaction().commit();

	}

	public void listaImoveis(EntityManager em) {

		String jpql = "select i from Imovel i";
		
		TypedQuery<Imovel> typedQuery = em.createQuery(jpql, Imovel.class);
		
		List<Imovel> imoveis = typedQuery.getResultList();
		
		System.out.println("Lista de imóveis: ");

		for (Imovel i : imoveis) {
			System.out.println(i);
		}
	}

	public Imovel buscaImovelPorMatricula(EntityManager em) {
		
		System.out.println("Insira a matricula do imóvel que está buscando: ");
		String matricula = scanner.nextLine();

		String jpql = "select i from Imovel i where Matricula = :matricula";
		
		TypedQuery<Imovel> typedQuery = em.createQuery(jpql, Imovel.class);
		typedQuery.setParameter("matricula", matricula);

		Imovel imovel = typedQuery.getSingleResult();
		
		System.out.println(imovel);
		
			return imovel;	
	}

	public void alteraImovel(EntityManager em) {

		System.out.println("Insira a matricula do imóvel que você deseja alterar: ");
		String matricula = scanner.nextLine();

		System.out.println("Agora insira o novo endereço: ");
		String endereco = scanner.nextLine();

		String jpql = "select i from Imovel i WHERE Matricula= :matricula";

		TypedQuery<Imovel> typedQuery = em.createQuery(jpql, Imovel.class);
		typedQuery.setParameter("matricula", matricula);
		
		Imovel i = typedQuery.getSingleResult();
		
		em.getTransaction().begin();
		
		//Atualiza o endereço do imóvel
		i.setEndereco(endereco);
		em.persist(i);
		em.getTransaction().commit();
			
	}

	public void excluiImovel(EntityManager em) {

		System.out.println("Insira a matricula do imóvel que você deseja excluir: ");
		String matricula = scanner.nextLine();

		String jpql = "delete from MODEL.Imovel where Matricula= :matricula";
		
		em.getTransaction().begin();
		
		Query query = em.createQuery(jpql);
		
		query.setParameter("matricula", matricula);
		
		int rowsDeleted = query.executeUpdate(); // Executa a consulta de exclusão
	    em.getTransaction().commit();
	    
	    System.out.println(rowsDeleted + " registro(s) foram excluídos.");
		
	}

}
