package ia.typeIA;
/*
L'ia superieur correspond a l'IA dont sa strategie est de remplir la premiere ligne du mur le
plus rapidement possible
 */
import controller.manager.CentreDeTable;
import controller.manager.fabrique.Fabrique;
import controller.manager.fabrique.ListeDesFabriques;
import ia.IA;
import ia.TypeIa;
import tuile.Couleurs;
import controller.joueur.LignesMotif;
import controller.joueur.Mur;
import tuile.Tuile;

import java.util.LinkedList;
import java.util.ListIterator;

public class IAsuperieur extends IA {

    /**
     * Retourne le type de l'IA
     * @return typeIa.IA_SUPERIEUR
     */
    public TypeIa getType() {
        return TypeIa.IA_SUPERIEUR;
    }


    /**
     * Defini le nombre de tuile a poser
     * @param nbTuilesAPoser le nombre de tuile a poser
     */
    public void setNombreTuileAPoser(int nbTuilesAPoser) {
        nombreTuileAPoser = nbTuilesAPoser;
    }

    /**
     * Fais le choix entre fabrique et centre
     * @param centreDeTable centre de la table
     * @param fabriques liste des fabriques
     */
    public void choixFabriqueOuCentre(CentreDeTable centreDeTable, ListeDesFabriques fabriques, Mur mur) {
        boolean centreVide = true;
        boolean uniquementjeton = false;
        // vérifie que le centre n'est pas vide
        for (int i = 0; i < centreDeTable.getCentre().length; i++) {//parcours du centre
            if (centreDeTable.getCentre()[i].getCouleur() != Couleurs.VIDE) {//verifie que centre pas vide
                if (centreDeTable.getCentre()[i].getCouleur() == Couleurs.JETON) {//verifie que la cas == jeton
                    uniquementjeton = true;
                    for (int j = i; j < centreDeTable.getCentre().length; j++) {//parcours du reste du centre si le jeton est au centre
                        if (centreDeTable.getCentre()[i].getCouleur() != Couleurs.VIDE) {//si une couleur est trouvé
                            uniquementjeton = false;
                        }
                    }
                } else {
                    centreVide = false;
                    i = centreDeTable.getCentre().length;
                }
            }
        }

        if (centreVide || uniquementjeton) {
            choixFabriqueOuCentre = 0;
            return;
        }
        // retourne le centre s'il n'est pas vide pour ne pas piocher trop de tuile

        choixFabriqueOuCentre = 1;
    }


    /**
     * Fais le choix de la couleur dans une fabrique en prennant en compte le mur
     * @param fabriques liste des fabriques
     * @param indexDansFabrique index de la fabrique
     * @param mur le mur du joueur
     */
    public void choixCouleurDansFabrique(ListeDesFabriques fabriques, int indexDansFabrique, Mur mur) {
        // recuperation des fabriques
        int[] occCouleurs = new int[5];
        LinkedList<Fabrique> list = fabriques.getListFabrique();
        ListIterator<Fabrique> iter = list.listIterator(0);
        int i = 0;
        // recuperation de la fabrique
        Tuile[] tuiles = new Tuile[4] ;
        while (iter.hasNext()){

            tuiles = iter.next().getFabrique();

            while(tuiles[0].getCouleur() == Couleurs.VIDE) {
                tuiles = iter.next().getFabrique();
                i++;
            }
            choixCouleur(tuiles, occCouleurs, mur);
            choixFabrique = i;
            return;
        }

    }

    /**
     * @param centreDeTable le centre de la table
     * Choisit une couleur dans le centre (le moins de tuile possible pour ne pas surcharger la ligne 1 du mur)
     */
    public void choixCouleurDansCentre(CentreDeTable centreDeTable, Mur mur) {
        int[] occCouleurs = new int[5];
        choixCouleur(centreDeTable.getCentre(), occCouleurs, mur);
    }

