package controller.manager;

import controller.joueur.Mur;
import controller.joueur.TypeMur;
import ia.IA;
import controller.joueur.Joueur;
import tuile.Couleurs;
import ia.TypeIa;
import tuile.Tuile;
import utilitaire.AfficherElement;

import java.util.LinkedList;
import java.util.ListIterator;

public class Manager {
    private final int nombreJoueur;
    private int nombreMatchNull;
    private int nombreDeManche;
    private final LinkedList<Joueur> listJoueur;
    private final Plateau plateau;
    private String posedeJeton;
    private boolean debutManche;

    public Manager(int nombreJoueur, TypeMur typeMur) {
        this.nombreMatchNull = 0;
        listJoueur = new LinkedList<>();
        this.nombreDeManche = 1;
        this.nombreJoueur = nombreJoueur;
        for (int i = 0; i < nombreJoueur; i++) {
            if (i % 3 == 0) {
                listJoueur.add(new Joueur(i + 1, TypeIa.IA_RANDOM, typeMur));
            } else if (i % 3 == 1) {
                listJoueur.add(new Joueur(i + 1, TypeIa.IA_INTERMEDIARE, typeMur));
            } else {
                listJoueur.add(new Joueur(i + 1, TypeIa.IA_SUPERIEUR, typeMur));
            }
        }
        plateau = new Plateau(this);
        posedeJeton = getListJoueur().get(0).getNomJoueur();
        debutManche = true;
    }

    public int getNombreJoueur() {
        return nombreJoueur;
    }

    public int getNombreMatchNull() {
        return this.nombreMatchNull;
    }

    public int getNombreDeManche() {
        return nombreDeManche;
    }

    public int nombreDeManchePlusUn() {
        return nombreDeManche++;
    }

    public LinkedList<Joueur> getListJoueur() {
        return listJoueur;
    }

    /**
     * méthode qui permet d'avoir la liste des joueurs avec les malus
     */
    public void malusListeJoueur() {
        ListIterator<Joueur> iter_list = listJoueur.listIterator(0);
        Joueur joueur;
        AfficherElement.affichePrintln();
        while (iter_list.hasNext()) {
            joueur = iter_list.next();
            for(int i = 0; i < joueur.getPlancherJoueur().getPlancher().length; i++){
                if(joueur.getPlancherJoueur().getPlancher()[i].getCouleur() == Couleurs.JETON)
                    posedeJeton = joueur.getNomJoueur();
            }
            AfficherElement.affichePointAvantMalus(joueur);
            joueur.getCompterPoint().malusPlancher(joueur);
            plateau.remettrePlancherDansCouvercle(joueur.getPlancherJoueur().getPlancher());
            AfficherElement.affichePointApresMalus(joueur);
        }
        AfficherElement.affichePrintln();
    }

    /**
     * méthode qui permet de savoir si il y a match nul
     *
     * @param listJoueur
     * @param maxPoint
     * @return vrai si il y a un match nul sinon faux
     */
    private boolean verifieSiMatchNull(LinkedList<Joueur> listJoueur, int maxPoint) {
        int done = 0;
        for (Joueur joueur : listJoueur) {
            if (joueur.getCompterPoint().getPoint() == maxPoint)
                done++;
            if (done > 1)
                return true;
        }
        return false;
    }

    public void compareTo(){
        int maxLigne =0;
        for (Joueur joueur : listJoueur) {
            maxLigne = Math.max(joueur.getNombreDeLignesRemplisPartie(),maxLigne);
        }
        for(Joueur joueur :listJoueur){
            if (joueur.getNombreDeLignesRemplisPartie() ==maxLigne){
                joueur.incrementeNombreDeVictoire();
            }
        }
    }
    /**
     * méthode qui permet d'avoir un classement des joueurs en fonction de leur point
     *
     * @param listJoueur
     */
    public void classement(LinkedList<Joueur> listJoueur) {
        int maxPoint = 0;
        for (Joueur joueur : listJoueur) {
            maxPoint = Math.max(joueur.getCompterPoint().getPoint(), maxPoint);
        }
        LinkedList<Joueur> vainqueurs = new LinkedList<>();
        for (Joueur joueur : listJoueur) {
            AfficherElement.afficheClassement(joueur);
            if (joueur.getCompterPoint().getPoint() == maxPoint) {
                vainqueurs.add(joueur);
                if (!verifieSiMatchNull(getListJoueur(), maxPoint))
                    joueur.incrementeNombreDeVictoire();
            else compareTo();
                break;
            }
        }
        AfficherElement.afficheVainqueur(vainqueurs, maxPoint);
    }

