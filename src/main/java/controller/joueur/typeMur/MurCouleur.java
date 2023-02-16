package controller.joueur.typeMur;
/**
 *    @author Azul-C group
 *    @date 11/10/2022
 */

import controller.joueur.Joueur;
import controller.joueur.Mur;
import controller.joueur.TypeMur;
import tuile.Couleurs;
import tuile.Tuile;
import utilitaire.AfficherElement;

/**
 * Classe permettant de créer le mur et de récupérer ses informations
 */
public class MurCouleur extends Mur {
    private final Couleurs [] tab = {Couleurs.BLEU, Couleurs.JAUNE, Couleurs.ROUGE, Couleurs.NOIR, Couleurs.BLANC};

    /**
     * Constructeur par défaut créant un mur
     */
    public MurCouleur() {
        /* Création du mur */
        mur = new Tuile[5][];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            Tuile [] tuile = new Tuile[5];
            for (int j = 0; j < 5; j++) {
                tuile[j] = new Tuile(tab[k]);
                k = (j == 4) ? k : (++k % 5);
            }
            this.mur[i] = tuile;
        }
    }
    @Override
    public TypeMur getTypeMur() {
        return TypeMur.MUR_COULEUR;
    }

    public void restaureMur(){
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.mur[i][j].setBool(false);
            }
        }
    }


    /**
     * 	@param ligne
     * 	@param couleurs
     * 	@return la 1ère tuile de cette couleur sur la ligne
     */
    public int getPos(int ligne, Couleurs couleurs) {
        int indexTuileDansMur = 0;
        while(indexTuileDansMur < 5 && this.mur[ligne - 1][indexTuileDansMur].getCouleur() != couleurs) {
            indexTuileDansMur++;
        }
        return indexTuileDansMur;
    }

    /**
     * @return le mur
     */

    /** @param numLigne
     * 	@param numColonne
     * 	@param joueur
     * Placement (modificication) d'une tuile sur le mur */
    public void placeTuileSurMur(int numLigne, int numColonne, Joueur joueur, int affichage) {
        joueur.getMurJoueur().getMur()[numLigne - 1][numColonne].setBool(true);
        AfficherElement.affichePlaceTuileSurMur(joueur, affichage);
        for (int i = 0; i < numLigne; i++)
            joueur.getLigneMotifJoueur().getPlateauDeMotif()[numLigne - 1][i].setCouleur(Couleurs.VIDE);
    }
    //Compte le nombre de tuile par ligne sur le mur
    public int compteurTuileLigne(int ligne){
        int compteur = 0;
        for (int i = 0; i < 5; i++){
            if (mur[ligne][i].getBool()){
                compteur++;
            }
        }
        return compteur;
    }
    //methode permettant de savoir si une ligne du mur est pleine de couleur
    public boolean lignePleine(int ligne){
        for (int i = 0; i < 5; i++){
            if (!mur[ligne - 1][i].getBool()) {
                return false;
            }
        }
        return true;
    }

    public boolean verificationSiUneLigneEstPleine(){
        for(int i = 0; i < 5; i++){
            if (lignePleine(i + 1))
                return true;
        }
        return false;
    }

    //methode permettant de savoir si sur une ligne du mur une Couleur est deja poser sur le mur

    public boolean couleurDejaPoserLigne(int ligne, Couleurs couleur) {
        int i = 0;
        while (mur[ligne - 1][i].getCouleur() != couleur && i < 4) {
            i++;
        }
        return mur[ligne - 1][i].getBool();
    }
}