    /**
     * Choisit la ligne de motif adaptee pour la strategie de remplir le mur au plus vite
     * @param couleurDeLaTuile couleur de la tuile choisie
     * @param plateauDeMotif lignes de motif du joueur
     * @param mur mur du joueur
     */
    public void choixPlacementCouleurSurLigne(Couleurs couleurDeLaTuile, LignesMotif plateauDeMotif, Mur mur) {
        if (nombreTuileAPoser == 0) {
            choixLigne = 0;
            return;
        }
        // =-=-=-=-=- cherche une ligne qui a déjà cette couleur de posee (non pleine) et libre sur le mur =-=-=-=-=-=-=
        // parcours toutes les lignes de motif
        for (int i = 0; i < 5; i++) {
            // regarde si une ligne n'a pas la couleur choisit sur la 1ere case
            if (plateauDeMotif.getPlateauDeMotif()[i][0].getCouleur() == couleurDeLaTuile) {
                // regarde si la derniere case de la ligne est vide
                if (plateauDeMotif.getPlateauDeMotif()[i][i].getCouleur() != couleurDeLaTuile) {
                    // alors la ligne i n'est pas pleine et possede au moins une tuile de la couleur choisie
                    // on pose les tuiles sur cette ligne
                    choixLigne = (i+1);
                    return;
                }
            }
        }

        // =-=-=-=-= pose les tuiles dans l'ordre croissant des lignes de motif =-=-=-=-=-=
        // parcours les lignes de motif
        for (int i = 0; i < 5; i++) {
            // verifie que la ligne i est libre
            if (plateauDeMotif.getPlateauDeMotif()[i][0].getCouleur() == Couleurs.VIDE) {
                // verifie que la couleur n'est pas deja remplis sur le mur
                if (!mur.couleurDejaPoserLigne(i+1, couleurDeLaTuile)) {
                    choixLigne = (i+1);
                    return;
                }

            }
        }
        choixLigne = 7;
    }

    /**
     * regroupe tous les choix de l'Ia en fonction de tous les paramètres
     * @param fabriques la liste des fabriques
     * @param lignesMotif les lignes de motifs
     * @param centreDeTable le centre de la table
     * @param mur le mur
     * @return choixIA
     */
    public IA choixIA(ListeDesFabriques fabriques, LignesMotif lignesMotif, CentreDeTable centreDeTable, Mur mur) {
        choixFabrique = 0;
        choixCouleur = null;
        choixFabriqueOuCentre(centreDeTable, fabriques, mur);
        if (choixFabriqueOuCentre == 0) {
            choixCouleurDansFabrique(fabriques, choixFabrique, mur);
        }
        else
            choixCouleurDansCentre(centreDeTable, mur);
        choixPlacementCouleurSurLigne(choixCouleur, lignesMotif, mur);
        return this;
    }

    public Couleurs compteCouleur (Tuile[] tabTuiles, int[] tabCompte) {
        // compte les couleurs
        for (Tuile tabTuile : tabTuiles) {
            if (tabTuile.getCouleur() == Couleurs.ROUGE)
                tabCompte[0]++;
            if (tabTuile.getCouleur() == Couleurs.BLEU)
                tabCompte[1]++;
            if (tabTuile.getCouleur() == Couleurs.JAUNE)
                tabCompte[2]++;
            if (tabTuile.getCouleur() == Couleurs.NOIR)
                tabCompte[3]++;
            if (tabTuile.getCouleur() == Couleurs.BLANC)
                tabCompte[4]++;
        }
        int indexMax = 0;
        int max = 0;
        // trouve quelle couleur est la plus present
        for (int i = 0; i < 5; i++) {
            if (tabCompte[i] > max) {
                max = tabCompte[i];
                indexMax = i;
            }
        }

        this.nombreTuileAPoser = tabCompte[indexMax];
        // retourne la couleur la plus present
        if (indexMax == 0) {
            return Couleurs.ROUGE;
        }
        if (indexMax == 1) {
            return Couleurs.BLEU;
        }
        if (indexMax == 2) {
            return Couleurs.JAUNE;
        }
        if (indexMax == 3) {
            return Couleurs.NOIR;
        }
        return Couleurs.BLANC;
    }