    /**
     * méthode qui permet de savoir si la partie est finie
     *
     * @return vrai si la partie est finie sinon faux
     */
    protected boolean verificationSiPartieEstFinie() {
        for (Joueur joueur : listJoueur) {
            if (joueur.getMurJoueur().verificationSiUneLigneEstPleine()) {
                joueur.incrementeNombreDeLigneRemplis();
                joueur.incrementeNombreDeLignesRemplisPartie();
                classement(listJoueur);
                joueur.rebootTemoinNombreDeLignesRemplisPartie();
                return true;
            }
        }
        return false;
    }

    protected void placeTuileSurMurPourChaqueJoueur() {
        // On place les tuiles de toutes les lignes motif pleines sur le mur pour chaque joueur
        for (Joueur joueur : listJoueur) {
            for (int ligne = 1; ligne < 6; ligne++) {
                Couleurs couleurs = joueur.getLigneMotifJoueur().getPlateauDeMotif()[ligne - 1][0].getCouleur();
                if (couleurs != Couleurs.VIDE)
                    this.verificationSiLignePleine(ligne, joueur, couleurs);
            }
        }
        this.malusListeJoueur();
    }

    /**
     * méthode qui permet de savoir si une ligne est pleine
     *
     * @param ligne
     * @param joueur
     * @param couleur
     */
    private void verificationSiLignePleine(int ligne, Joueur joueur, Couleurs couleur) {

        if (joueur.getLigneMotifJoueur().estLignePleine(ligne) == 1) {
            AfficherElement.affichageLignePleine(ligne, joueur);
            int pos = joueur.getMurJoueur().getPos(ligne, couleur);
            plateau.remplirCouvercleParExcedentLM(couleur, ligne);
            joueur.getMurJoueur().placeTuileSurMur(ligne, pos, joueur, 1);
            joueur.getCompterPoint().compterPointLigneMotif(joueur, ligne, pos);
            AfficherElement.affichageVerifLignePleine2(joueur);
            AfficherElement.afficheLigneMotifMurPlancher(joueur);
        }
    }

