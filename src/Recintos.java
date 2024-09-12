import java.util.List;

public class Recintos {
	
	private int num;
	private int tamanhoRecinto;
	private List<String> biomaRecinto;
	private List<Animal> animaisNoRecinto;
	private int espacoDisponivel;
	
	
	public Recintos(int num, int tamanho, List<String> biomaRecinto, List<Animal> animaisNoRecinto) {
		super();
		this.num = num;
		this.tamanhoRecinto = tamanho;
		this.biomaRecinto = biomaRecinto;
		this.animaisNoRecinto = animaisNoRecinto;
		this.espacoDisponivel = this.tamanhoRecinto;
		if(animaisNoRecinto.size()>0) {
			for(int i = 0; i < animaisNoRecinto.size(); i++) {
				this.espacoDisponivel -= this.animaisNoRecinto.get(i).getTamanhoAnimal();
			}
		}
	}
	public int getNum() {
		return num;
	}


	public int getTamanhoRecinto() {
		return tamanhoRecinto;
	}


	public List<String> getBiomaRecinto() {
		return biomaRecinto;
	}


	public List<Animal> getAnimaisNoRecinto() {
		return animaisNoRecinto;
	}


	public int getEspacoDisponivel() {
		return espacoDisponivel;
	}
	public void setEspacoDisponivel(int espacoDisponivel) {
		this.espacoDisponivel -= espacoDisponivel;
	}
	@Override
	public String toString() {
		return  "Recinto " + this.num + " (espaço livre: " + this.espacoDisponivel + " total: " + this.tamanhoRecinto + ")" ;
	}
	
	public void setAnimaisNoRecinto(Animal animal, int quantosAnimais) throws Erro {
		if(animal == null) {
			System.out.println("Animal Inválido");
			throw new Erro("Animal Inválido");
		}else if(quantosAnimais <= 0) {
			System.out.println("Quantidade Inválida");
			throw new Erro("Quantidade Inválida");
		}else {
			this.espacoDisponivel -= (animal.tamanhoAnimal)*quantosAnimais;
			for(int i = 0; i < quantosAnimais; i++) {
				this.animaisNoRecinto.add(animal);				
			}
		}
	}
	
}
