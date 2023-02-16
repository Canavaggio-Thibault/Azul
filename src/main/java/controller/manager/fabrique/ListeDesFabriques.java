/**
 *  Classe créant la fabrique et la gérant
 *	@author Azul-C group
 **/

package controller.manager.fabrique;

import controller.manager.CentreDeTable;
import controller.manager.Pioche;
import tuile.Tuile;
import utilitaire.AfficherElement;
import tuile.Couleurs;

import java.util.LinkedList;
import java.util.ListIterator;

public class ListeDesFabriques {

	private final LinkedList<Fabrique> listFabrique;

	private ListIterator<Fabrique> listIterator;

	public LinkedList<Fabrique> getListFabrique() {
		return listFabrique;
	}

	public ListeDesFabriques(int nombreJoueur, Pioche pioche) {
		listFabrique = new LinkedList<>();
		this.listIterator = listFabrique.listIterator(0);
		for (int i = 0; i < nombreJoueur * 2 + 1; i++) {
			addFabrique(new Fabrique(pioche));
		}
	}

	public ListeDesFabriques(int nombreFabrique, Fabrique ... fabrique) {
		listFabrique = new LinkedList<>();
		this.listIterator = listFabrique.listIterator(0);
		for (int i = 0; i < nombreFabrique; i++) {
			addFabrique(fabrique[i]);
		}
	}

	public void addFabrique(Fabrique tabTuiles) {
		listFabrique.add(tabTuiles);
	}

	/**
	 * Reroll la fabrique avec des nouvelles tuiles de couleur
	 */
	public boolean restoreListeFabriques(Pioche pioche,int affichage) {
		boolean done = false;
		for (Fabrique fabrique: listFabrique){
			if (!fabrique.restoreFabrique(pioche))
				return done;
			done = true;
		}
		pioche.setIndexCourant(0);
		AfficherElement.affichageDelaPioche(pioche,affichage);
		return true;
	}

	/**
	 * @param couleurs
	 * Les tuiles d'une couleur différents sont placées au centre de la table.
	 */
	public void viderFabriqueList(Couleurs couleurs, int index, CentreDeTable centreDeTable) {
		this.listIterator = listFabrique.listIterator(index);
		Fabrique fabrique = ((this.listIterator.hasNext()) ? this.listIterator.next() : null);
		assert fabrique != null;
		fabrique.viderFabrique(couleurs, centreDeTable);

	}



	/**
	 * @param  couleurs
	 * @param index
	 * @return le nombre de tuile de cette couleur dans la fabrique
	 */
	public int nombreOccurrenceCouleurList(Couleurs couleurs, int index){
		this.listIterator = listFabrique.listIterator(index);
		Fabrique fabrique = ((this.listIterator.hasNext()) ? this.listIterator.next() : null);
		assert fabrique != null;
		return fabrique.nombreOccurrenceCouleur(couleurs);
	}

	public boolean verifieSiListeFabriqueVide(){
		ListIterator<Fabrique> listIterator = listFabrique.listIterator(0);
		while (listIterator.hasNext()){
			Fabrique fabrique = listIterator.next();
			if (!fabrique.verifieSiFabriqueVide())
				return false;
		}
		return true;
	}
}
