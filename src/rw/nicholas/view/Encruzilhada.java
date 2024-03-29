package rw.nicholas.view;

import rw.nicholas.model.Fase;
import rw.nicholas.model.exceptions.EntradaProibidaException;
import rw.nicholas.model.exceptions.EscolhaInvalidaException;
import rw.nicholas.model.personagens.Paladino;
import rw.nicholas.controller.InimigoController;
import rw.nicholas.controller.ModoBatalha;

public class Encruzilhada extends Fase{
	private int tmpCount;
	private Paladino paladino;
	private InimigoController icInimigo = new InimigoController();
	
	public Encruzilhada() {}
	
	public Encruzilhada(Paladino paladino) {
		this.paladino = paladino;
		tmpCount = 0;
	}
	
	private void menuEncruzilhada() {
		System.out.println("Muito bem, " + paladino.getNome() + ". Prepare-se para uma aventura que se passa nos reinos"
				+ " do norte.\n");
		pausarDialogo(2);
		
		System.out.println("\tVocê é um Paladino, uma classe de fiéis guerreiros da Luz, que com a perseguição"
				+ " instaurada contra os seguidores da Luz a partir da ascensão do culto ao Fogo Eterno,"
				+ " sua Ordem se viu dizimada, e com o fim da Ordem dos Paladinos você decide exilar-se a fim de"
				+ " estudar a Luz, como bom Paladino que é, você sabe que sua fé é tão importante quanto o aço que"
				+ " carrega empunhado.\n");
		pausarDialogo(8);
		
		System.out.println("Diante de uma encruzilhada, você tem 4 possíveis caminhos: "
				+ "\n\t1- À sua frente Novigrad"
				+ "\n\t2- À sua esquerda Kaer Morhen"
				+ "\n\t3- Atrás de você, está Velen"
				+ "\n\t4- E à sua direita Oxenfurt"
				+ "\n\nQual caminho você irá escolher? Sabendo que no caminho de Novigrad você vê a chama do Fogo Eterno.");
	}
	
	public void start() {
		do {
			int opt;
			while(true) {
				try {
					menuEncruzilhada();
					opt = entrada(1, 4);
					break;
				} catch (EntradaProibidaException epe) {
					System.out.println(epe.getMessage());
				} catch (EscolhaInvalidaException eie) {
					System.out.println(eie.getMessage());
				}
			}
			
			switch (opt) {
			case 1:
				System.out.println("Como fiél guerreiro da Luz, você irá defender o direito daqueles que também "
						+ "quiserem seguir o caminho da Luz.\n");
				pausarDialogo(4);
				
				bandidosEncruzilhada();
				tmpCount = 3; // Para sair do loop
				break;

			default:
				if (tmpCount == 0){
					System.out.println("Você é um guerreiro da Luz, Novigrad está sendo oprimida pelos nobres "
							+ "que aderiram ao culto do Fogo Eterno.\nVocê tem certeza que não quer defender "
							+ "o povo lá? Qual caminho você irá escolher?\n");
				}
				tmpCount++;
				break;
			}
		} while (tmpCount < 2);
		if (tmpCount == 2) {
			System.out.println("Tudo bem, você morreu abandonado pela Luz.");
			paladino.subtrairVida(paladino.getMaxVida());
			
		}
	}
	
	private void menuBandidosEncruzilhada() {
		System.out.println("Seguindo sua viagem a Novigrad, você dá de cara com dois brutamontes que tinham a "
				+ "insígnia do Fogo Eterno em seus trapos.\n"
				+ "\nO que você faz?\n"
				+ "\t1- Esconder-se\n"
				+ "\t2- Sacar arma\n"
				+ "\t3- Consultar informações do seu Paladino");
	}
	
	private void bandidosEncruzilhada() {
		int opt = 0;
		boolean continuar = true;

		icInimigo.inserirInimigo("brutamonteEncruzilhada1", "Quebra-Ossos", 10, new Encruzilhada());
		icInimigo.inserirInimigo("brutamonteEncruzilhada2", "Palitinho", 6, new Encruzilhada());
		
		do {
			while(true) {
				try {
					menuBandidosEncruzilhada();
					opt = entrada(1, 3);
					break;
				} catch (EntradaProibidaException epe) {
					System.out.println(epe.getMessage());
				} catch (EscolhaInvalidaException eie) {
					System.out.println(eie.getMessage());
				}
			}
			
			switch (opt) {
			case 1:
				if (paladino.randDado(20, 1) >= 15) {
					System.out.println("Parabéns, você conseguiu conjurar 'Manto do Exilado', ficando oculto.\n");
					pausarDialogo(3);
					continuar = false;
					break;
				} else {
					System.out.println("Não foi dessa vez, prepare-se para a batalha!\n");
					pausarDialogo(2);
				}
			case 2:
				ModoBatalha fight = new ModoBatalha();
				fight.batalhar(paladino, new Encruzilhada());
				
				if (paladino.getVivo()) {
					System.out.println("Parabéns, por meio de sua fé e determinação você foi capaz de derrotá-los.\n");
					pausarDialogo(3);
					continuar = false;
				} else {
					continuar = false;
				}
				break;
			case 3:
				System.out.println(paladino.toString());
				continuar = true;
				break;

			default:
				System.out.println("Opção inválida!");
				continuar = true;
				break;
			}
		} while(continuar);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Encruzilhada) {
			return true;
		}
		return false;
	}
}
