package ia.typeIA;

/*

  Classe de test permettant de tester le comportement attendu de l'IA random

 */

import controller.joueur.Joueur;
import controller.joueur.TypeMur;
import controller.manager.CentreDeTable;
import controller.manager.Pioche;
import controller.manager.fabrique.Fabrique;
import controller.manager.fabrique.ListeDesFabriques;
import ia.TypeIa;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import tuile.Tuile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class IArandomTest {
	Fabrique fabrique;
	Fabrique fabriqueBis;
	ListeDesFabriques fabriques;
	Joueur joueur = new Joueur(1, TypeIa.IA_RANDOM, TypeMur.MUR_COULEUR);
	CentreDeTable centreDeTable = new CentreDeTable();

	public void setUp () {
		/* Création des objets permettant le test */
		fabriques = new ListeDesFabriques(1, new Pioche());
		fabrique = new Fabrique(Couleurs.JAUNE, Couleurs.JAUNE, Couleurs.ROUGE, Couleurs.BLEU);
	}

	public void setUpBis (){
		fabriques = new ListeDesFabriques(1, new Pioche());
		fabriqueBis = new Fabrique(Couleurs.ROUGE, Couleurs.BLANC, Couleurs.BLEU, Couleurs.ROUGE);
	}

	@Test
	/*
	  Vérifie que l'IA random pioche bien aléatoirement dans la fabrique
	 */
	public void testChoixPiocheAleatoireDansUneFabrique () {
		setUp();
		fabriques.addFabrique(fabrique);
		//sélectione une couleur aléatoire de la fabrique (jaune, rouge ou bleu)
		joueur.getIa().choixCouleurDansFabrique(fabriques, 3, joueur.getMurJoueur());
		Couleurs couleurSelectione = joueur.getIa().getChoixCouleur();
		//vérifie que la couleur fait bien partit de la fabrique
		assertNotEquals(couleurSelectione, Couleurs.BLANC);
		assertNotEquals(couleurSelectione, Couleurs.VIDE);
		assertNotEquals(couleurSelectione, Couleurs.NOIR);
	}

	/**
	 * Vérifie que l'IA random sélectione une tuile de couleur aléatoire (et non vide) du centre
	 */
	@Test
	public void testChoixCouleurDansCentre() {
		/* Place sur centre de la table du jaune, bleu, vide, jaune et rouge */
		Tuile[] tabTuile= {new Tuile(Couleurs.JAUNE), new Tuile(Couleurs.BLEU),
				new Tuile(Couleurs.VIDE), new Tuile(Couleurs.JAUNE),
				new Tuile(Couleurs.ROUGE)};
		centreDeTable.remplirCentre(tabTuile);

		/* L'IA Random choisie une couleur aléatoire présente sur le centre */
		joueur.getIa().choixCouleurDansCentre(centreDeTable, joueur.getMurJoueur());
		Couleurs couleurChoisit = joueur.getIa().getChoixCouleur();
		/* Vérifie que la couleur choisit est bien dans le centre : tout sauf vide, blanc et noir */
		assertNotEquals(couleurChoisit, Couleurs.VIDE);
		assertNotEquals(couleurChoisit, Couleurs.BLANC);
		assertNotEquals(couleurChoisit, Couleurs.NOIR);
	}


	@Test
	/*
	  Vérifie que l'Ia random pose les tuiles sur une ligne de motif aléatoire
	  (ou sur la ligne qui a déjà cette couleur de posée
	 */
	public void testChoixPlacementAleatoireSurUneLigne () {
		/* Placement de 2 tuiles rouge sur la ligne numéro 3 (indice 2 dans le tableau) */
		joueur.getLigneMotifJoueur().placeTuileSurLigne(3, Couleurs.ROUGE, 2, joueur);
		joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
		int ligne = joueur.getIa().getLigne();
		/* Vérifie que l'IA a bien sélectioner le ligne d'indice 3 */
		assertEquals(ligne, 3);

		/* Vérifie que l'IA ne sélectione pas la ligne indice 2 contenant du rouge pour le placement
		 * d'une tuile bleu (sélectione 20 fois pour être sûr) */
		for (int i = 0; i < 20; i++) {
			joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.BLEU, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
			ligne = joueur.getIa().getLigne();
			assertNotEquals(ligne, 3);
		}

		//Remplie la ligne 1 du mur
		for (int i = 0; i < 5; i++) {
			joueur.getMurJoueur().placeTuileSurMur(1, i, joueur, 0);
		}

		for (int i = 0; i < 20; i++) {
			joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
			assertEquals(joueur.getIa().getLigne(), 3);  // l'ia random doit choisir la ligne 3 avec du rouge deja posé et une tuile dispo
			joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.BLEU, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
			assertNotEquals(joueur.getIa().getLigne(), 3);    // verif que le bleu se pose pas ligne 1 car la tuile bleu de la ligne du mur 1 est deja remplie
			assertNotEquals(joueur.getIa().getLigne(), 1);        //verif que le bleu se pose pas sur la ligne 3 contenant du rouge
		}

		for (int i = 0; i < 5; i++) {
			joueur.getMurJoueur().placeTuileSurMur(2, i, joueur, 0);
			joueur.getMurJoueur().placeTuileSurMur(3, i, joueur, 0);
			joueur.getMurJoueur().placeTuileSurMur(4, i, joueur, 0);
			joueur.getMurJoueur().placeTuileSurMur(5, i, joueur, 0);
		}

		// verif que le choix de ligne est bien plancher lorsque tout le mur est plein (20 fois de suite pour tester le random)
		for (int i = 0; i < 20; i++) {
			joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
			assertEquals(joueur.getIa().getLigne(), 7);    // 7 correspond au plancher
		}
	}

		@Test
		/*
			Test plus approfondie pour le placement des tuiles sur une lignes
		*/
		public void testChoixPlacementAleatoireSurUneLigneApprofondie (){
		/*
			On teste avec une ligne totalement remplie, et 2 lignes avec une ou plusieurs tuiles
			Placement de 2 tuiles bleu sur la ligne 2 (indice 1 dans le tableau) : remplie totalement la ligne
			Placement de 3 tuiles jaune sur la ligne 4 (indice 3 dans le tableau) : remplie partiellement la ligne
			Placement de 1 tuile noir sur la ligne 5 (indice 4 dans le tableau) : remplie partiellement la ligne
		 */
		joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.BLEU, 2, joueur);
		joueur.getLigneMotifJoueur().placeTuileSurLigne(4, Couleurs.JAUNE, 3, joueur);
		joueur.getLigneMotifJoueur().placeTuileSurLigne(5, Couleurs.NOIR, 1, joueur);

		setUpBis();
		fabriques.addFabrique(fabriqueBis);

		for(int i = 0; i < 20; i++) {
			joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
			int ligne = joueur.getIa().getLigne();
			/* Vérifie que l'IA a bien sélectioner la ligne 1 ou 3 */
			assertNotEquals(ligne, 2);
			assertNotEquals(ligne, 4);
			assertNotEquals(ligne, 5);
			joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.BLANC, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
			ligne = joueur.getIa().getLigne();
			assertNotEquals(ligne, 2);
			assertNotEquals(ligne, 4);
			assertNotEquals(ligne, 5);
		}

		setUp();

		for(int i = 0; i < 20; i++) {
			joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.JAUNE, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
			int ligne = joueur.getIa().getLigne();
			/* Vérifie que l'IA a bien sélectioner la ligne 4 ou 5 */
			assertNotEquals(ligne, 2);
			assertNotEquals(ligne, 5);
		}
	}

	@Test
	/*
		Vérifie que l'Ia random pose les tuiles sur une ligne de motif aléatoire
		en fonction des tuiles sur le mur
	 */
	public void testChoixPlacementAleatoireSurUneLigneEnFonctionDuMur (){
		/*
			On teste 2 lignes avec plusieurs tuiles
			Placement de 2 tuiles rouge sur la ligne 3 (indice 2 dans le tableau) : remplie partiellement la ligne
			Placement de 3 tuile jaune sur la ligne 5 (indice 4 dans le tableau) : remplie partiellement la ligne
		 */
		joueur.getLigneMotifJoueur().placeTuileSurLigne(3, Couleurs.ROUGE, 2, joueur);
		joueur.getLigneMotifJoueur().placeTuileSurLigne(5, Couleurs.JAUNE, 3, joueur);

		//Remplie la ligne 1 du mur
		for (int i = 0; i < 5; i+=2) {
			joueur.getMurJoueur().placeTuileSurMur(1, i, joueur, 0);
		}

		//Remplie la ligne 2 du mur
		for (int i = 0; i < 3; i++) {
			joueur.getMurJoueur().placeTuileSurMur(2, i, joueur, 0);
		}

		//Remplie la ligne 4 du mur
		for (int i = 0; i < 3; i++) {
			joueur.getMurJoueur().placeTuileSurMur(4, i, joueur, 0);
		}

		setUpBis();
		//sélectionne une couleur aléatoire de la fabrique (rouge, blanc ou bleu)
		Couleurs couleurSelectionneBis = Couleurs.ROUGE;

		for(int i = 0; i < 20; i++) {
			joueur.getIa().choixPlacementCouleurSurLigne(couleurSelectionneBis, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
			int ligne = joueur.getIa().getLigne();
			/* Vérifie que l'IA a bien sélectioner la ligne 2 et 3 */
			assertNotEquals(ligne, 1);
			assertNotEquals(ligne, 5);
		}
	}

}