    /**
     * Lance la partie
     */
    public void lancePartie() {
        int nombreDeTour = 1;
        /* =-=-=-=-=-=-= Instenciation des joueurs et des fabriques =-=-=-=-=-=-= */
        if (AfficherElement.nombrePartie > 1)
            plateau.restaurationDesElements(listJoueur, 1);
        plateau.mettreJetonAuCentre();
        AfficherElement.affichageLancePartie(2, listJoueur);
        AfficherElement.affichageManche(getNombreDeManche());
        while (true) {
            boolean dejaPick = false;
            AfficherElement.affichageNbTour(nombreDeTour);
            AfficherElement.affichageCentreEtFabrique(plateau.getCentreDeTable(), plateau.getListeFabrique(), 1);
            for (Joueur joueur : listJoueur) {
                if (posedeJeton == joueur.getNomJoueur() || !debutManche) {
                    debutManche = false;
                    AfficherElement.affichePrintln();
                    AfficherElement.debutTourJoueur(joueur);
                    AfficherElement.affichePrintln();
                    /* =-=-=-=-=-=-= Recuperation des choix de l'IA =-=-=-=-=-=-=*/
                    AfficherElement.afficheLigneMotifPlancher(joueur);
                    IA choixIA = joueur.getIa().choixIA(plateau.getListeFabrique(), joueur.getLigneMotifJoueur(), plateau.getCentreDeTable(), joueur.getMurJoueur());
                    int choixFabriqueOuCentre = choixIA.getChoixFabriqueOuCentre(); //choix de l'endroit où est pioché la tuile
                    if (choixFabriqueOuCentre == 1 && !dejaPick) {
                        Tuile[] tab = plateau.getTuileMemeCouleur(Couleurs.JETON);
                        joueur.getPlancherJoueur().remplirPlancher(Couleurs.JETON, tab.length);
                        dejaPick = true;
                    }
                    Couleurs couleurChoisie = choixIA.getChoixCouleur(); //choix de la couleur
                    int ligneChoisie = choixIA.getLigne(); //choix du placement sur une ligne de motif
                    int nombreOccurenceCouleur;
                    int indexFabrique = 0;
                    /* =-=-=-=-=-=-= Sélectionne une couleur dans une des fabrique =-=-=-=-=-=-= */
                    if (choixFabriqueOuCentre == 0) {
                        indexFabrique = choixIA.getChoixFabrique(); // Recuperation du choix de l'index d'une fabrique
                        nombreOccurenceCouleur = plateau.nombreOccurrenceCouleurList(couleurChoisie, indexFabrique);
                    }
                    /* =-=-=-=-=-=-= Selectione une couleur du centre de la table =-=-=-=-=-=-= */
                    else {
                        /* Recupere le nombre de tuile de la meme couleur a placer */
                        Tuile[] tuilesRecupDuCentre = plateau.getTuileMemeCouleur(couleurChoisie);
                        nombreOccurenceCouleur = tuilesRecupDuCentre.length;
                    }
                    /* =-=-=-=-=-=-= Place les tuiles selectionee sur la ligne de motif selectionee =-=-=-=-=-=-= */
                    int nombreTuileRestante = 0;
                    if (ligneChoisie == 7) {    //correspond au plancher
                        Tuile[] tuiles = joueur.getPlancherJoueur().remplirPlancher(couleurChoisie, nombreOccurenceCouleur);
                        plateau.remplirCouvercle(tuiles);
                    } else {
                        nombreTuileRestante = joueur.getLigneMotifJoueur().placeTuileSurLigne(ligneChoisie, couleurChoisie, nombreOccurenceCouleur, joueur);
                    }
                    // On place les tuiles restantes dans le plancher
                    if (nombreTuileRestante > 0) {
                        Tuile[] tuiles = joueur.getPlancherJoueur().remplirPlancher(couleurChoisie, nombreTuileRestante);
                        plateau.remplirCouvercle(tuiles);
                    }
                    AfficherElement.afficheJeuJoueur(joueur, choixFabriqueOuCentre, nombreOccurenceCouleur, indexFabrique, couleurChoisie, ligneChoisie);
                    /* =-=-=-=-=-=-= Vide la fabrique si le joueur a pioche des tuiles dedans =-=-=-=-=-=-= */
                    if (choixFabriqueOuCentre == 0) {
                        plateau.viderFabriqueList(couleurChoisie, indexFabrique, plateau.getCentreDeTable());
                    }
                    AfficherElement.afficheLigneMotifPlancher(joueur);
                    AfficherElement.affichePrintln();
                    AfficherElement.finTourJoueur(joueur);
                    /* =-=-=-=-=-=-= Termine la manche si toutes les fabriques et le centre sont vides =-=-=-=-=-=-= */
                    if (plateau.verificationSiFabriqueEtCentreVide()) {
                        plateau.mettreJetonAuCentre();
                        return;
                    }
                }
            }
            for (Joueur joueur : listJoueur) {
                nombreDeTour++;
                joueur.incrementeNombreDeTours();
            }
        }
    }

    /**
     * S'occupe de la gestion de la manche
     */
    public void gestionManche() {
        AfficherElement.afficheFinDeManche();
        nombreDeManchePlusUn();
        debutManche = true;
        AfficherElement.affichageManche(getNombreDeManche());
    }
}