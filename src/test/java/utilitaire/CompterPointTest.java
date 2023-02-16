package utilitaire;

import controller.joueur.Joueur;
import controller.joueur.TypeMur;
import controller.manager.Couvercle;
import ia.TypeIa;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import tuile.Tuile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompterPointTest {
	private final Joueur joueur = new Joueur(1, TypeIa.IA_RANDOM, TypeMur.MUR_COULEUR);
	private final Couvercle couvercle = new Couvercle();

	@Test
	// 1 Point
	public void testCompter1Point() {
		joueur.getMurJoueur().placeTuileSurMur(3, 2, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 2);
		assertEquals(1, joueur.getCompterPoint().getPoint());
	}

	@Test
	// 1 Point
	public void testCompter1PointMaisCaseDif() {
		joueur.getMurJoueur().placeTuileSurMur(4, 2, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 4, 2);
		assertEquals(1, joueur.getCompterPoint().getPoint());
	}

	@Test
	// 2 Point
	public void testAjouterTuileCase32pourPlus2Point() {
		joueur.getMurJoueur().placeTuileSurMur(3, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 3, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 3);
		assertEquals(2, joueur.getCompterPoint().getPoint());
	}

	@Test
	// 2 Point
	public void test2PointsEnLigne() {
		joueur.getMurJoueur().placeTuileSurMur(3, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 3);
		assertEquals(2, joueur.getCompterPoint().getPoint());
	}

	@Test
	// 2 Point
	public void test3PointsEnLigne() {
		joueur.getMurJoueur().placeTuileSurMur(2, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 3);
		assertEquals(3, joueur.getCompterPoint().getPoint());
	}

	@Test
	// 1 Point
	public void testCoin() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 1, 0);
		assertEquals(1, joueur.getCompterPoint().getPoint());
	}

	@Test
	// 1 Point
	public void testPasdePoint() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 2);
		assertEquals(0, joueur.getCompterPoint().getPoint());
	}

	@Test
	// 16 Point
	public void testPlusieursMancheEtFinDePartie() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 1, 0);
		joueur.getMurJoueur().placeTuileSurMur(3, 2, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 2);
		joueur.getMurJoueur().placeTuileSurMur(4, 4, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 4, 4);
		joueur.getMurJoueur().placeTuileSurMur(5, 4, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 5, 4);
		//1Manche
		joueur.getMurJoueur().placeTuileSurMur(1, 1, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 1, 1);
		joueur.getMurJoueur().placeTuileSurMur(2, 0, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 2, 0);
		joueur.getMurJoueur().placeTuileSurMur(3, 1, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 1);
		//2Manche
		joueur.getMurJoueur().placeTuileSurMur(1, 2, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 1, 2);
		joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 4, 3);
		//3Manche
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(16, joueur.getCompterPoint().getPoint());
	}

	@Test
	// Test du malus plancher on retire 1 point
	public void testmalusPlancher1() {

		Tuile tuiles[] = joueur.getPlancherJoueur().remplirPlancher(Couleurs.BLANC, 1);
		couvercle.remplirCouvercle(tuiles);
		joueur.getCompterPoint().malusPlancher(joueur);
		assertEquals(0, joueur.getCompterPoint().getPoint());
	}



	@Test
	// Test du malus plancher on retire 4 points, 3 tuiles dans le plancher
	public void testmalusPlancher2() {
		Tuile[] tuiles = joueur.getPlancherJoueur().remplirPlancher(Couleurs.BLANC, 1);
		couvercle.remplirCouvercle(tuiles);
		tuiles = joueur.getPlancherJoueur().remplirPlancher(Couleurs.BLANC, 1);
		couvercle.remplirCouvercle(tuiles);
		tuiles = joueur.getPlancherJoueur().remplirPlancher(Couleurs.NOIR, 1);
		couvercle.remplirCouvercle(tuiles);
		joueur.getCompterPoint().malusPlancher(joueur);
		assertEquals(0, joueur.getCompterPoint().getPoint());
	}

	@Test
	// Test du malus plancher on retire 14 points, 7 tuiles dans le plancher
	public void testmalusPlancher3() {
		Tuile[] tuiles = joueur.getPlancherJoueur().remplirPlancher(Couleurs.BLANC, 2);
		couvercle.remplirCouvercle(tuiles);
		tuiles = joueur.getPlancherJoueur().remplirPlancher(Couleurs.NOIR, 3);
		couvercle.remplirCouvercle(tuiles);
		tuiles = joueur.getPlancherJoueur().remplirPlancher(Couleurs.ROUGE, 1);
		couvercle.remplirCouvercle(tuiles);
		tuiles = joueur.getPlancherJoueur().remplirPlancher(Couleurs.JAUNE, 1);
		couvercle.remplirCouvercle(tuiles);
		joueur.getCompterPoint().malusPlancher(joueur);
		assertEquals(0, joueur.getCompterPoint().getPoint());
	}

	@Test
	// Test point d'une ligne verticale
	public void testligneVerticale1() {
		joueur.getMurJoueur().placeTuileSurMur(1, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 2, joueur,0);
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(7, joueur.getCompterPoint().getPoint());
	}

	@Test
	// Test point de deux lignes verticales
	public void testligneVerticale2() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(1, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 3, joueur,0);
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(14, joueur.getCompterPoint().getPoint());
	}

	@Test
	// Test 1 ligne horizontale
	public void testligneHorizontale1() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(1, 1, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(1, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(1, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(1, 4, joueur,0);
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(2, joueur.getCompterPoint().getPoint());
	}

	@Test
	// Test 3 ligne horizontale
	public void testligneHorizontale2() {
		for (int i = 1; i < 4; i++) {
			joueur.getMurJoueur().placeTuileSurMur(i, 0, joueur,0);
			joueur.getMurJoueur().placeTuileSurMur(i, 1, joueur,0);
			joueur.getMurJoueur().placeTuileSurMur(i, 2, joueur,0);
			joueur.getMurJoueur().placeTuileSurMur(i, 3, joueur,0);
			joueur.getMurJoueur().placeTuileSurMur(i, 4, joueur,0);
		}
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(6, joueur.getCompterPoint().getPoint());
	}

	@Test
	// Test une ligne de couleur rempli
	public void testCouleur1() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 1, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 4, joueur,0);
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(10, joueur.getCompterPoint().getPoint());
	}


	@Test
	// Test 2 lignes de couleur rempli
	public void testCouleur2() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 1, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 4, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(1, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 4, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 1, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 2, joueur,0);
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(20, joueur.getCompterPoint().getPoint());
	}

	@Test
	// Test le max de point avec un mur rempli
	public void testToutPointFinDeMancheTotal() {
		for (int i = 0; i < 5; i++) {
			joueur.getMurJoueur().placeTuileSurMur(1, i, joueur,0);
			joueur.getMurJoueur().placeTuileSurMur(2, i, joueur,0);
			joueur.getMurJoueur().placeTuileSurMur(3, i, joueur,0);
			joueur.getMurJoueur().placeTuileSurMur(4, i, joueur,0);
			joueur.getMurJoueur().placeTuileSurMur(5, i, joueur,0);
		}
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(95, joueur.getCompterPoint().getPoint());
	}

	@Test
	// test ajout 1 pointLigneMotif + 10 PointFinPartie
	public void testComptePointLigneMotifEtFinDeManche1() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 1, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 4, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 2);
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(11, joueur.getCompterPoint().getPoint());
	}

	@Test
	// test ajout 4 pointLigneMotif + 10 PointFinPartie
	public void testComptePointLigneMotifEtFinDeManche2() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 1, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 1, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 4, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 4, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 2);
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(14, joueur.getCompterPoint().getPoint());
	}

	@Test
	// test ajout 4 pointLigneMotif + 10 PointFinPartie
	public void testComptePointLigneMotifEtFinDeManche3() {
		joueur.getMurJoueur().placeTuileSurMur(1, 0, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(1, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 1, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(2, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 1, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 2, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(3, 4, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 4, joueur,0);
		joueur.getMurJoueur().placeTuileSurMur(5, 3, joueur,0);
		joueur.getCompterPoint().compterPointLigneMotif(joueur, 3, 2);
		joueur.getCompterPoint().compterPointFinPartie(joueur);
		assertEquals(21, joueur.getCompterPoint().getPoint());
	}
}