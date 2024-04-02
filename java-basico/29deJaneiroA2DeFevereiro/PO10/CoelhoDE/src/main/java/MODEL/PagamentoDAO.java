package MODEL;


import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PagamentoDAO {

	Scanner scanner = new Scanner(System.in);
	FaturaDAO fatura = new FaturaDAO();

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_coelho");
	EntityManager em = emf.createEntityManager();

	// CRUD de Pagamento

	public void efetuarPagamento() {

		fatura.listarFaturasEmAberto(em);

		System.out.println("Informe o ID da fatura que deseja efetuar o pagamento: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		String jpql = "select f from Fatura f where Id = :id";

		TypedQuery<Fatura> typedQuery = em.createQuery(jpql, Fatura.class);
		typedQuery.setParameter("id", id);

		Fatura fatura = typedQuery.getSingleResult();

		System.out.println("Informe o valor que deseja pagar: ");
		double valor = scanner.nextDouble();
		scanner.nextLine();

		long horarioAtual = System.currentTimeMillis();
		Timestamp data = new Timestamp(horarioAtual);

		Pagamento pagamento = new Pagamento(data, valor, fatura);

		em.getTransaction().begin();
		em.persist(pagamento);
		
		//Fazer a soma de pagamentos
		String jpql2 = "select p from Pagamento p where IdFatura = :id";
		
		TypedQuery<Pagamento> typedQuery2 = em.createQuery(jpql2, Pagamento.class);
		typedQuery2.setParameter("id", id);
		
		List<Pagamento> pagamentos = typedQuery2.getResultList();
		
		double somaDePagamentos = 0.0;
		
		for(Pagamento p : pagamentos) {
			somaDePagamentos += p.getValor();
		}

		if (valor >= fatura.getValor()) {

			fatura.setQuitado(true);
			em.persist(fatura);

			double valorReembolso = valor - fatura.getValor();
			Reembolso reembolso = new Reembolso(data,valorReembolso);

			em.persist(reembolso);
			em.getTransaction().commit();
			
		} else if (somaDePagamentos >= fatura.getValor()) {
			
			fatura.setQuitado(true);
			em.persist(fatura);
			
			double valorReembolso = somaDePagamentos - fatura.getValor();
			Reembolso reembolso = new Reembolso(data,valorReembolso);
			em.persist(reembolso);
			em.getTransaction().commit();
			
		} else {
			em.persist(pagamento);
			em.getTransaction().commit();
		}
	}
	
	public void buscaPagamentoPorFatura() {

		fatura.listarFaturasEmAberto(em);

		System.out.println("Informe o ID da fatura que deseja verificar os pagamentos feitos: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		String jpql = "select p from Pagamento p where IdFatura = :id";
		
		TypedQuery<Pagamento> typedQuery = em.createQuery(jpql, Pagamento.class);
		typedQuery.setParameter("id", id);
		
		List<Pagamento> pagamentos = typedQuery.getResultList();
		
		for(Pagamento p : pagamentos) {
			System.out.println(p);
		}	
	}
}
