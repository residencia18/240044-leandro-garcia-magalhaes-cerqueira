package MODEL;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ClienteDAO {
	
	Scanner scanner = new Scanner(System.in);
	
	//CRUD de Cliente
	
	public void criaCliente(EntityManager em) {
		
		System.out.println("Insira o nome do cliente: ");
		String nome = scanner.nextLine();
		
		System.out.println("Insira o CPF do cliente: ");
		String cpf = scanner.nextLine();
		
		Cliente cliente = new Cliente(cpf,nome);
			
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
			
	}

	public void listaClientes(EntityManager em) {
		
		String jpql = "select c from Cliente c";
		
		TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
		
		List<Cliente> clientes = typedQuery.getResultList();
		
		System.out.println("Lista de clientes: ");
		
		for(Cliente c : clientes) {
			System.out.println(c);
		}
	}

	public void listaClientePorCPF(EntityManager em) {
		
		System.out.println("Insira o CPF do cliente que está buscando: ");
		String cpf = scanner.nextLine();
			
		String jpql = "select c from Cliente c where CPF = :cpf";
	
		TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
		typedQuery.setParameter("cpf", cpf);
			
		Cliente cliente = typedQuery.getSingleResult();
					
			System.out.println(cliente);												
		}

	public void alteraCliente(EntityManager em) {
		
		System.out.println("Insira o CPF do cliente que você deseja alterar: ");
		String cpf = scanner.nextLine();
		
		System.out.println("Agora insira o novo nome: ");
		String nome = scanner.nextLine();
		
		String jpql = "select c from Cliente c where CPF = :cpf";
		
		TypedQuery<Cliente> typedQuery = em.createQuery(jpql, Cliente.class);
		typedQuery.setParameter("cpf", cpf);
		
		Cliente c = typedQuery.getSingleResult(); 
	
		em.getTransaction().begin();

		//Atualiza o nome do cliente, por exemplo.
		c.setNome(nome);
		em.persist(c);
		em.getTransaction().commit();
							
	}
	
	public void excluiCliente(EntityManager em) {
	    System.out.println("Insira o CPF do cliente que você deseja excluir: ");
	    String cpf = scanner.nextLine();
	            
	    String jpql = "delete from MODEL.Cliente where CPF = :cpf";
	    
	    em.getTransaction().begin();
	    
	    Query query = em.createQuery(jpql); // Alterado para Query
	    
	    query.setParameter("cpf", cpf);
	        
	    int rowsDeleted = query.executeUpdate(); // Executa a consulta de exclusão
	    em.getTransaction().commit();
	    
	    System.out.println(rowsDeleted + " registros foram excluídos.");
	}

	
}