    public void choixCouleur (Tuile[] tuiles, int[] occCouleurs, Mur mur) {
        Couleurs couleurMax = compteCouleur(tuiles, occCouleurs);

        // retourne couleur si le mur ligne 1 n'est pas remplit avec
        if (occCouleurs[0] == 1) {
            if (!mur.couleurDejaPoserLigne(1, Couleurs.ROUGE)) {
                choixCouleur = Couleurs.ROUGE;
                nombreTuileAPoser = 1;
                return;
            }
        }
        if (occCouleurs[1] == 1) {
            if (!mur.couleurDejaPoserLigne(1, Couleurs.BLEU)) {
                choixCouleur = Couleurs.BLEU;
                nombreTuileAPoser = 1;
                return;
            }
        }
        if (occCouleurs[2] == 1) {
            if (!mur.couleurDejaPoserLigne(1, Couleurs.JAUNE)) {
                choixCouleur = Couleurs.JAUNE;
                nombreTuileAPoser = 1;
                return;
            }
        }
        if (occCouleurs[3] == 1) {
            if (!mur.couleurDejaPoserLigne(1, Couleurs.NOIR)) {
                choixCouleur = Couleurs.NOIR;
                nombreTuileAPoser = 1;
                return;
            }
        }
        if (occCouleurs[4] == 1) {
            if (!mur.couleurDejaPoserLigne(1, Couleurs.BLANC)) {
                choixCouleur = Couleurs.BLANC;
                nombreTuileAPoser = 1;
                return;
            }
        }

        // cas où il a 2 et 2 d'une même couleur
        if (occCouleurs[0] == 2) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.ROUGE)) {
                choixCouleur = Couleurs.ROUGE;
                nombreTuileAPoser = 1;
                return;
            }
        }

        if (occCouleurs[1] == 2) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.BLEU)) {
                choixCouleur = Couleurs.BLEU;
                nombreTuileAPoser = 1;
                return;
            }
        }
        if (occCouleurs[2] == 2) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.JAUNE)) {
                choixCouleur = Couleurs.JAUNE;
                nombreTuileAPoser = 2;
                return;
            }
        }
        if (occCouleurs[3] == 2) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.NOIR)) {
                choixCouleur = Couleurs.NOIR;
                nombreTuileAPoser = 2;
                return;
            }
        }
        if (occCouleurs[4] == 2) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.BLANC)) {
                choixCouleur = Couleurs.BLANC;
                nombreTuileAPoser = 2;
                return;
            }
        }

        if (occCouleurs[0] == 1) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.ROUGE)) {
                choixCouleur = Couleurs.ROUGE;
                nombreTuileAPoser = 1;
                return;
            }
        }
        if (occCouleurs[1] == 1) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.BLEU)) {
                choixCouleur = Couleurs.BLEU;
                nombreTuileAPoser = 1;
                return;
            }
        }
        if (occCouleurs[2] == 1) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.JAUNE)) {
                choixCouleur = Couleurs.JAUNE;
                nombreTuileAPoser = 1;
                return;
            }
        }
        if (occCouleurs[3] == 1) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.NOIR)) {
                choixCouleur = Couleurs.NOIR;
                nombreTuileAPoser = 1;
                return;
            }
        }
        if (occCouleurs[4] == 1) {
            if (!mur.couleurDejaPoserLigne(2, Couleurs.BLANC)) {
                choixCouleur = Couleurs.BLANC;
                nombreTuileAPoser = 1;
                return;
            }
        }

        choixCouleur = couleurMax;
        for (int i = 0; i < 5; i++) {
            if (occCouleurs[i] >= 3) {
                nombreTuileAPoser = occCouleurs[i];
            }
        }
    }
}


