package controller.joueur;

import tuile.Couleurs;
import tuile.Tuile;

public abstract class Mur {

    protected Tuile[][] mur;
    public abstract TypeMur getTypeMur();

    public Tuile[][] getMur(){return mur;}

    public abstract void placeTuileSurMur(int numLigne, int numColonne, Joueur joueur, int affichage);

    public abstract boolean lignePleine(int ligne);

    public abstract boolean verificationSiUneLigneEstPleine();

    public abstract boolean couleurDejaPoserLigne(int ligne, Couleurs couleur);

    public abstract void restaureMur();

    public abstract int getPos(int ligne, Couleurs couleur);
}
