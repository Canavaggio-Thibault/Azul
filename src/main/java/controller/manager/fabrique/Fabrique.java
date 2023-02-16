package controller.manager.fabrique;

import controller.manager.CentreDeTable;
import controller.manager.Pioche;
import tuile.Couleurs;
import tuile.Tuile;

public class Fabrique {

    private final Tuile[] fabrique;

    public Fabrique(Pioche pioche) {
        this.fabrique = new Tuile[4];
        for (int j = 0; j < 4; j++) {
            fabrique[j] = new Tuile(pioche.piochage()); // On génère une tuile dans la case i du tableau
        }
    }

    public Fabrique(Couleurs ... couleurs) {
        fabrique = new Tuile[couleurs.length];
        for (int j = 0; j < couleurs.length; j++) {
            Tuile tuile = new Tuile(couleurs[j]);
            fabrique[j] = tuile; // On génère une tuile dans la case i du tableau
        }
    }

    public Tuile[] getFabrique() {
        return fabrique;
    }

    public void viderFabrique(Couleurs couleurs, CentreDeTable centreDeTable) {
        int j = 0;
        int compteur = 0;
        /* Compte le nombre de tuile de la bonne couleur dans la fabrique */
        for (int i = 0; i < 4; i++) {
            if (fabrique[i].getCouleur() != couleurs)
                compteur = compteur + 1;
        }
        /* Remplie le centre avec les tuiles de la fabrique d'une couleur différente */
        Tuile[] temp = new Tuile[compteur];
        for (int i = 0; i < 4; i++) {
            if (fabrique[i].getCouleur() != couleurs) {
                temp[j++] = new Tuile(fabrique[i].getCouleur());
            }
            /* Vide la fabrique */
            fabrique[i].setCouleur(Couleurs.VIDE);
        }
        centreDeTable.remplirCentre(temp);
    }

    public boolean restoreFabrique(Pioche pioche) {
        boolean done = false;
        for (int j = 0; j < 4; j++) {
            Couleurs couleur = pioche.piochage();
            if (couleur == Couleurs.VIDE) {
                return done;
            }
            fabrique[j].setCouleur(couleur);
            done = true;
        }
        return true;
    }

    public boolean verifieSiFabriqueVide(){
        for (Tuile tuile: fabrique) {
            if(tuile.getCouleur() != Couleurs.VIDE) {
                return false;
            }
        }
        return true;
    }

    public int nombreOccurrenceCouleur(Couleurs couleurs){
        int nb = 0;
        for(Tuile t: fabrique){
            if (t.getCouleur() == couleurs)
                nb++;
        }
        return nb;
    }
}
