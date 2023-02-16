package controller.joueur;
import ia.TypeIa;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import tuile.Tuile;

import static org.junit.jupiter.api.Assertions.*;

public class LigneMotifTest {

	/* Initialisation d'une tuile classique (jaune) et création de les lignes de motif */
	Tuile tuile = new Tuile(Couleurs.JAUNE);

	private final Joueur joueur = new Joueur(1, TypeIa.IA_RANDOM,TypeMur.MUR_COULEUR);

	@Test
	/* Vérifie si le placement de la tuile en ligne 0 case 0 est fonctionnel */
	public void testPlacementTuileLigne0Case0() {
		joueur.getLigneMotifJoueur().placeTuileSurLigne(5, Couleurs.JAUNE, 1, joueur);
		Tuile inter = joueur.getLigneMotifJoueur().getCase(5,0);
		assertEquals(Couleurs.JAUNE, inter.getCouleur());
	}
	@Test
	public void testOverloadUneLigne () {
		/* Essais de placer 10 tuiles sur une même ligne */
		for (int i = 0; i < 10; i++) {
			joueur.getLigneMotifJoueur().placeTuileSurLigne(4, Couleurs.JAUNE, 1, joueur);
		}
		/* Vérifie que la  case est remplis */
		assertEquals(Couleurs.JAUNE, joueur.getLigneMotifJoueur().getCase(4, 1).getCouleur());
		/* Vérifie que a dernière case de la ligne 4 est remplis */
		assertEquals(Couleurs.JAUNE, joueur.getLigneMotifJoueur().getCase(4, 3).getCouleur());
		/* Vérifie que la case n+1 de la ligne 4 est vide bien qu'une tuile a voulu y être placé */
		assertNull(joueur.getLigneMotifJoueur().getCase(4, 5));
	}

	public static class MurTest {

		private final Joueur joueur = new Joueur(1, TypeIa.IA_RANDOM,TypeMur.MUR_COULEUR);

		@Test
		public void testJspCeQueCaFait () {
			int ligne = 5;
			joueur.getLigneMotifJoueur().placeTuileSurLigne(ligne, Couleurs.JAUNE, 1, joueur);
			joueur.getMurJoueur().placeTuileSurMur(ligne, 0, joueur,0);
			Mur mur = joueur.getMurJoueur();
			assertEquals(mur.getMur()[4][0].getCouleur(), Couleurs.JAUNE);
		}

		@Test
		public void VerifTrue (){
			int ligne = 3;
			joueur.getLigneMotifJoueur().placeTuileSurLigne(ligne, Couleurs.BLEU, 1, joueur);

			joueur.getMurJoueur().placeTuileSurMur(ligne, 2, joueur,0);
			Mur mur = joueur.getMurJoueur();
			assertTrue(mur.getMur()[ligne - 1][2].getBool());
		}
	}
}
