/**
 * Classe permettant la gestion des points au fûr et à mesure des points
 */
package utilitaire;

import controller.joueur.Joueur;
import controller.joueur.TypeMur;
import controller.joueur.typeMur.MurVariante;
import tuile.Couleurs;
import controller.joueur.Mur;
import controller.joueur.Plancher;

import static controller.joueur.TypeMur.MUR_VARIANTE;

public class CompterPoint {

    private int nbPoint;

    /**
     * Constructeur de la CompterPoint
     */
    public CompterPoint() {
        nbPoint = 0;
    }

    /**
     * @return les points
     */
    public int getPoint() {
        return nbPoint;
    }

    public void setNbPoint(int i) {
        this.nbPoint = i;
    }

    /**
     * Ajouter les points entre eux (anciens et nouveau)
     */
    public void ajouterPoint(int point) {
        nbPoint += point;
    }

    /**
     * Retirer point malus
     *
     * @param point
     */
    public void retirerPoint(int point) {

        this.nbPoint = Math.max(this.nbPoint - point, 0);
    }

    /**
     * Compte le nombre de tuiles pour x +
     *
     * @param joueur
     * @param ligne
     * @param x
     */
    public int compteXPlus(Joueur joueur, int ligne, int x) {
        int nb = 0;
        int i = x + 1;
        while (i < 5 && joueur.getMurJoueur().getMur()[ligne][i].getBool()) {
            nb++;
            i++;
        }
        return nb;
    }

    /**
     * Compte le nombre de tuile pour x -
     *
     * @param joueur
     * @param ligne
     * @param x
     */
    public int compteXMoins(Joueur joueur, int ligne, int x) {
        int nb = 0;
        int i = x - 1;
        while (i >= 0 && joueur.getMurJoueur().getMur()[ligne][i].getBool()) {
            nb++;
            i--;
        }
        return nb;
    }

    /**
     * Compte le nombre de tuile sur ligne +
     *
     * @param joueur
     * @param ligne
     * @param x
     * @return
     */
    public int compteLignePlus(Joueur joueur, int ligne, int x) {
        int nb = 0;
        int i = ligne + 1;
        while (i < 5 && joueur.getMurJoueur().getMur()[i][x].getBool()) {
            nb++;
            i++;
        }
        return nb;
    }

    /**
     * Compte le nombre de tuile sur ligne -
     *
     * @param joueur
     * @param ligne
     * @param x
     * @return
     */
    public int compteLigneMoins(Joueur joueur, int ligne, int x) {
        int nb = 0;
        int i = ligne - 1;
        while (i >= 0 && joueur.getMurJoueur().getMur()[i][x].getBool()) {
            nb++;
            i--;
        }
        return nb;
    }

    /**
     * Vérification sur centre du mur pour comptage point
     *
     * @param joueur
     * @param ligne
     * @param colon
     * @return
     */
    public int milieuTableau(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 1;
        if (mur.getMur()[ligne][colon - 1].getBool())
            nb += compteXMoins(joueur, ligne, colon);

        if (mur.getMur()[ligne][colon + 1].getBool())
            nb += compteXPlus(joueur, ligne, colon);

        if (mur.getMur()[ligne - 1][colon].getBool())
            nb += compteLigneMoins(joueur, ligne, colon);

        if (mur.getMur()[ligne + 1][colon].getBool())
            nb += compteLignePlus(joueur, ligne, colon);

        return nb;
    }

    /**
     * Comptage des points si ligne = 1
     *
     * @param joueur
     * @param ligne
     * @param colon
     */
    public int ligne1(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 1;
        if (mur.getMur()[ligne][colon - 1].getBool())
            nb += compteXMoins(joueur, ligne, colon);
        if (mur.getMur()[ligne][colon + 1].getBool())
            nb += compteXPlus(joueur, ligne, colon);
        if (mur.getMur()[ligne + 1][colon].getBool())
            nb += compteLignePlus(joueur, ligne, colon);
        return nb;
    }

    /**
     * Comptage des points si ligne = 5
     *
     * @param joueur
     * @param ligne
     * @param colon
     * @return un entier
     */
    public int ligne5(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 1;
        if (mur.getMur()[ligne][colon - 1].getBool())
            nb += compteXMoins(joueur, ligne, colon);
        if (mur.getMur()[ligne][colon + 1].getBool())
            nb += compteXPlus(joueur, ligne, colon);
        if (mur.getMur()[ligne - 1][colon].getBool())
            nb += compteLigneMoins(joueur, ligne, colon);
        return nb;
    }

    /**
     * Comptage des points si colonne = 0
     *
     * @param joueur
     * @param ligne
     * @param colon
     * @return un entier
     */
    public int colon0(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 1;
        if (mur.getMur()[ligne][colon + 1].getBool())
            nb += compteXPlus(joueur, ligne, colon);
        if (mur.getMur()[ligne - 1][colon].getBool())
            nb += compteLigneMoins(joueur, ligne, colon);
        if (mur.getMur()[ligne + 1][colon].getBool())
            nb += compteLignePlus(joueur, ligne, colon);
        return nb;
    }

