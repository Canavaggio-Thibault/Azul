/**
 * Cette classe permet de Créer des tuiles de couleur aléatoire
 */
package utilitaire;

import tuile.Couleurs;
import tuile.Tuile;

import java.security.SecureRandom;

public class Utilitaire {

	private static SecureRandom r;

	private static Couleurs[] tab = { Couleurs.BLEU, Couleurs.JAUNE, Couleurs.ROUGE, Couleurs.NOIR, Couleurs.BLANC };

	public static Couleurs[] getTab() {
		return tab;
	}

	public Utilitaire() {
	}

	public static int getValue(int value){
		r = new SecureRandom();
		return r.nextInt(value);
	}

	public static Tuile getRandom() {
		return new Tuile(tab[getValue(5)]);
	}

	public static SecureRandom getR() {
		r = new SecureRandom();
		return r;
	}
	public static Couleurs getCouleur() {
		return tab[getValue(5)];
	}

}
