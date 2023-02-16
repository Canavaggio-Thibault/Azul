package controller.joueur;
/*
 *  Classe créant le plancher et le gérant
 *	@author Azul-C group
 */

import tuile.Couleurs;
import tuile.Tuile;

public class Plancher {
	private final Tuile[] plancher; // Tableau Plancher qui stocke les tuiles en trop lors du placement des tuiles
	// sur lignesMotif
	/**
	 * Constructeur de la classe Plancher
	 */
	public Plancher() {
		plancher = new Tuile[7];
		for (int i = 0; i < plancher.length; i++) {
			plancher[i] = new Tuile(Couleurs.VIDE);
		}
	}

	public void restaurePlancher(){
		for (int i = 0; i < 7; i++){
			if (this.plancher[i].getCouleur() == Couleurs.VIDE)
				break;
			this.plancher[i].setCouleur(Couleurs.VIDE);
		}
	}

	/**
	 * @return le plancher
	 */
	public Tuile[] getPlancher() {
		return plancher;
	}

	/**
	 * @param couleurs couleur des tuiles a remplir
	 * @param nombreTuile nombre de tuile a placer sur le planché
	 * @return tuiles Remplis le plancher d'une tuile de cette couleur, et return les tuiles en exédant
	 */
	public Tuile[] remplirPlancher(Couleurs couleurs, int nombreTuile) {
		int i = 0;
		while ( i < 7 && plancher[i].getCouleur() != Couleurs.VIDE)
			i++;

		if (i == 7) {
			Tuile[] tuiles = new Tuile[nombreTuile];
			int j = 0;
			while (nombreTuile-- != 0)
				tuiles[j++] = new Tuile(couleurs);
			return tuiles;
		}
		while (i < 7 && nombreTuile-- > 0) {
			plancher[i].setCouleur(couleurs);
			i++;
		}

		if (nombreTuile > 0) {
			Tuile[] tuiles = new Tuile[nombreTuile];
			int j =0;
			while (nombreTuile-- != 0)
				tuiles[j++] = new Tuile(couleurs);

			// return les tuiles en trop du plancher
			return tuiles;
		}
		return null;
	}
}
