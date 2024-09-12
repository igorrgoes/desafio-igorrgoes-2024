import java.util.Scanner;

public class Zoologico {
	
	public static void main(String[] args) {
		

		RecintosZoo admzoo = new RecintosZoo();		
		Scanner input = new Scanner(System.in);
		String entrada = input.nextLine();
		String[] animalQtd = entrada.split(",");
		String nomeAnimalSerInserido = animalQtd[0]; 
		int qtdAnimalSerInserido = Integer.parseInt(animalQtd[1].trim()); 
		String nomeAnimalSerInseridoSemAspas = nomeAnimalSerInserido.substring(1, nomeAnimalSerInserido.length()-1);
		
		try {
			admzoo.analisaRecintos(nomeAnimalSerInseridoSemAspas, qtdAnimalSerInserido);
		} catch (Erro e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.close();
	}
}