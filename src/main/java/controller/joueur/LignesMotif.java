/**
 *  Classe représentant les lignes de motifs sur lequel l'IA peut placer les tuiles
 *	@author Azul-C group
 *	@date 10/10/2022
 **/
package controller.joueur;

import tuile.Couleurs;
import tuile.Tuile;

public class LignesMotif {
	/* argument 1 : ligne argument 2 : Case de la ligne */
	private final Tuile[][] plateauDeMotif;

	/**
	 * Constructeur par défaut créant le plateau de ligne de motif 
	 */
	public LignesMotif() {
		/* on instancie le tableau de motif qu'on initialise à null*/
		plateauDeMotif = new Tuile[5][];
		for(int i = 0; i < 5; i++) {
			Tuile [] tabTuile = new Tuile[i+1];
			for (int j = 0; j < i + 1; j++) {
				tabTuile[j] = new Tuile();
			}
			this.plateauDeMotif[i] = tabTuile;
		}
	}


	public void restaureLignesMotif() {
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < i + 1; j++) {
				this.plateauDeMotif[i][j].setCouleur(Couleurs.VIDE);
			}
		}
	}

	/**
	 * @return le plateau de motif (accesseur) 
	 */
	public Tuile[][] getPlateauDeMotif() {
		return plateauDeMotif;
	}

	/**
	 * 	@param ligne de la ligne
	 * 	@param numeroCase numéro de la tuile
	 *  @return la tuile en question
	 */
	public Tuile getCase(int ligne, int numeroCase) {
		/* Vérifie que la case ne soit pas "out of bounds" */
		if (numeroCase < ligne && numeroCase >= 0) {
			return plateauDeMotif[ligne - 1][numeroCase];
		} else {
			return null;
		}
	}
	/**
	 * @param ligne ligne de motif
	 * @return le nombre de tuiles dans la ligne
	 */
	public int getNombreDeTuileDansLigne(int ligne) {
		int nbTuile = 0;
		while (nbTuile < ligne && this.plateauDeMotif[ligne - 1][nbTuile].getCouleur() != Couleurs.VIDE)
			nbTuile++;
		return nbTuile;
	}

	/**
	 * @param numeroLigne numéro d'une ligne de motif
	 * @return 1 si la ligne est pleine ou 0 si la ligne n'est pas pleine
	 */
	public int estLignePleine (int numeroLigne) {
		//ligne pleine
		if (getNombreDeTuileDansLigne(numeroLigne) == numeroLigne) {
			return 1;
		}
		// ligne non pleine
		return 0;
	}

	/**
	 * 	@param ligne
	 * 	@param couleurs
	 * 	@param nombreOccurrence
	 *  @param joueur
	 * 	Place la/les tuile(s) d'une couleur sur la ligne, et sur le plancher si il y en a trop
	 */
	public int placeTuileSurLigne(int ligne, Couleurs couleurs, int nombreOccurrence, Joueur joueur) {
		/* On assigne une tuile à une case vide d'une ligne */
		int colonne = 0;
		int nombreTuileRestante = nombreOccurrence;
		while(colonne < ligne && joueur.getLigneMotifJoueur().getPlateauDeMotif()[ligne - 1][colonne].getCouleur()
				!= Couleurs.VIDE ) {
			colonne++;
		}
		if (colonne < ligne){
			/* On place les tuiles sur la ligne motif*/
			while (colonne < ligne && nombreTuileRestante > 0) {
				joueur.getLigneMotifJoueur().getPlateauDeMotif()[ligne - 1][colonne++].setCouleur(couleurs);
				nombreTuileRestante--;
			}
		}
		return nombreTuileRestante;
	}

	public void setLigneMotifJoueur(Couleurs couleur, int i, int j) {
		this.getPlateauDeMotif()[i][j].setCouleur(couleur);
	}
}