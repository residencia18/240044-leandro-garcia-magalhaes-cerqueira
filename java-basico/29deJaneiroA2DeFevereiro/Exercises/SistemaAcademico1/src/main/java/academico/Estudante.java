package academico;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

@Entity
public class Estudante {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Integer Id;
	private String Nome;
	private String Email;
	private String Matricula;
	
	public Estudante() {
		super();
	}

	public Estudante(Integer id, String nome, String email, String matricula) {
		super();
		Id = id;
		Nome = nome;
		Email = email;
		Matricula = matricula;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
	}

	@Override
	public String toString() {
		return "Estudante [Id=" + Id + ", Nome=" + Nome + ", Email=" + Email + ", Matricula=" + Matricula + "]";
	}
	
	public static void main(String[] args) {
		
//		Estudante e1 = new Estudante(null,"Tõe","toe@tutu","111111");
//		Estudante e2 = new Estudante(null,"Lia","lia@tutu","222222");
//		Estudante e3 = new Estudante(null,"Tuca","tuca@tutu","333333");
//		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit_academico");
		EntityManager em = emf.createEntityManager();
		
		Estudante r = em.find(Estudante.class, 2);
		System.out.println(r);
//		
//		em.getTransaction().begin();
//		em.persist(e1);
//		em.persist(e2);
//		em.persist(e3);
//		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}
		
}
	
	

