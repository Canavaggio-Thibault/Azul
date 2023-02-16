package ia.typeIA;

import controller.joueur.LignesMotif;
import controller.joueur.Mur;
import controller.manager.CentreDeTable;
import controller.manager.fabrique.Fabrique;
import controller.manager.fabrique.ListeDesFabriques;
import ia.IA;
import ia.TypeIa;
import tuile.Couleurs;
import tuile.Tuile;
import utilitaire.Utilitaire;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * Cette classe permet de choisir le comportement d'un joueur en fonction de son niveau d'IA
 *
 */

public class IArandom extends IA {

    /**
     * Le choix d'une couleur aléatoire dans la fabrique
     * @param listeDesFabriques la liste des fabriques²
     * @param indexDansFabrique l'index de la fabrique
     */
    public void choixCouleurDansFabrique(ListeDesFabriques listeDesFabriques, int indexDansFabrique, Mur mur) {
        Tuile tuile = Utilitaire.getRandom();
        int nbOcc = listeDesFabriques.nombreOccurrenceCouleurList(tuile.getCouleur(), indexDansFabrique);
        //return directement la couleur si elle est présente dans la fabrique
        if (nbOcc > 0)
            choixCouleur = tuile.getCouleur();
        else {
            //relance une couleur tant que la couleur n'est pas présente dans la fabrique
            while (nbOcc == 0) {
                tuile = Utilitaire.getRandom();
                nbOcc = listeDesFabriques.nombreOccurrenceCouleurList(tuile.getCouleur(), indexDansFabrique);
            }
        }
        choixCouleur = tuile.getCouleur();
    }
    
    /**
     * Choisit entre fabrique (0) centre (1) 
     * @param centreDeTable le centre de la table
     * @param fabriques la liste de fabriques
     */
    public void choixFabriqueOuCentre (CentreDeTable centreDeTable, ListeDesFabriques fabriques, Mur mur) {
    	boolean centreVide = true;
    	boolean fabriquesVide = true;
        boolean uniquementjeton = true;
    	/* Vérifie que le centre n'est pas vide */
        for (int i = 0; i < centreDeTable.getCentre().length; i++) {
            if (centreDeTable.getCentre()[i].getCouleur() != Couleurs.VIDE && centreDeTable.getCentre()[i].getCouleur() != Couleurs.JETON) {
                for(int j = 1; j < centreDeTable.getCentre().length; j++){
                    if(centreDeTable.getCentre()[i].getCouleur() != Couleurs.VIDE){
                        uniquementjeton = false;
                    }
                }
                centreVide = false;
            }
        }
    	
    	/* Vérifie que toutes les fabriques ne sont pas vide */
        LinkedList<Fabrique> list = fabriques.getListFabrique();
        ListIterator<Fabrique> iter = list.listIterator(0);
        while (iter.hasNext()){
            Tuile [] tuiles = iter.next().getFabrique();
            for (Tuile tuile: tuiles) {
                if(tuile.getCouleur() != Couleurs.VIDE) {
                    fabriquesVide = false;
                    break;
                }
            }
        }
        /* Retourne fabrique (0) ou centre (1) aleatoirement sauf si une des deux est vide */
    	if (centreVide) {
            choixFabriqueOuCentre = 0;
            return;
        }

    	if (fabriquesVide) {
            choixFabriqueOuCentre = 1;
        }
        else {
            if(uniquementjeton){
                choixFabriqueOuCentre = 0;
            }
            else choixFabriqueOuCentre = Utilitaire.getValue(2);
        }
    }

    /**
     * Permet de choisir une couleur (la 1ère présente dans le centre)
     * @param centreDeTable le centre de la table
     */
    public void choixCouleurDansCentre(CentreDeTable centreDeTable, Mur mur) {
    	/* Créer un tableau temporaire permettant de stocker toutes les couleurs non vides
    	 * (car il peut y avoir des "trou" dans le centre" : [ROUGE VIDE BLEU...]
    	 */
    	Tuile[] tabCouleurCentre = new Tuile[centreDeTable.getCentre().length];
    	for (int i = 0; i < tabCouleurCentre.length; i++) {
    		tabCouleurCentre[i] = new Tuile();
    	}
    	
    	/* Remplie le tableau avec toutes les tuiles de couleur du centre et incrémente le compteur */
    	int compteur = 0;
    	for (int i = 0; i < centreDeTable.getCentre().length; i++) {
    		if (centreDeTable.getCentre()[i].getCouleur() != Couleurs.VIDE) {
    			tabCouleurCentre[compteur].setCouleur(centreDeTable.getCentre()[i].getCouleur());  
    			compteur++;
    		}
    	}
    		
    	/* Créer un random et retourne la couleur de cette tuile */
    	if (compteur > 0) {
        	int random = Utilitaire.getValue(compteur);
        	choixCouleur = tabCouleurCentre[random].getCouleur();
    	} else {
    		choixCouleur = null;
    	}
    }