    /**
     * Comptage des points si colonne = 4
     *
     * @param joueur
     * @param ligne
     * @param colon
     */
    public int colon4(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 1;
        if (mur.getMur()[ligne][colon - 1].getBool())
            nb += compteXMoins(joueur, ligne, colon);
        if (mur.getMur()[ligne - 1][colon].getBool())
            nb += compteLigneMoins(joueur, ligne, colon);
        if (mur.getMur()[ligne + 1][colon].getBool())
            nb += compteLignePlus(joueur, ligne, colon);
        return nb;
    }

    /**
     * Comptage des points si on est dans le coin haut gauche du mur
     *
     * @param joueur
     * @param ligne
     * @param colon
     */
    public int coinHautGauche(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 1;
        if (mur.getMur()[ligne][colon + 1].getBool())
            nb += compteXPlus(joueur, ligne, colon);
        if (mur.getMur()[ligne + 1][colon].getBool())
            nb += compteLignePlus(joueur, ligne, colon);
        return nb;
    }

    /**
     * Comptage des points si on est dans le coin bas gauche du mur
     *
     * @param joueur
     * @param ligne
     * @param colon
     */
    public int coinBasGauche(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 1;
        if (mur.getMur()[ligne][colon + 1].getBool())
            nb += compteXPlus(joueur, ligne, colon);
        if (mur.getMur()[ligne - 1][colon].getBool())
            nb += compteLigneMoins(joueur, ligne, colon);
        return nb;
    }

    /**
     * Comptage des points si on est dans le coin bas droite du mur
     *
     * @param joueur
     * @param ligne
     * @param colon
     */
    public int coinBasDroit(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 1;
        if (mur.getMur()[ligne][colon - 1].getBool())
            nb += compteXMoins(joueur, ligne, colon);
        if (mur.getMur()[ligne - 1][colon].getBool())
            nb += compteLigneMoins(joueur, ligne, colon);
        return nb;
    }

    /**
     * Comptage des points si on est dans le coin haut droite du mur
     *
     * @param joueur
     * @param ligne
     * @param colon
     */
    public int coinHautDroit(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 1;
        if (mur.getMur()[ligne][colon - 1].getBool())
            nb += compteXMoins(joueur, ligne, colon);
        if (mur.getMur()[ligne + 1][colon].getBool())
            nb += compteLignePlus(joueur, ligne, colon);
        return nb;
    }

    /**
     * Permet la gestion du comptage des point après chaque manche
     *
     * @param joueur
     * @param ligne
     * @param colon
     */
    public void compterPointLigneMotif(Joueur joueur, int ligne, int colon) {
        Mur mur = joueur.getMurJoueur();
        int nb = 0;
        ligne = ligne - 1;
        if (ligne > 0 && ligne < 4 && colon > 0 && colon < 4 && mur.getMur()[ligne][colon].getBool())
            nb += milieuTableau(joueur, ligne, colon);

        if (ligne == 0 && colon < 4 && colon > 0 && mur.getMur()[ligne][colon].getBool())
            nb += ligne1(joueur, ligne, colon);

        if (ligne == 4 && colon < 4 && colon > 0 && mur.getMur()[ligne][colon].getBool())
            nb += ligne5(joueur, ligne, colon);

        if (colon == 0 && ligne < 4 && ligne > 0 && mur.getMur()[ligne][colon].getBool())
            nb += colon0(joueur, ligne, colon);

        if (colon == 4 && ligne < 4 && ligne > 0 && mur.getMur()[ligne][colon].getBool())
            nb += colon4(joueur, ligne, colon);

        if (colon == 0 && ligne == 0 && mur.getMur()[ligne][colon].getBool())
            nb += coinHautGauche(joueur, ligne, colon);

        if (colon == 0 && ligne == 4 && mur.getMur()[ligne][colon].getBool())
            nb += coinBasGauche(joueur, ligne, colon);

        if (colon == 4 && ligne == 4 && mur.getMur()[ligne][colon].getBool())
            nb += coinBasDroit(joueur, ligne, colon);

        if (colon == 4 && ligne == 0 && mur.getMur()[ligne][colon].getBool())
            nb += coinHautDroit(joueur, ligne, colon);
        //Point bonus mur variante
        if (mur.getTypeMur() == MUR_VARIANTE) {
            if (ligne == 1 && colon == 1)
                nb = nb * 2;
            if (ligne == 1 && colon == 3)
                nb = nb * 2;
            if (ligne == 2 && colon == 2)
                nb = nb * 2;
            if (ligne == 3 && colon == 1)
                nb = nb * 2;
            if (ligne == 3 && colon == 3)
                nb = nb * 2;
        }
        ajouterPoint(nb);
    }

