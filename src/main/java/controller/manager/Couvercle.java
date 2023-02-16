package controller.manager;
/*
 * Classe permettant de creer le couvercle et de le gerer
 */

import controller.joueur.Joueur;
import tuile.Couleurs;
import tuile.Tuile;
import controller.manager.Manager;
import utilitaire.AfficherElement;

import java.util.*;


public class Couvercle {

    private final Tuile[] couvercle;
    private int indexCourant;

    public void setCoupole(Couleurs couleur) {
        if (this.indexCourant < 100)
            this.couvercle[indexCourant++].setCouleur(couleur);
    }

    /**
     * Constructeur de la classe Couvercle
     */
    public Couvercle() {
        this.indexCourant = 0;
        couvercle = new Tuile[100];
        for (int i = 0; i < couvercle.length; i++) {
            couvercle[i] = new Tuile(Couleurs.VIDE);
        }
    }

    /**
     * @param tuiles methode permettant de remplir le couvercle
     */
    public void remplirCouvercle(Tuile[] tuiles) {
        if (tuiles == null || tuiles.length == 0)
            return;
        /* Parcours du tableau de tuiles */
        int index = 0;
        /* Remplit les premières cases du vide du couvercle */
        for (Tuile tuile : couvercle) {
            if (tuile.getCouleur() == Couleurs.VIDE) {
                tuile.setCouleur(tuiles[index].getCouleur());
            }
            if (++index >= tuiles.length) {
                return;
            }
        }
    }

    /**
     * @return le Couvercle
     */
    public Tuile[] getCouvercle() {
        return couvercle;
    }

    /**
     * @param couleur une couleur
     * @return le nombre de tuile de la même couleur dans le Couvercle
     */

    public int nombreOccurenceCouleurCouvercle(Couleurs couleur) {
        int nombre = 0;
        for (int i = 0; i < 100; i++) {
            if (couvercle[i].getCouleur() == couleur) {
                nombre++;
            }
        }
        return nombre;
    }

    /**
     * @param plancher une couleur
     *                 méthode permettant de remettre le plancher dans le couvercle
     */
    public void remettrePlancherDansCouvercle(Tuile[] plancher) {
        for (int i = 0; i < plancher.length; i++) {
            if (plancher[i].getCouleur() == Couleurs.JETON){
                plancher[i].setCouleur(Couleurs.VIDE);
            }
        }
        for (int j = 0; j < 7; j++) {
            if (plancher[j].getCouleur() != Couleurs.VIDE) {
                if (this.indexCourant == 100)
                    return;
                couvercle[indexCourant++].setCouleur(plancher[j].getCouleur());
                plancher[j].setCouleur(Couleurs.VIDE);
            }
        }
        for (int i = 0; i < plancher.length; i++) {
            plancher[i].setCouleur(Couleurs.VIDE);
        }

    }

    /**
     * méthode permettant de melanger le couvercle
     */
    public void melangerCouvercle() {
        List<Tuile> list = Arrays.asList(this.couvercle);
        Collections.shuffle(list);
        list.toArray(this.couvercle);
    }

    /**
     * methode permettant de remplir la pioche avec le couvercle
     *
     * @param pioche
     * @return vrai si la pioche est vide
     */
    public boolean remplirPiocheAvecCouvercle(Pioche pioche) {
        int index = 0;
        boolean done = false;
        this.melangerCouvercle();
        for (int i = 0; i < 100; i++) {
            if (this.couvercle[i].getCouleur() != Couleurs.VIDE) {
                pioche.getPioche()[index++].setCouleur(couvercle[i].getCouleur());
                done = true;
            }
        }
        pioche.setIndexCourant(0);
        restaureCouvercle();
        return done;
    }


    /**
     * méthode permettant de remplir le couvercle avec l'excedant de la ligne de motif
     * @param couleurs
     * @param ligne
     */
    public void remplirCouvercleParExcedentLM(Couleurs couleurs, int ligne) {
        for (int i = 0; i < ligne - 1; i++) {
            if (this.indexCourant == 100)
                return;
            this.couvercle[this.indexCourant++].setCouleur(couleurs);
        }
    }
    /**
     * methode qui permet de restaurer le couvercle a la fin d'une partie
     */
    public void restaureCouvercle() {
        this.indexCourant = 0;
        for (Tuile tuile : couvercle) {
            tuile.setCouleur(Couleurs.VIDE);
        }
    }
}