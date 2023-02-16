/** PlancherTest
 *
 *
 */

package controller.joueur;

import controller.manager.Couvercle;
import ia.TypeIa;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import tuile.Tuile;
import utilitaire.Utilitaire;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlancherTest {
	Tuile[] tab = new Tuile[7];
	private final Joueur joueur = new Joueur(1, TypeIa.IA_RANDOM,TypeMur.MUR_COULEUR);

	/* Vérifie qu'il est possible de remplir sans écraser les tuiles qui étaient déjà présente dans le plancher */
	@Test
	public void remplirPlancherVide () {
		Couvercle couvercle = new Couvercle();
		for (int i = 0; i < tab.length; i++) {
			tab[i] = Utilitaire.getRandom();
			/* Remplis le plancher avec tab */
			Tuile[] tuiles = joueur.getPlancherJoueur().remplirPlancher(tab[i].getCouleur(), 1);
			couvercle.remplirCouvercle(tuiles);
		}

		Tuile[] plancher = joueur.getPlancherJoueur().getPlancher();
		for (int i = 0; i <plancher.length; i++) {
			assertEquals(plancher[i].getCouleur(), tab[i].getCouleur());
		}
	}

	/* Vérifie lorsque l'on remplie le plancher avec les tuiles d'une meme couleurs si les tuiles sont bien placé dans le plancher */
	@Test
	public void remplirPlancherUneCouleur () {
		Couvercle couvercle = new Couvercle();
		Tuile[] tuiles = joueur.getPlancherJoueur().remplirPlancher(Couleurs.BLEU, 3);
		couvercle.remplirCouvercle(tuiles);

		Tuile[] plancher = joueur.getPlancherJoueur().getPlancher();
		for (int i = 0; i < 3; i++) {
			assertEquals(Couleurs.BLEU, plancher[i].getCouleur());
		}
	}

}