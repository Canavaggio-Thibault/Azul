package controller.manager;
/**
 * Cette classe permet de créer le centre de ta table et de récupérer les tuiles de même couleurs présente
 * à l'intérieur 
 */

import tuile.Tuile;
import tuile.Couleurs;

public class CentreDeTable {
	private final Tuile[] centreDeLaTable;

	private final Couleurs jeton= Couleurs.JETON;

	/**
	 *  constructeur pour le centre de la table
	 */
	public CentreDeTable () {
		centreDeLaTable = new Tuile[7*3]; // 7 fabriques de 3 tuiles max 
		for (int i = 0; i < centreDeLaTable.length; i++) {
			centreDeLaTable[i] = new Tuile (Couleurs.VIDE);
		}
	}

	/**
	 * @param numeroTuile le numero de la tuile
	 * @return la tuile de la couleur donnée dans le centre de la table 
	 */
	public Tuile getTuile (int numeroTuile) {
		return centreDeLaTable[numeroTuile];
	}

	/**
	 * @return le centre de la table
	 */

	public Tuile[] getCentre () {
		return centreDeLaTable;
	}

	public void mettreJetonAuCentre () {
		centreDeLaTable[0] = new Tuile (jeton);
	}

	/**
	 * @param tuiles un tableau de tuile et les places au centre de la table
	 */
	public void remplirCentre (Tuile[] tuiles) {
		/* Parcours du tableau de tuiles */
		int parcours = 0;
		/* Replit les premières cases vide du centre de la table */
		if (tuiles == null ||tuiles.length == 0)
			return;
		for (int i = 0; i < centreDeLaTable.length; i++) {
			if (centreDeLaTable[i].getCouleur() == Couleurs.VIDE ) {
				centreDeLaTable[i] = tuiles[parcours++];
			}
			/** On sort de la méthode quand on a fini de placer toutes les tuiles */
			if (parcours == tuiles.length) {
				return;
			}
		}
	}

	/**
	 * méthode qui permet de verifier si le centre est vide
	 */
	public boolean verifieSiCentretableVide(){
		for (int i = 0; i < getCentre().length; i++) {
			if (getCentre()[i].getCouleur() != Couleurs.VIDE && getCentre()[i].getCouleur() != jeton) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 	@param couleur une couleure qui va être piochée du centre
	 *  @return toutes les tuiles de même couleur et les enlève du centre de la table 
	 */
	public Tuile[] getTuileMemeCouleur (Couleurs couleur) {
		/* Initialise le tableau de tuile qui va être renvoyer ainsi que sa variable de parcours*/
		Tuile[] tuilesReturn;
		int parcours = 0;

		/* Boucle permettant de definir la taille du tableau de retour a creer */
		int compteurTailleTabReturn = 0;
		/* Parcours le centre de la table */
		for (Tuile tuile : centreDeLaTable) {
			/* Recherche les tuiles de même couleur qu'en paramètre */
			if (tuile.getCouleur() == couleur) {
				/* Incremente le compteur */
				compteurTailleTabReturn++;
			}
		}

		tuilesReturn = new Tuile[compteurTailleTabReturn];
		for (Tuile tuile : centreDeLaTable) {
			/* Recherche les tuiles de même couleur qu'en paramètre */
			if (tuile.getCouleur() == couleur) {
				/* Remplit le tableau qui va être return avec le nombre de tuile identique */
				tuilesReturn[parcours] = new Tuile(couleur);
				/* Affecte la couleur VIDE à la tuile piochée du centre */
				tuile.setCouleur(Couleurs.VIDE);
				parcours++;
			}
		}
		return tuilesReturn;
	}

	/**
	 * methode qui permet de restaurer le centre de la table
	 */
    public void restaureCentreDeTable() {
		for (Tuile tuile : centreDeLaTable) {
			tuile.setCouleur(Couleurs.VIDE);
		}
    }
}


