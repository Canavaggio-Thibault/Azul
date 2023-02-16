package controller.joueur;
/**
 *
 */

import controller.joueur.typeMur.MurCouleur;
import controller.joueur.typeMur.MurGris;
import controller.joueur.typeMur.MurVariante;
import ia.IA;
import ia.typeIA.IArandom;
import ia.typeIA.IAsuperieur;
import ia.TypeIa;
import ia.typeIA.IAintermediaire;
import utilitaire.CompterPoint;

public class Joueur {

    private final String nomJoueur;
    private final LignesMotif ligneMotifJoueur;
    private Mur murJoueur;
    private final Plancher plancherJoueur;

    private final CompterPoint compterPoint;
    private int nombreDeVictoire;

    private int nombreDeTour;
    private int nombreDeManche;
    private int nombreDeLigneRemplis;
    private int nombreDeLignesRemplisPartie;

    private int nbPointMalus;
    public int getNbPointMalus(){return nbPointMalus;}

    public int getNombreDeLigneRemplis(){return nombreDeLigneRemplis;}
    public void incrementeNombreDeMalis(){this.nbPointMalus++;}
    public void incrementeNombreDeLignesRemplisPartie(){this.nombreDeLignesRemplisPartie++;}
    public void rebootTemoinNombreDeLignesRemplisPartie(){this.nombreDeLignesRemplisPartie=0;}
    public int getNombreDeLignesRemplisPartie(){return nombreDeLignesRemplisPartie;}

    public void incrementeNombreDeLigneRemplis(){this.nombreDeLigneRemplis++;}

    public CompterPoint getCompterPoint() {
        return compterPoint;
    }

    public int getNombreDeManche() {return nombreDeManche;}
    public void incrementeNombreDeVictoire(){this.nombreDeVictoire++;}

    public void incrementeNombreDeManche(){this.nombreDeManche++;}
    public void incrementeNombreDeTours(){this.nombreDeTour++;}
    public int getNombreDeTours() {return nombreDeTour;}
    public int getNombreDeVictoire() {
        return nombreDeVictoire;
    }

    private IA ia;

    public String getNomJoueur() {
        return nomJoueur;
    }

    public LignesMotif getLigneMotifJoueur() {
        return ligneMotifJoueur;
    }

    public Mur getMurJoueur() {
        return murJoueur;
    }

    public Plancher getPlancherJoueur() {
        return plancherJoueur;
    }

    public IA getIa() {
        return ia;
    }
    
    public Joueur(int id, TypeIa typeIa, TypeMur Mur){
        nomJoueur = "Joueur" + id;
        ligneMotifJoueur = new LignesMotif();
        plancherJoueur = new Plancher();
        compterPoint = new CompterPoint();

        switch (typeIa){
            case IA_RANDOM -> {
                ia = new IArandom();
            }
            case IA_SUPERIEUR -> {
                ia = new IAsuperieur();
            }
            case IA_INTERMEDIARE -> {
                ia = new IAintermediaire();
            }
        }

        switch (Mur){
            case MUR_COULEUR -> {
                murJoueur = new MurCouleur();
            }
            case MUR_GRIS -> {
                murJoueur = new MurGris();
            }
            case MUR_VARIANTE -> {
                murJoueur = new MurVariante();
            }
        }
    }
}
