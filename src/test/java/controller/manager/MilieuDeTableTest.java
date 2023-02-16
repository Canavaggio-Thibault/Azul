/** Classe de test pour Milieu de Table 
 *
 *
 */

package controller.manager;

import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import tuile.Tuile;
import utilitaire.Utilitaire;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class MilieuDeTableTest {
	CentreDeTable leMilieuDeTable = new CentreDeTable();
	Tuile[] tab = new Tuile[24];
	Tuile[] tabCourt1 = new Tuile[4];
	Tuile[] tabCourt2 = new Tuile[4];



	/* Vérifie qu'il est possible de remplir le centre de la table s'il est vide */
	@Test
	public void remplirCentreVide () {
		for (int i = 0; i < tab.length; i++) {
			tab[i] = new Tuile();
		}
		/* Remplis le centre de la table avec tab */
		leMilieuDeTable.remplirCentre(tab);

		for (int i = 0; i < leMilieuDeTable.getCentre().length; i++) {
			assertEquals(leMilieuDeTable.getCentre()[i].getCouleur(), tab[i].getCouleur());
		}

	}


	/* Vérifie s'il est possible d'ajouter des tuiles(et dans l'ordre)  à un centre qui en possède déjà */
	@Test
	public void remplirCentreNonVide () {
		/* Remplissage des deux tableaux court avec des tuiles de couleur aléatoire */
		for (int i = 0; i < tabCourt1.length; i++) {
			tabCourt1[i] = Utilitaire.getRandom();
			tabCourt2[i] = Utilitaire.getRandom();
		}

		/* On remplit le centre avec ces deux tableaux de tuiles */
		leMilieuDeTable.remplirCentre(tabCourt1);
		leMilieuDeTable.remplirCentre(tabCourt2);

		/* Compare le début du centre avec tabCourt1 */
		for (int i = 0; i < 4; i++) {
			assertEquals(leMilieuDeTable.getCentre()[i].getCouleur(), tabCourt1[i].getCouleur());
		}

		/* Compare la suite du centre avec tabCourt2 */
		int j = 0;
		for (int i = 4; i < 8; i++) {
			assertEquals(leMilieuDeTable.getCentre()[i].getCouleur(), tabCourt2[j++].getCouleur());
		}
	}

	/* Vérifie si on peut récupérer des tuiles de même couleur et les retirer du centre */
	@Test
	public void recupT () {
		for (int i = 0; i < tab.length; i++) {
			tab[i] = Utilitaire.getRandom();
		}

		/* Remplit le centre de table */
		leMilieuDeTable.remplirCentre(tab);

		/* Compte le nombre de tuile rouge qui ont été générée au centre de la table */
		int cptRouge = 0;
		for (int i = 0; i < leMilieuDeTable.getCentre().length; i++) {
			if (leMilieuDeTable.getCentre()[i].getCouleur() == Couleurs.ROUGE) {
				cptRouge++;
			}
		}
		CentreDeTable tabCompare = leMilieuDeTable;
		/* Stocke un tableau de tuile pioché depuis le centre de la table (toutes les rouges) */
		Tuile[] tabSortie = leMilieuDeTable.getTuileMemeCouleur(Couleurs.ROUGE);



		cptRouge = 0;
		for (int i = 0; i < leMilieuDeTable.getCentre().length; i++) {
			if (leMilieuDeTable.getCentre()[i].getCouleur() == Couleurs.ROUGE) {
				cptRouge++;
			}
		}
		/* Vérifie qu'il n'y a plus de rouge dans le centre */
		assertEquals(0, cptRouge);

		/* Vérifie que le milieu ne soit pas modifié (hormis les tuiles d'une même couleur qui ont été piochée) et que les tuiles piochées soient bien retirées */
		for (int i = 0; i < leMilieuDeTable.getCentre().length; i++) {
			if(leMilieuDeTable.getCentre()[i].getCouleur() != Couleurs.ROUGE) {
				assertEquals(leMilieuDeTable.getCentre()[i].getCouleur(), tabCompare.getCentre()[i].getCouleur());
			}

			if(leMilieuDeTable.getCentre()[i].getCouleur() == Couleurs.VIDE) {
				assertEquals(leMilieuDeTable.getCentre()[i].getCouleur(), Couleurs.VIDE);
			}
		}
	}
	@Test
	//verifie si le centre de la table a été correctement restaurer
	public void restaurerCentre() {
		for (int i = 0; i < tab.length; i++) {
			tab[i] = Utilitaire.getRandom();
		}
		leMilieuDeTable.remplirCentre(tab);
		leMilieuDeTable.restaureCentreDeTable();
		for (int i = 0; i < leMilieuDeTable.getCentre().length; i++) {
			assertEquals(leMilieuDeTable.getCentre()[i].getCouleur(), Couleurs.VIDE);
		}
	}

}
