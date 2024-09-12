import java.util.List;

public class Animal {
	String nome;
	int tamanhoAnimal;
	List<String> biomaAnimal;
	boolean Carnivoro;	
	
	public Animal(String nome, int tamanhoAnimal, List<String> biomaAnimal, boolean Carnivoro) {
		super();
		this.nome = nome;
		this.tamanhoAnimal = tamanhoAnimal;
		this.biomaAnimal = biomaAnimal;
		this.Carnivoro = Carnivoro;
		
	}

	public String getNome() {
		return nome;
	}

	public int getTamanhoAnimal() {
		return tamanhoAnimal;
	}

	public List<String> getBiomaAnimal() {
		return biomaAnimal;
	}

	public boolean isCarnivoro() {
		return Carnivoro;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nome;
	}
}
