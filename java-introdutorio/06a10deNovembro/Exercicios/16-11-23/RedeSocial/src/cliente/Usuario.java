package cliente;
import java.util.Scanner; //Classe de insercao de dados e etc..
import java.util.ArrayList;

public class Usuario {
	
	private String nome;
	private String email;
	private String nacionalidade;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	private ArrayList<String> postagens;
	
	public Usuario(){
		postagens = new ArrayList<String>();
	}
	
	public void adicionaPostagem(String post){
		postagens.add(post);
	}
	
	
	//MAIN ------------	
	public static void main(String[] args) {
		
	String postagem;	
		
		Scanner scanner = new Scanner(System.in);
		
		Usuario usuario = new Usuario();
		
		   System.out.println("Insira seu nome: ");
	       usuario.setNome(scanner.nextLine());
	       scanner.nextLine(); 
	       
	       System.out.println("Insira seu e-mail: ");
	       usuario.setEmail(scanner.nextLine());
	       scanner.nextLine(); 
	       
	       System.out.println("Insira sua nacionalidade: ");
	       usuario.setNacionalidade(scanner.nextLine());
	       scanner.nextLine();
	       
	       System.out.println("Insira uma postagem: ");
	       scanner.nextLine();
	       postagem = scanner.nextLine();
	       usuario.adicionaPostagem(postagem);
	       
	       scanner.close();
	       
	       //CONTINUAR NO SLIDE 14
	       //
	       //
	       
	}
}


