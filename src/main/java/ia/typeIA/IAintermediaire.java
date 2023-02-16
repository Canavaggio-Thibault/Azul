package ia.typeIA;

/*
  IAintermediare : pioche les tuiles dans l'endroit qui possède le plus de tuile
  d'une même couleur, pose en priorité sur la ligne de même nombre que les
  tuiles piochées, ensuite sur une ligne plus grande et en dernier choix sur
  une ligne plus petite
  Variables à récupérer pour le placement et le choix :
  choixFabriqueOuCentre, choixCouleur, choixFabrique, nombreTuileAPoser, ligne (numéro de la ligne de motif, si =7 alors c'est dans le plancher
 */

import controller.joueur.LignesMotif;
import controller.joueur.Mur;
import controller.manager.CentreDeTable;
import controller.manager.fabrique.Fabrique;
import controller.manager.fabrique.ListeDesFabriques;
import ia.IA;
import ia.TypeIa;
import tuile.Couleurs;
import tuile.Tuile;

import java.util.LinkedList;
import java.util.ListIterator;

public class IAintermediaire extends IA {

	@Override
	public TypeIa getType() {
		return TypeIa.IA_INTERMEDIARE;
	}

	/**
	 * Set le nombre de tuiles a poser
	 * @param nbTuilesAPoser nombres de tuiles a poser
	 */
	public void setNombreTuileAPoser(int nbTuilesAPoser) { nombreTuileAPoser = nbTuilesAPoser;}


    /**
     * Retourne le maximum d'un tableau d'entiers
     * @param tab tableau d'entiers
     * @return max
     */
    public int maxTab(int[] tab) {
    	int max = 0;
		for (int j : tab) {
			if (j > max)
				max = j;
		}
    	return max;
    }