    /**
     * Utilise le plancher du joueur pour retirer des points au joueur
     *
     * @param joueur
     */
    public void malusPlancher(Joueur joueur) {
        Plancher plancher = joueur.getPlancherJoueur();
        if (joueur.getMurJoueur().getTypeMur() == MUR_VARIANTE) {
            for (int i = 0; i < 7; i++) {
                if (plancher.getPlancher()[i].getCouleur() != Couleurs.VIDE) {
                    if (i == 0 && i == 2 && i == 4) {
                        retirerPoint(1);
                        joueur.incrementeNombreDeMalis();
                    }
                    if (i == 3 && i == 5) {
                        retirerPoint(2);
                        joueur.incrementeNombreDeMalis();
                    }
                    if (i == 6) {
                        retirerPoint(3);
                        joueur.incrementeNombreDeMalis();
                    }
                }
            }
        } else {
            for (int i = 0; i < 7; i++) {
                if (plancher.getPlancher()[i].getCouleur() != Couleurs.VIDE) {
                    if (i == 0 || i == 1) {
                        retirerPoint(1);
                        joueur.incrementeNombreDeMalis();
                    }
                    if (i == 2 || i == 3 || i == 4) {
                        retirerPoint(2);
                        joueur.incrementeNombreDeMalis();
                        joueur.incrementeNombreDeMalis();
                    }
                    if (i == 5 || i == 6) {
                        retirerPoint(3);
                        joueur.incrementeNombreDeMalis();
                        joueur.incrementeNombreDeMalis();
                        joueur.incrementeNombreDeMalis();
                    }
                }
            }
        }
    }

    /**
     * Vérifie si le joueur possède une ligneHorizontale pour ajouter 2 point bonus
     *
     * @param joueur
     * @param ligne
     */
    public int ligneHorizontale(Joueur joueur, int ligne) {
        Mur mur = joueur.getMurJoueur();
        if (mur.getMur()[ligne][0].getBool() && mur.getMur()[ligne][1].getBool() && mur.getMur()[ligne][2].getBool()
                && mur.getMur()[ligne][3].getBool() && mur.getMur()[ligne][4].getBool())
                return 2;
        return 0;

    }

    /**
     * Vérifie si le joueur possède une ligneVerticale pour ajouter 7 point bonus
     *
     * @param joueur
     * @param colon
     */
    public int ligneVerticale(Joueur joueur, int colon) {
        Mur mur = joueur.getMurJoueur();
        if (mur.getMur()[0][colon].getBool() && mur.getMur()[1][colon].getBool() && mur.getMur()[2][colon].getBool()
                && mur.getMur()[3][colon].getBool() && mur.getMur()[4][colon].getBool())
            return 7;
        return 0;
    }

    /**
     * Vérifie si le joueur possède 5 tuiles de la même couleur pour ajouter 10
     * points
     *
     * @param joueur
     * @param choix
     */
    public int tuile5Couleurs(Joueur joueur, int choix) {
        Mur mur = joueur.getMurJoueur();
        // Couleurs couleur = Couleurs.VIDE;
        // couleur = Couleurs.JAUNE;
        if (choix == 0) {
            int nb = 0;
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++) {
                    if (mur.getMur()[i][j].getBool() && mur.getMur()[i][j].getCouleur() == Couleurs.JAUNE)
                        nb++;
                }
            if (nb == 5)
                return 10;

        }
        // couleur = Couleurs.BLANC;
        if (choix == 1) {
            int nb = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++)
                    if (mur.getMur()[i][j].getBool() && mur.getMur()[i][j].getCouleur() == Couleurs.BLANC)
                        nb++;
            }
            if (nb == 5)
                return 10;
        }
        // couleur = Couleurs.NOIR;
        if (choix == 2) {
            int nb = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++)
                    if (mur.getMur()[i][j].getBool() && mur.getMur()[i][j].getCouleur() == Couleurs.NOIR)
                        nb++;
            }
            if (nb == 5)
                return 10;

        }
        // couleur = Couleurs.ROUGE;
        if (choix == 3) {
            int nb = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++)
                    if (mur.getMur()[i][j].getBool() && mur.getMur()[i][j].getCouleur() == Couleurs.ROUGE)
                        nb++;
            }
            if (nb == 5)
                return 10;

        }
        // couleur = Couleurs.BLEU;
        if (choix == 4) {
            int nb = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++)
                    if (mur.getMur()[i][j].getBool() && mur.getMur()[i][j].getCouleur() == Couleurs.BLEU)
                        nb++;
            }
            if (nb == 5)
                return 10;

        }
        return 0;
    }

    /**
     * On va compter les points bonus à la fin de la partie, si le joueurs possèdes
     * des lignes horizontales et verticales ainsi que 5 tuiles de même couleur
     *
     * @param joueur
     */
    public void compterPointFinPartie(Joueur joueur) {
        int point = 0;
        for (int i = 0; i < 5; i++) {
            point += ligneHorizontale(joueur, i);
        }
        for (int i = 0; i < 5; i++) {
            point += ligneVerticale(joueur, i);
        }
        for (int i = 0; i < 5; i++) {
            point += tuile5Couleurs(joueur, i);
        }
        ajouterPoint(point);
    }
}
