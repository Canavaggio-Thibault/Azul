package controller.joueur.typeMur;

import controller.joueur.Joueur;
import controller.joueur.Mur;
import controller.joueur.TypeMur;
import tuile.Couleurs;
import tuile.Tuile;
import utilitaire.AfficherElement;

public class MurGris extends Mur {

    public MurGris() {
        mur = new Tuile[5][];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            Tuile[] tuile = new Tuile[5];
            for (int j = 0; j < 5; j++) {
                tuile[j] = new Tuile(Couleurs.GRIS);
                k = (j == 4) ? k : (++k % 5);
            }
            this.mur[i] = tuile;
        }
    }

    @Override
    public TypeMur getTypeMur() {
        return TypeMur.MUR_GRIS;
    }

    /**
     * getMurGris
     *
     * @return
     */

    /**
     * Place la tuile sur le mur gris
     *
     * @param numLigne
     * @param numColonne
     * @param joueur
     * @param affichage
     */
    public void placeTuileSurMur(int numLigne, int numColonne, Joueur joueur, int affichage) {
        // parcours de toutes les lignes
        for (int i = 0; i < 5; i++) {
            // verif meme couleur dans la colonne
            for (int j = 0; j < 5; j++) {
                if (mur[j][numColonne].getCouleur() == joueur.getLigneMotifJoueur().getPlateauDeMotif()[numLigne - 1][0].getCouleur()) {
                    numColonne = (numColonne >= 4) ? numColonne % 4 : numColonne + 1;
                }
            }
            // verif si tuile d'une autre couleur deja posee sur colonne et ligne choisit
            if (joueur.getMurJoueur().getMur()[numLigne - 1][numColonne].getBool()) {
                numColonne = (numColonne >= 4) ? numColonne % 4 : numColonne + 1;
            }
        }

        joueur.getMurJoueur().getMur()[numLigne - 1][numColonne].setBool(true);
        joueur.getMurJoueur().getMur()[numLigne - 1][numColonne].setCouleur(joueur.getLigneMotifJoueur().getPlateauDeMotif()[numLigne - 1][0].getCouleur());
        AfficherElement.affichePlaceTuileSurMur(joueur, affichage);
        for (int i = 0; i < numLigne; i++)
            joueur.getLigneMotifJoueur().getPlateauDeMotif()[numLigne - 1][i].setCouleur(Couleurs.VIDE);
    }

    /**
     * On vérifie la ligne pour savoir si elle est pleine
     *
     * @param ligne
     * @return
     */
    public boolean lignePleine(int ligne) {
        for (int i = 0; i < 5; i++) {
            if (!mur[ligne - 1][i].getBool())
                return false;
        }
        return true;
    }

    /**
     * On appelle la méthode lignePleine
     *
     * @return
     */
    public boolean verificationSiUneLigneEstPleine() {
        for (int i = 0; i < 5; i++) {
            if (lignePleine(i + 1))
                return true;
        }
        return false;
    }


    /**
     * On vérifie si la couleur est déja poser sur la ligne ou la colonne
     *
     * @param ligne
     * @param couleur
     * @return
     */
    public boolean couleurDejaPoserLigne(int ligne, Couleurs couleur) {
        for (int i = 0; i < 5; i++)
            if (mur[ligne - 1][i].getCouleur() == couleur)
                return true;
        return false;
    }

    @Override
    public void restaureMur() {

    }

    @Override
    public int getPos(int ligne, Couleurs couleur) {
        return 0;
    }

}
