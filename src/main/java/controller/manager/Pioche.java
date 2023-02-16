/**
 *  Classe créant la Pioche et la gérant
 *	@author Azul-C group (Thibault CANAVAGGIO)
 **/
package controller.manager;
import tuile.Couleurs;
import tuile.Tuile;
import utilitaire.Utilitaire;

import java.util.*;

public class Pioche {
    private Tuile[] sac;
    private int indexCourant;
    private final Couleurs [] couleurs;

    public Couleurs[] getCouleurs() {
        return couleurs;
    }


    public void setIndexCourant(int i) {
        this.indexCourant = i;
    }
    /**
     * Constructeur de la classe Pioche
     */
    public Pioche() {
        this.indexCourant = 0;
        sac = new Tuile[100];
        couleurs = Utilitaire.getTab();
        int k = 0;
        for(int i = 0; i < 100; i++){
            k = (i % 20 == 0 && i != 0) ? k + 1 : k;
            sac[i] = new Tuile(couleurs[k]);
        }
        this.melangerSac();
    }

    /**
     *méthode qui permet de piocher une tuile dans la pioche
     * @return une couleur de tuile
     */
    public Couleurs piochage(){
        if (this.indexCourant > 99)
            return Couleurs.VIDE;
        while(sac[this.indexCourant].getCouleur() == Couleurs.VIDE) {
            indexCourant++;
        }
        Couleurs couleur = sac[this.indexCourant].getCouleur();
        sac[this.indexCourant].setCouleur(Couleurs.VIDE);
        this.indexCourant++;
        return (couleur);
    }
    /**
     * @return la Pioche
     */
    public Tuile[] getPioche() { return sac;}

    public void melangerSac() {
        List<Tuile> list = Arrays.asList(sac);
        Collections.shuffle(list);
        list.toArray(sac);
    }

    /**
     * @param couleur une couleur
     * @return le nombre de tuiles de la même couleur dans la Pioche
     */
    public int nombreOccurrenceCouleur(Couleurs couleur) {
        int nombre = 0;
        for (int i = 0; i < 100; i++) {
            if (sac[i].getCouleur() == couleur) {
                nombre++;
            }
        }
        return nombre;
    }

    /**
     *méthode qui permet de restaurer la pioche
     */
    public void restaurePioche() {
        this.indexCourant = 0;
        int k = 0;
        for(int i = 0; i < 100; i++){
            k = (i % 20 == 0 && i != 0) ? k + 1 : k;
            sac[i].setCouleur(couleurs[k]);
        }
        this.melangerSac();
    }

    public void viderPioche() {
        for (int i = 0; i < 100; i++) {
            sac[i].setCouleur(Couleurs.VIDE);
        }
    }
}