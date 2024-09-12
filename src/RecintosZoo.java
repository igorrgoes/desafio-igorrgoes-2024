import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecintosZoo {
	


	Animal leao = new Animal("LEAO", 3, Arrays.asList("savana"), true);
	Animal leopardo = new Animal("LEOPARDO", 2, Arrays.asList("savana"), true);
	Animal crocodilo = new Animal("CROCODILO", 3, Arrays.asList("rio"), true);
	Animal macaco = new Animal("MACACO", 1, Arrays.asList("savana", "floresta"), false);
	Animal gazela = new Animal("GAZELA", 2, Arrays.asList("savana"), false);
	Animal hipopotamo = new Animal("HIPOPOTAMO", 2, Arrays.asList("savana", "rio"), false);
	
	Recintos r1 = new Recintos(1, 10, Arrays.asList("savana"), new ArrayList<>(Arrays.asList(macaco, macaco, macaco)));     
	Recintos r2 = new Recintos(2, 5, Arrays.asList("floresta"), new ArrayList<>(Arrays.asList()));
	Recintos r3 = new Recintos(3, 7, Arrays.asList("savana","rio"), new ArrayList<>(Arrays.asList(gazela)));
	Recintos r4 = new Recintos(4, 8, Arrays.asList("rio"), new ArrayList<>(Arrays.asList()));
	Recintos r5 = new Recintos(5, 9, Arrays.asList("savana"), new ArrayList<>(Arrays.asList(leao)));		
	
	
	List<Animal> animais = new ArrayList<>(Arrays.asList(leao, leopardo, crocodilo, macaco, gazela, hipopotamo));
	List<Recintos> recintos = new ArrayList<>(Arrays.asList(r1,r2,r3,r4,r5));
		

	@SuppressWarnings("unused")
	public List<Recintos> analisaRecintos(String nomeAnimalSerInserido, int qtdAnimalSerInserido) throws Erro {
		
		Animal animalParaInserir = null;
		
		for(int i = 0; i < animais.size(); i++) {
			if(animais.get(i).getNome().equalsIgnoreCase(nomeAnimalSerInserido)) {
				animalParaInserir = animais.get(i);
				break;
			}
		}
		
		List<Recintos> recintosAdequados = new ArrayList<>();

		if (animalParaInserir == null || qtdAnimalSerInserido <= 0) {
			try {
				r1.setAnimaisNoRecinto(animalParaInserir, qtdAnimalSerInserido);
			} catch (Erro e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
		}
		if (!(animalParaInserir == null)) {

			// CHECAGEM DE BIOMAS

			for (int i = 0; i < recintos.size(); i++) {
				for (String biomaAnimal : animalParaInserir.getBiomaAnimal()) {
					if (recintos.get(i).getBiomaRecinto().contains(biomaAnimal)) {
						recintosAdequados.add(recintos.get(i));
						break; // EVITAR DUPLICATAS
					}
				}
			}

			// CHECAGEM DE ESPAÇO DISPONIVEL

			for (int i = recintosAdequados.size() - 1; i >= 0; i--) {
				if (recintosAdequados.get(i).getEspacoDisponivel() < ((animalParaInserir.getTamanhoAnimal()) * qtdAnimalSerInserido)) {
					recintosAdequados.remove(i);
				}
			}

			// CHECAGEM CARNIVORO

			if (animalParaInserir.isCarnivoro()) {
				for (int i = recintosAdequados.size() - 1; i >= 0; i--) {
					for (int j = 0; j < recintosAdequados.get(i).getAnimaisNoRecinto().size(); j++) {
						if (!recintosAdequados.get(i).getAnimaisNoRecinto().get(j).equals(animalParaInserir)) {
							recintosAdequados.remove(i);
							break;
						}
					}
				}
			}

			// CHECAGEM SE TEM CARNIVORO, PARA OS HERBIVOROS NÃO ENTRAREM

			if (!animalParaInserir.isCarnivoro()) {
				for (int i = recintosAdequados.size() - 1; i >= 0; i--) {
					for (int j = 0; j < recintosAdequados.get(i).getAnimaisNoRecinto().size(); j++) {
						if (recintosAdequados.get(i).getAnimaisNoRecinto().get(j).isCarnivoro()) {
							recintosAdequados.remove(i);
							break;
						}
					}
				}
			}

			// CHECAGEM SE O RECINTO TEM ANIMAIS DIFERENTES DO HIPOPOTAMO PARA ELE ENTRAR

			if (animalParaInserir.equals(hipopotamo)) {
				for (int i = recintosAdequados.size() - 1; i >= 0; i--) {
					for (int j = 0; j < recintosAdequados.get(i).getAnimaisNoRecinto().size(); j++) {
						if (!recintosAdequados.get(i).getAnimaisNoRecinto().get(j).equals(hipopotamo)
								&& !recintosAdequados.get(i).equals(r3)) {
							recintosAdequados.remove(i);
						}
					}
				}
			}

			// CHECAGEM PARA NÃO INSERIR UM ANIMAL ONDE JA TEM UM HIPOPOTAMO, SE NÃO FOR O
			// RECINTO 3

			for (int i = recintosAdequados.size() - 1; i >= 0; i--) {
				for (int j = 0; j < recintosAdequados.get(i).getAnimaisNoRecinto().size(); j++) {
					if (recintosAdequados.get(i).getAnimaisNoRecinto().contains(hipopotamo)
							&& !recintosAdequados.get(i).equals(r3) && !animalParaInserir.equals(hipopotamo)) {
						recintosAdequados.remove(i);
						break;

					}

				}
			}

			// CHECAR SE O MACACO VAI FICAR SOZINHO

			if (animalParaInserir.equals(macaco)) {
				for (int i = recintosAdequados.size() - 1; i >= 0; i--) {
					if (recintosAdequados.get(i).getAnimaisNoRecinto().size() == 0 && qtdAnimalSerInserido == 1) {
						recintosAdequados.remove(i);
					}

				}

			}
		}

		// ADICIONAR ENTRADAS
		for (int i = 0; i < recintosAdequados.size(); i++) {
			try {
				recintosAdequados.get(i).setAnimaisNoRecinto(animalParaInserir, qtdAnimalSerInserido);
			} catch (Erro e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		//CHECAR SE POSSUI MAIS DE UMA RAÇA DENTRO DO RECINTO PARA DIMINUIR O ESPAÇO DISPONIVEL EM UM RECINTO(SE NÃO JA TIVER SIDO CONSIDERADO)
		int somaTamanhoAnimais = 0;
		for(int i = 0; i < recintosAdequados.size(); i++) {
			somaTamanhoAnimais = 0;
			for(int j = 0; j < recintosAdequados.get(i).getAnimaisNoRecinto().size(); j++) {
				somaTamanhoAnimais += recintosAdequados.get(i).getAnimaisNoRecinto().get(j).getTamanhoAnimal();
			}
			
			if((recintosAdequados.get(i).getTamanhoRecinto())  == (recintosAdequados.get(i).getEspacoDisponivel() + somaTamanhoAnimais)) {
				for (int k = 0; k < recintosAdequados.size(); k++) {
					for (int l = 1; l < recintosAdequados.get(k).getAnimaisNoRecinto().size(); l++) {
						if (recintosAdequados.get(k).getAnimaisNoRecinto().size() > 1) {
							if (!(recintosAdequados.get(k).getAnimaisNoRecinto().get(l).equals(recintosAdequados.get(k)
									.getAnimaisNoRecinto().get(l - 1)))) {
								recintosAdequados.get(k).setEspacoDisponivel(1);
							}
						}
					}
				}
				break;
			}
		}
		if(recintosAdequados.size() == 0) {
			System.out.println("Não há recinto viável");
			throw new Erro("Não há recinto viável");
		}else {
		System.out.print("recintosViaveis: ");
		System.out.println(recintosAdequados);
		}
		return recintosAdequados;
	}
}
