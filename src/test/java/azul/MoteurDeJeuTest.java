package azul;

import controller.joueur.TypeMur;
import controller.manager.Manager;

/** Classe main permettant de lancer le jeu */
public class MoteurDeJeuTest {

	public static void main(String[] args) {
		/* On lance la manche */
		int i = 0;
		Manager manager = new Manager(2, TypeMur.MUR_COULEUR);
		while (i++ < 1)
			manager.lancePartie();
	}
}
