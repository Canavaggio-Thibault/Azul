package controller.joueur;

import ia.TypeIa;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;

import static org.junit.jupiter.api.Assertions.*;

public class MurCouleurTest {

	private final Joueur joueur = new Joueur(2, TypeIa.IA_RANDOM,TypeMur.MUR_COULEUR);

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
	@Test
	public void TouteLesLigneDuMurPleine(){
		Mur mur = joueur.getMurJoueur();
		//verifie le nombre de couleur sur la ligne du mur
		for (int i = 0; i < 5; i++) {
			mur.placeTuileSurMur(1,i, joueur,0);
			mur.placeTuileSurMur(2,i, joueur,0);
			mur.placeTuileSurMur(3,i, joueur,0);
			mur.placeTuileSurMur(4,i, joueur,0);
			mur.placeTuileSurMur(5,i, joueur,0);
		}
		assertTrue(mur.lignePleine(2));
		assertTrue(mur.lignePleine(1));
		assertTrue(mur.lignePleine(3));
		assertTrue(mur.lignePleine(4));
		assertTrue(mur.lignePleine(5));
	}
	@Test
	public void LigneDuMurPresquePleine(){
		Mur mur = joueur.getMurJoueur();
		//verifie le nombre de couleur sur la ligne du mur
		for (int i = 0; i < 4; i++) {
			mur.placeTuileSurMur(1,i, joueur,0);
			mur.placeTuileSurMur(2,i, joueur,0);
			mur.placeTuileSurMur(3,i, joueur,0);
			mur.placeTuileSurMur(4,i, joueur,0);
			mur.placeTuileSurMur(5,i, joueur,0);
		}
		assertFalse(mur.lignePleine(2));
		assertFalse(mur.lignePleine(1));
		assertFalse(mur.lignePleine(3));
		assertFalse(mur.lignePleine(4));
		assertFalse(mur.lignePleine(5));
	}
	@Test
	public void Couleurdejaposer(){
		//placer une tuile de couleur bleue sur le mur
		Mur mur = joueur.getMurJoueur();
		for (int i = 0; i < 3; i++) {
			mur.placeTuileSurMur(3, i, joueur, 0);
		}
		for (int i = 0; i < 2; i++) {
			mur.placeTuileSurMur(2, i, joueur, 0);
		}
		for (int i = 0; i < 5; i++) {
			mur.placeTuileSurMur(1, i, joueur, 0);
		}
		for (int i = 0; i < 5; i++) {
			mur.placeTuileSurMur(4, i, joueur, 0);
		}
		for (int i = 0; i < 1; i++) {
			mur.placeTuileSurMur(5, i, joueur, 0);
		}
		assertTrue(mur.couleurDejaPoserLigne(1, Couleurs.BLEU));
		assertTrue(mur.couleurDejaPoserLigne(3, Couleurs.BLEU));
		assertTrue(mur.couleurDejaPoserLigne(3, Couleurs.BLEU));
		assertTrue(mur.couleurDejaPoserLigne(4, Couleurs.BLEU));
		assertTrue(mur.couleurDejaPoserLigne(1, Couleurs.BLEU));
		assertTrue(mur.couleurDejaPoserLigne(2, Couleurs.BLEU));
		assertFalse(mur.couleurDejaPoserLigne(2, Couleurs.JAUNE));
		assertFalse(mur.couleurDejaPoserLigne(2, Couleurs.ROUGE));
		assertFalse(mur.couleurDejaPoserLigne(2, Couleurs.NOIR));
		assertFalse(mur.couleurDejaPoserLigne(5, Couleurs.BLANC));
		assertTrue(mur.couleurDejaPoserLigne(5, Couleurs.JAUNE));
	}
}