    /**
     * Retourne fabriques (0) ou centre (1) selon l'endroit où le plus d'occurrence
     * d'une même couleur se trouve (fabriques si égalité)
     */
   public void choixFabriqueOuCentre(CentreDeTable centreDeTable, ListeDesFabriques fabriques, Mur mur) {
    	boolean centreVide = true;
    	boolean fabriquesVide = true;
	   boolean uniquementjeton = true;
    	int maxCouleursFabriques = 0;
    	int maxCouleursCentre;
    	// vérifie que le centre n'est pas vide
	   for (int i = 0; i < centreDeTable.getCentre().length; i++) {//parcours du centre
		   if (centreDeTable.getCentre()[i].getCouleur() != Couleurs.VIDE) {//verifie que centre pas vide
			   if (centreDeTable.getCentre()[i].getCouleur() == Couleurs.JETON) {//verifie que la cas == jeton
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
    	
    	// récupère la liste des fabriques
        LinkedList<Fabrique> list = fabriques.getListFabrique();
        ListIterator<Fabrique> iter = list.listIterator(0);
        int compteurIndex = 0;
        while (iter.hasNext()){	//parcours la liste des fabriques
            Tuile [] tuiles = iter.next().getFabrique();
            for (Tuile tuile: tuiles) {
            	//vérifie que la liste n'est pas vide
				if (tuile.getCouleur() != Couleurs.VIDE) {
					fabriquesVide = false;
					break;
				}
            }
            
            // stocke le max d'occurrence d'une meme couleur dans les fabriques
            int[] tabCompte = new int[5];
            compteCouleur(tuiles, tabCompte);
            int maxTab;
            maxTab = maxTab(tabCompte);
            if (maxTab > maxCouleursFabriques) {
            	maxCouleursFabriques = maxTab;
            	choixFabrique =  compteurIndex;
            }
           
            compteurIndex++;
        }
        
     // stocke le max d'occurrence d'une meme couleur du centre
        int[] couleursCentre = new int[5];
    	compteCouleur(centreDeTable.getCentre(), couleursCentre);
    	maxCouleursCentre = maxTab(couleursCentre);

        // retourne fabrique (0) ou centre (1) si l'un des deux est vide
    	if (centreVide) {
    		nombreTuileAPoser = maxCouleursFabriques;
    		choixFabriqueOuCentre = 0;
			return;
    	}

    	if (fabriquesVide) {
    		nombreTuileAPoser = maxCouleursCentre;
			choixFabriqueOuCentre = 1;
			return;
    	}
	   	if(uniquementjeton){
		   choixFabriqueOuCentre = 0;
		   return;
	   	}
    	
    	
    	if (maxCouleursCentre > maxCouleursFabriques) {
    		nombreTuileAPoser = maxCouleursCentre;
			choixFabriqueOuCentre = 1;
			return;
    	}
    	if (maxCouleursFabriques > maxCouleursCentre) {
    		nombreTuileAPoser = maxCouleursFabriques;
			choixFabriqueOuCentre = 0;
			return;
    	}
	   choixFabriqueOuCentre = 0;
    } 


    /**
     * Return la couleur la plus présente d'un tableau de tuiles
     * @param tabTuiles tableau de tuiles
     * @param tabCompte	tab de couleurs (va être update suite a cette methode)
     */
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

    /**
     * Choix de la couleur la plus présente dans la fabrique 
     * @param fabriques liste de fabriques
     * @param indexDansFabrique index de la fabrique voulue
     */
    public void choixCouleurDansFabrique(ListeDesFabriques fabriques, int indexDansFabrique, Mur mur) {
    	// recuperation des fabriques
    	int[] occCouleurs = new int[5];
        LinkedList<Fabrique> list = fabriques.getListFabrique();
        ListIterator<Fabrique> iter = list.listIterator(0);
        
        // recuperation de la fabrique 
        int i = 0;
        Tuile [] tuiles = new Tuile[4] ;
        while (iter.hasNext() && i != (indexDansFabrique + 1) ){
        	i++;
			tuiles = iter.next().getFabrique();
        }
        // compte les couleurs de la fabrique et retourne la couleur la plus présente
        choixCouleur = compteCouleur(tuiles, occCouleurs);
    }
    

    /**
     * @param centreDeTable le centre de la table
     * Choisit la couleur la plus présente dans le centre
     */
    public void choixCouleurDansCentre(CentreDeTable centreDeTable, Mur mur) {
    	int[] occCouleurs = new int[5];
    	 // compte les couleurs dans le centre et retourne la plus présente
		choixCouleur = compteCouleur(centreDeTable.getCentre(), occCouleurs);
    }

   
    /**
     * @param couleurDeLaTuile couleur de la tuile
     * @param plateauDeMotif lignes de motif
	 * @param mur mur
     */
    public void choixPlacementCouleurSurLigne(Couleurs couleurDeLaTuile, LignesMotif plateauDeMotif, Mur mur) {
        if (nombreTuileAPoser == 0) {
			choixLigne = 0;
			return;
        }

		int indexDejaCetteCouleurMoinsDePlace = 50;

		// cherche une ligne qui a déjà cette couleur de posee (non pleine) et libre sur le mur
		// parcours toutes les lignes de motif
        for (int i = 0; i < 5; i++) {
			// regarde si une ligne n'a pas la couleur choisit sur la 1ere case
        	if (plateauDeMotif.getPlateauDeMotif()[i][0].getCouleur() == couleurDeLaTuile) {
				// regarde si la derniere case de la ligne est vide
        		if(plateauDeMotif.getPlateauDeMotif()[i][i].getCouleur() != couleurDeLaTuile) {
					// alors la ligne i n'est pas pleine et possede au moins une tuile de la couleur choisie
					// on pose les tuiles sur cette ligne
					int compteurCaseNonLibre = 0;
					for (int j = 0; plateauDeMotif.getPlateauDeMotif()[i][j].getCouleur() != Couleurs.VIDE; j++) {
						compteurCaseNonLibre++;
					}
					int compteurCaseLibre = i+1 - compteurCaseNonLibre;
					if (compteurCaseLibre >= nombreTuileAPoser) {
						choixLigne = ( i+1);
						return;
					} else {
						indexDejaCetteCouleurMoinsDePlace = i;
					}
        		}
        	}
        }
        
        // cherche si la ligne de longueur nombreDeTuileAPoser est vide
		if (nombreTuileAPoser < 5 && nombreTuileAPoser > 0) {
			if (plateauDeMotif.getPlateauDeMotif()[nombreTuileAPoser-1][0].getCouleur() == Couleurs.VIDE) {
				if(!mur.couleurDejaPoserLigne(nombreTuileAPoser, couleurDeLaTuile)){
					choixLigne = nombreTuileAPoser; // ligne
					return;
				}

			}
		}

		if (indexDejaCetteCouleurMoinsDePlace != 50) {
			if (!mur.couleurDejaPoserLigne(indexDejaCetteCouleurMoinsDePlace+1, couleurDeLaTuile)) {
				choixLigne = indexDejaCetteCouleurMoinsDePlace+1;
				return;
			}

		}

        // cherche une ligne disposable plus longue que le nombre de tuiles à poser
        for (int i = nombreTuileAPoser; i <  plateauDeMotif.getPlateauDeMotif().length; i++) {
        	if (plateauDeMotif.getPlateauDeMotif()[i][0].getCouleur() == Couleurs.VIDE) {
				if (!mur.couleurDejaPoserLigne(i+1, couleurDeLaTuile)) {
					choixLigne = (i+1); // ligne
					return;
				}
        	}
        }


        // cherche une ligne disponible plus courte que le nombre de tuiles à poser
        for (int i = nombreTuileAPoser; i >= 0; i--) {
			if (i < 4 && i >= 0 ) {
				if (plateauDeMotif.getPlateauDeMotif()[i][0].getCouleur() == Couleurs.VIDE) {
					if (!mur.couleurDejaPoserLigne(i+1, couleurDeLaTuile)) {
						choixLigne = (i + 1); // ligne
						return;
					}
				}
			}
        }

        // 7 correspond au plancher
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
        // choisit fabrique (0) ou centre (1) et change la variable choixFabrique si fabrique a ete choisit
    	choixFabriqueOuCentre(centreDeTable, fabriques, mur);
    	if (choixFabriqueOuCentre == 0)
    		choixCouleurDansFabrique(fabriques, choixFabrique, mur);
		else
			choixCouleurDansCentre(centreDeTable, mur);

		choixPlacementCouleurSurLigne(choixCouleur, lignesMotif, mur);
		return this;
	}
}
