package ia.typeIA;


import controller.joueur.Joueur;
import controller.joueur.LignesMotif;
import controller.joueur.Mur;
import controller.joueur.TypeMur;
import controller.joueur.typeMur.MurCouleur;
import controller.manager.CentreDeTable;
import controller.manager.Manager;
import controller.manager.Pioche;
import controller.manager.fabrique.Fabrique;
import controller.manager.fabrique.ListeDesFabriques;
import ia.TypeIa;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import tuile.Tuile;
import utilitaire.AfficherElement;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IAintermediaireTest {

	ListeDesFabriques fabriques;
	Joueur joueur = new Joueur(1, TypeIa.IA_INTERMEDIARE,TypeMur.MUR_COULEUR);
	CentreDeTable centreDeTable = new CentreDeTable();
	Pioche pioche;
	LignesMotif lignesMotif = new LignesMotif();
	Mur mur = new MurCouleur();
	
	
	//set up les variables
	public void setUp () {
		/* Création des objets permettant le test */
		this.fabriques = new ListeDesFabriques(2, new Pioche());
		Manager manager = new Manager(1, TypeMur.MUR_COULEUR);
		Tuile[] tabTuile= {new Tuile(Couleurs.JAUNE), new Tuile(Couleurs.BLEU),
				   new Tuile(Couleurs.VIDE), new Tuile(Couleurs.JAUNE),
				   new Tuile(Couleurs.ROUGE)};
		centreDeTable.remplirCentre(tabTuile);
		this.pioche = new Pioche();
	}
	
	/**
	 * Vérifie que l'IA choisit bien la couleur la plus présente sur le centre 
	 * (centre : 2 jaunes, 1 bleu, 1 rouge, et le reste en tuiles vides)
	 */
	@Test
	public void choixCouleurPlusPresenteDansCentre1 () {
		setUp();
		joueur.getIa().choixCouleurDansCentre(centreDeTable, joueur.getMurJoueur());
		Couleurs couleurChoisie = joueur.getIa().getChoixCouleur();
		assertEquals(Couleurs.JAUNE, couleurChoisie);
	}
	
	/**
	 * Vérifie que l'IA choisit bien la couleur la plus présente sur le centre 
	 * (centre : 2 jaunes, 3 bleu, 1 rouge, et le reste en tuiles vides)
	 */
	@Test
	public void choixCouleurPlusPresenteDansCentre2 () {
		setUp();
		Tuile[] tabTuile= {new Tuile(Couleurs.BLEU), new Tuile(Couleurs.BLEU)}; //rajoute deux tuiles bleu sur le centre deja set up
		centreDeTable.remplirCentre(tabTuile);
		
		joueur.getIa().choixCouleurDansCentre(centreDeTable, joueur.getMurJoueur());
		Couleurs couleurChoisie = joueur.getIa().getChoixCouleur();
		assertEquals(Couleurs.BLEU, couleurChoisie);
	}
	
	@Test 
	/**
	 * Vérifie que l'IA choisie l'endroit avec le plus d'occurence d'une même couleur
	 * 3 fabriques avec 4 tuiles différentes, 1 fabriques avec 4 jaunes, centre avec 3 bleu
	 */
	public void choixFabriqueOuCentre () {
		setUp();
		fabriques = new ListeDesFabriques(2,pioche);
		LinkedList<Fabrique> list = fabriques.getListFabrique();
		Couleurs[] fabriqueJaune = {Couleurs.JAUNE, Couleurs.JAUNE, Couleurs.JAUNE, Couleurs.JAUNE};
		fabriques.addFabrique(new Fabrique(fabriqueJaune));
		
		joueur.getIa().choixFabriqueOuCentre(centreDeTable, fabriques, mur);
		int choixFabriqueOuCentre = joueur.getIa().getChoixFabriqueOuCentre();
		// l'IA doit renvoyer fabrique (0) car une fabrique a 4 jaunes et le centre 2 jaunes
		assertEquals(0, choixFabriqueOuCentre);
		
		Tuile[] addCentre = {new Tuile(Couleurs.JAUNE), new Tuile(Couleurs.JAUNE)};
		centreDeTable.remplirCentre(addCentre);
		
		//Centre : centre 4 jaunes, 3 fabriques random et 1 fabrique avec 4 jaune, l'IA doit retourner fabrique
		joueur.getIa().choixFabriqueOuCentre(centreDeTable, fabriques, mur);
		choixFabriqueOuCentre = joueur.getIa().getChoixFabriqueOuCentre();
		assertEquals(0, choixFabriqueOuCentre);
		
		//Centre : 6 jaunes, 3 fabriques random et 1 fabrique avec 4 jaune, l'IA doit retourner centre 
		Tuile[] addCentre2 = {new Tuile(Couleurs.JAUNE), new Tuile(Couleurs.JAUNE)};
		centreDeTable.remplirCentre(addCentre2);
		joueur.getIa().choixFabriqueOuCentre(centreDeTable, fabriques, mur);
		choixFabriqueOuCentre = joueur.getIa().getChoixFabriqueOuCentre();
		assertEquals(0, choixFabriqueOuCentre);
	}
	
	
	@Test
	/**
	 * Vérifie que l'IA intermediaire choisit des lignes correctement
	 */

	public void choixPlacementLigneMotif () {
		setUp();
		// L'ia veut poser 3 tuiles noir, théoriquement sur la ligne 3 qui a 3 cases vides
		joueur.getIa().setNombreTuileAPoser(3);
		joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, lignesMotif, mur);
		int choixLigne = joueur.getIa().getLigne();
		assertEquals(3, choixLigne);

		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 0, 0);
		
		// ligne 1 pleine, vérifie que l'IA ne pose pas dans la ligne 1 même en piochant 1 rouge
		// choix attendue : ligne 2 (1ere ligne plus grande dispo après la ligne 1)
		joueur.getIa().setNombreTuileAPoser(1);
		joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, lignesMotif, mur);
		choixLigne = joueur.getIa().getLigne();

		assertEquals(2, choixLigne);
		
		//ligne 4 a 1 tuiles rouges
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 3, 0);
		
		// choix attendu de l'IA : ligne 4 car une tuile rouge est déjà posée et la ligne n'est pas pleine
		joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, lignesMotif, mur);
		choixLigne = joueur.getIa().getLigne();
		assertEquals(4, choixLigne);
		
		// L'IA doit choisir où poser 4 tuiles, la ligne 4 finiriat dans le plancher donc ligne 5 attendue
		joueur.getIa().setNombreTuileAPoser(4);
		
		
		//ligne 4 a 2 tuiles rouges
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 3, 1);
		
		int nbAPoser = joueur.getIa().getNombreTuileAPoser();
		joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, lignesMotif, mur);
		choixLigne = joueur.getIa().getLigne();
		assertEquals(4, choixLigne);

		// reset des lignes de motif
		lignesMotif = new LignesMotif();
		// remplissage ligne 5
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 4, 0);
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 4, 1);
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 4, 2);
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 4, 3);
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 4, 4);
		// une tuile rouge sur la ligne 4
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 3, 0);
		
		// L'Ia veut poser 4 tuiles jaunes, la ligne 4 n'ayant pas la place elle choisit alors la ligne 3
		joueur.getIa().setNombreTuileAPoser(4);
		joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, lignesMotif, mur);
		choixLigne = joueur.getIa().getLigne();
		assertEquals(4, choixLigne);
		
		// une tuile rouge sur la ligne 3, choix attendue ligne 2
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 2, 0);
		joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, lignesMotif, mur);
		choixLigne = joueur.getIa().getLigne();
		assertEquals(4, choixLigne);
		
		
		// on remplit toutes les lignes avec une tuiles, il n'y a la place nul part de placer 4 tuiles
		// l'ia doit retourner le plancher (7)
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 1, 0);
		lignesMotif.setLigneMotifJoueur(Couleurs.ROUGE, 0, 0);
		joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, lignesMotif, mur);
		choixLigne = joueur.getIa().getLigne();
		assertEquals(4, choixLigne);
	}

}
