/**
 * Cette classe permet de Créer des tuiles de différentes couleur
 */
package tuile;

public class Tuile {
    /* couleurs type from couleurs enum */
    private Couleurs couleur;
    private boolean bool;

    /**
     *  Constructeur par défault
    */
    public Tuile() {
        this.couleur = Couleurs.VIDE;
    }

    public Tuile(Couleurs couleur) {
        this.couleur = couleur;
    }
    public boolean getBool() {
        return this.bool;
    }

    /**
     * @param b
     */
    public void setBool(boolean b) {
        this.bool = b;
    }

    /**
     * @param couleur
     */
    public void setCouleur(Couleurs couleur) {
        this.couleur = couleur;
    }

    public Couleurs getCouleur() {
        return this.couleur;
    }

}
