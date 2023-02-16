package controller.manager;

import controller.joueur.Joueur;
import controller.manager.fabrique.ListeDesFabriques;
import tuile.Couleurs;
import tuile.Tuile;
import utilitaire.AfficherElement;

import java.util.LinkedList;

public class Plateau {
    private final ListeDesFabriques listeDesFabriques;
    private Pioche pioche;
    private final CentreDeTable centreDeTable;
    private final Couvercle couvercle;
    private final Manager manager;

    /**
     * Constructeur de la classe Plateau
     *
     * @param manager
     */
    public Plateau(Manager manager) {
        pioche = new Pioche();
        couvercle = new Couvercle();
        listeDesFabriques = new ListeDesFabriques(manager.getNombreJoueur(), pioche);
        centreDeTable = new CentreDeTable();
        this.manager = manager;
    }

    public Pioche getPioche() {
        return this.pioche;
    }


    public ListeDesFabriques getListeFabrique() {
        return listeDesFabriques;
    }

    public CentreDeTable getCentreDeTable() {
        return this.centreDeTable;
    }

    public Couvercle getCouvercle() {
        return this.couvercle;
    }

    /**
     * méthode qui permet de restaurer les éléments des joueurs dans le plateau
     *
     * @param listJoueur
     */
    public void restaurationDesElements(LinkedList<Joueur> listJoueur, int affichage) {
        for (Joueur joueur : listJoueur) {
            joueur.getLigneMotifJoueur().restaureLignesMotif();
            joueur.getMurJoueur().restaureMur();
            joueur.getPlancherJoueur().restaurePlancher();
            joueur.getCompterPoint().setNbPoint(0);
        }
        centreDeTable.restaureCentreDeTable();
        pioche.restaurePioche();
        couvercle.restaureCouvercle();
        listeDesFabriques.restoreListeFabriques(pioche, affichage);
        AfficherElement.affichageRestoreFabrique(affichage);
        AfficherElement.affichageCentreEtFabrique(centreDeTable, listeDesFabriques, affichage);
    }

    /**
     * méthode qui permet de vérifier si la fabrique et le centre sont vides
     *
     * @return vrai si le centre et la fabrique sont vide et faux si non vide
     */
    protected boolean verificationSiFabriqueEtCentreVide() {
        boolean fabriquesVide = false;
        boolean centreVide = false;
        // Vérification si la fabrique est vide
        if (listeDesFabriques.verifieSiListeFabriqueVide())
            fabriquesVide = true;
        // Vérification si le centre de la table est vide
        if (centreDeTable.verifieSiCentretableVide())
            centreVide = true;
        // si des tuiles sont encore presente
        if (!centreVide || !fabriquesVide)
            return false;
        for (Joueur joueur : manager.getListJoueur()) {
            joueur.incrementeNombreDeManche();
        }

        // remplit le mur avec les lignes de motif si tout est vide
        manager.placeTuileSurMurPourChaqueJoueur();
        centreDeTable.mettreJetonAuCentre();
        if (manager.verificationSiPartieEstFinie())
            return true;
        manager.gestionManche();
        // Si le centre et les fabriques sont vides on restaure les fabriques à partir de la pioche

        couvercle.melangerCouvercle();
        couvercle.remplirPiocheAvecCouvercle(pioche);
        pioche.melangerSac();
        listeDesFabriques.restoreListeFabriques(pioche, 0);
       // AfficherElement.affichageDelaPioche(pioche, 1);
        return false;

    }

    public void remplirCouvercle(Tuile[] tuiles) {
        couvercle.remplirCouvercle(tuiles);
    }

    public void mettreJetonAuCentre() {
        centreDeTable.mettreJetonAuCentre();
    }

    public Tuile[] getTuileMemeCouleur(Couleurs jeton) {
        return centreDeTable.getTuileMemeCouleur(jeton);
    }

    public void remettrePlancherDansCouvercle(Tuile[] plancher) {
        couvercle.remettrePlancherDansCouvercle(plancher);
    }
    public int nombreOccurrenceCouleurList(Couleurs couleurChoisie, int indexFabrique) {
        return listeDesFabriques.nombreOccurrenceCouleurList(couleurChoisie, indexFabrique);
    }

    public void viderFabriqueList(Couleurs couleurChoisie, int indexFabrique, CentreDeTable centreDeTable) {
        listeDesFabriques.viderFabriqueList(couleurChoisie, indexFabrique, centreDeTable);
    }

    public void piochage() {
        pioche.piochage();
    }

    public void remplirCouvercleParExcedentLM(Couleurs couleur, int ligne) {
        couvercle.remplirCouvercleParExcedentLM(couleur, ligne);
    }

}

