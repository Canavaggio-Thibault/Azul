package ia;

import controller.manager.CentreDeTable;
import controller.manager.fabrique.ListeDesFabriques;
import tuile.Couleurs;
import controller.joueur.LignesMotif;
import controller.joueur.Mur;

public abstract class IA {

    protected Couleurs choixCouleur = null;
    protected int choixLigne = 0;
    protected int choixFabrique = 0;
    protected int choixFabriqueOuCentre = 0;
    protected int nombreTuileAPoser = 0;


    public Couleurs getChoixCouleur()  {return choixCouleur;}
    public int getLigne() {return choixLigne;}
    public abstract TypeIa getType() ;
    public int getChoixFabrique(){return choixFabrique;}
    public abstract void setNombreTuileAPoser(int a);
    public  int getNombreTuileAPoser() {return nombreTuileAPoser;}
    public abstract void choixFabriqueOuCentre(CentreDeTable centreDeTable, ListeDesFabriques fabriques, Mur mur);
    public int getChoixFabriqueOuCentre() {return choixFabriqueOuCentre;}
    public abstract void choixCouleurDansFabrique(ListeDesFabriques fabriques, int indexDansFabrique, Mur mur);
    public abstract void choixCouleurDansCentre(CentreDeTable centreDeTable, Mur mur);
    public abstract void choixPlacementCouleurSurLigne(Couleurs couleurDeLaTuile, LignesMotif plateauDeMotif, Mur mur);
    public abstract IA choixIA(ListeDesFabriques fabriques, LignesMotif lignesMotif, CentreDeTable centreDeTable, Mur mur);

}
