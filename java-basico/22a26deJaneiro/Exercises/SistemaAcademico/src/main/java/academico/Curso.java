package academico;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Curso {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String Nome;
	private Integer NumSemestres;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public Integer getNumSemestres() {
		return NumSemestres;
	}
	public void setNumSemestres(Integer numSemestres) {
		NumSemestres = numSemestres;
	}
	public Curso(Integer id, String nome, Integer numSemestres) {
		super();
		Id = id;
		Nome = nome;
		NumSemestres = numSemestres;
	}
	public Curso() {
		super();
	}
	@Override
	public String toString() {
		return "Curso [Id=" + Id + ", Nome=" + Nome + ", NumSemestres=" + NumSemestres + "]";
	}
	
	
	

}