    /**
     *  Choisit la ligne sur laquelle placer la/les tuile(s)
     * 	@param couleurDeLaTuile couleur de la tuile que l'ont veut placer sur une ligne de motif
     * 	@param plateauDeMotif les lignes de motif
     *  @param mur le mur du joueur
     */
    public void choixPlacementCouleurSurLigne(Couleurs couleurDeLaTuile, LignesMotif plateauDeMotif, Mur mur) {
        //cherche si la couleur n'est pas déjà placée et retourne la ligne sur laquelle est placée cette couleur
        int random;
        for (int i = 0; i < 5; i++) {
            if (plateauDeMotif.getPlateauDeMotif()[i][0].getCouleur() == couleurDeLaTuile) {
                if (plateauDeMotif.getPlateauDeMotif()[i][i].getCouleur() == Couleurs.VIDE) {
                    choixLigne = (i + 1);
                    return;
                }
            }
        }
        //retourne une ligne aléatoire sur laquelle aucune tuile n'est posée et sur laquelle le mur n'est pas remplit pour cette couleur

        // retourne le plancher si toutes les lignes de motif occupées d'une couleur différente
        boolean full = true;
        for (int i = 0; i < 5; i++) {
            if (plateauDeMotif.getPlateauDeMotif()[i][0].getCouleur() == Couleurs.VIDE)
                full = false;
        }

        if (full) {
            choixLigne = 7;
            return;
        }

        for (int i = 0; i < 4; i++) {   //verifie qu'une ligne est bien disponnible
            if (!mur.couleurDejaPoserLigne(i+1, couleurDeLaTuile)) {
                int j = 20;
                while(j != 0) {
                    random = Utilitaire.getValue(5);
                    if (plateauDeMotif.getPlateauDeMotif()[random][0].getCouleur() == Couleurs.VIDE) {
                        if (!mur.couleurDejaPoserLigne(random+1, couleurDeLaTuile)) {
                            choixLigne = (random + 1);
                            return;
                        }
                    }
                    j--;
                }
            }
        }

        // retourne le plancher si aucune ligne n'est dispo
        choixLigne = 7;
    }

    /**
     * Selectione des fabriques nons vide
     * @param fabriques liste des fabriques
     */
    public void selectionFabriqueNonVide(ListeDesFabriques fabriques){
        this.choixFabrique = Utilitaire.getValue(fabriques.getListFabrique().size());
        LinkedList<Fabrique> list = fabriques.getListFabrique();
        ListIterator<Fabrique> iter = list.listIterator(choixFabrique);
        while (iter.hasNext()){
            Tuile [] tuiles = iter.next().getFabrique();
            for (Tuile tuile: tuiles) {
                if(tuile.getCouleur() != Couleurs.VIDE)
                    return;
            }
            this.choixFabrique = Utilitaire.getValue(fabriques.getListFabrique().size());
            list = fabriques.getListFabrique();
            iter = list.listIterator(choixFabrique);
        }
    }

    /**
     * methode permettant de faire les choix de l'IA
     * @param fabriques liste des fabriques
     * @param lignesMotif les lignes de motif du joueur
     * @param centreDeTable centre de la table
     * @param mur le mur du joueur
     * @return this
     */
    public IA choixIA(ListeDesFabriques fabriques, LignesMotif lignesMotif, CentreDeTable centreDeTable, Mur mur){
        
    	/* Choisit entre fabrique (0) ou centre (0) aleatoirement */
    	choixFabriqueOuCentre(centreDeTable, fabriques, mur);

        //L'IA Random a choisit la fabrique (0)
    	if (choixFabriqueOuCentre == 0) {
    		/* Choisit une fabrique non vide aleatoire */
            selectionFabriqueNonVide(fabriques);
            // choisir une couleur au hazard aussi dans cette fabrique choisie
            choixCouleurDansFabrique(fabriques, choixFabrique, mur);
            // Et l'IA choisie une ligne dans la ligne motif
        }
    	/* L'IA random a choisit le centre (1) */
    	else {
    		choixCouleurDansCentre(centreDeTable, mur);
        }
        choixPlacementCouleurSurLigne(choixCouleur, lignesMotif, mur);
        return this;    // on renvoit les choix de l'IA
    }

    @Override
    public TypeIa getType() {
        return TypeIa.IA_RANDOM;
    }

    @Override
	public void setNombreTuileAPoser(int a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNombreTuileAPoser() {
		// TODO Auto-generated method stub
		return 0;
	}

}
