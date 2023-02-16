package utilitaire;

import controller.joueur.Joueur;
import controller.joueur.LignesMotif;
import controller.joueur.Mur;
import controller.joueur.Plancher;
import controller.manager.CentreDeTable;
import controller.manager.Couvercle;
import controller.manager.Pioche;
import controller.manager.fabrique.Fabrique;
import controller.manager.fabrique.ListeDesFabriques;
import tuile.Couleurs;

import java.util.LinkedList;


public abstract class AfficherElement {

    public static int nombrePartie;

    public static void afficheLigneMotifMurPlancher(Joueur joueur) {
        /* Parcours toutes les lignes du mur */
        if (nombrePartie > 1)
            return;
        Mur mur = joueur.getMurJoueur();
        LignesMotif lignesMotif = joueur.getLigneMotifJoueur();
        Plancher plancher = joueur.getPlancherJoueur();
        for (int ligne = 1; ligne < 6; ligne++) {
            int k = 0;
            while (k++ < 5 - ligne)
                System.out.print("      ");
            System.out.print("[");
            for (int i = 0; i < ligne; i++) {
                if (i != ligne - 1)
                    System.out.print(AfficherElement.getCouleurTuile(lignesMotif.getPlateauDeMotif()[ligne - 1][i].getCouleur()) + ", ");
                else
                    System.out.print(AfficherElement.getCouleurTuile(lignesMotif.getPlateauDeMotif()[ligne - 1][i].getCouleur()));
            }
            System.out.print("] ");
            System.out.print(" [");
            /* Parcours une ligne et affiche ses couleurs */
            for (int i = 0; i < 5; i++) {
                if (mur.getMur()[ligne - 1][i].getBool()) {
                    if (i != 4)
                        System.out.print(AfficherElement.getCouleurTuile(mur.getMur()[ligne - 1][i].getCouleur()) + ", ");
                    else
                        System.out.print(AfficherElement.getCouleurTuile(mur.getMur()[ligne - 1][i].getCouleur()) + "]");
                } else {
                    if (i != 4)
                        System.out.print(mur.getMur()[ligne - 1][i].getCouleur() + ", ");
                    else
                        System.out.print(mur.getMur()[ligne - 1][i].getCouleur() + "]");
                }
            }
            /* Parcours une ligne et affiche ses tuiles remplis ou non */
            System.out.print("\n");
        }
        System.out.print("\nPlancher de " + joueur.getNomJoueur() + " : [");
        for (int i = 0; i < 7; i++) {
            if (i != 6)
                System.out.print(AfficherElement.getCouleurTuile(plancher.getPlancher()[i].getCouleur()) + ", ");
            else
                System.out.println(AfficherElement.getCouleurTuile(plancher.getPlancher()[i].getCouleur()) + "]\n");
        }
        System.out.println("==> Nombre de point(s) courant de " + joueur.getNomJoueur() + " : " + joueur.getCompterPoint().getPoint());
    }

    public static void affichageCentreEtFabrique(CentreDeTable centreDeTable, ListeDesFabriques fabriques, int afficher) {
        if (nombrePartie > 1)
            return;
        if (afficher == 1) {
            /* =-=-=-=-=-=-= Affichage des fabriques et du centre =-=-=-=-=-=-= */
            System.out.println("\n=-=-=-=-=-=-= Affichage des fabriques et du centre =-=-=-=-=-=-=");
            AfficherElement.affichageDesFabriques(fabriques, afficher);
            System.out.println("Tuiles au centre :");
            AfficherElement.afficheCentre(centreDeTable);
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            /* =-=-=-=-=-=-= Effectue un tour pour chaque joueur =-=-=-=-=-=-= */
        }
    }

    public static void afficheJeuJoueur(Joueur joueur,
                                        int choixFabriqueOuCentre,
                                        int nombreOccurenceCouleur,
                                        int indexFabrique,
                                        Couleurs couleurChoisie,
                                        int ligneChoisie) {
        if (nombrePartie > 1)
            return;
        if (choixFabriqueOuCentre == 0 && ligneChoisie != 7) {
            if(nombreOccurenceCouleur > 1){
            System.out.println("- $> " + joueur.getNomJoueur() + " a pioché " + nombreOccurenceCouleur + " tuiles de couleur "
                    + AfficherElement.getCouleurTuile(couleurChoisie) + " depuis la fabrique numero " + (indexFabrique + 1) + " et pose les tuiles"
                    + " sur la ligne de motif numero " + ligneChoisie);}
            else{
                System.out.println("- $> " + joueur.getNomJoueur() + " a pioché " + nombreOccurenceCouleur + " tuile de couleur "
                        + AfficherElement.getCouleurTuile(couleurChoisie) + " depuis la fabrique numero " + (indexFabrique + 1) + " et pose la tuile"
                        + " sur la ligne de motif numero " + ligneChoisie);
            }
            return;
        }
        if (ligneChoisie == 7) {
                System.out.println("- $> " + joueur.getNomJoueur() + " a pioché " + nombreOccurenceCouleur + " tuiles de couleur "
                        + AfficherElement.getCouleurTuile(couleurChoisie) + " depuis le centre ou la fabrique et pose la/les tuiles dans le plancher");
            } else {
                System.out.println("- $> " + joueur.getNomJoueur() + " a pioché " + nombreOccurenceCouleur + " tuiles de couleur "
                        + AfficherElement.getCouleurTuile(couleurChoisie) + " depuis le centre et pose la/les tuile(s)sur la ligne de motif numero " + ligneChoisie);
            }
        }

    public static void affichePlaceTuileSurMur(Joueur joueur, int afficher) {
        if (nombrePartie > 1)
            return;
        if (afficher == 1)
            System.out.println("Placement d'une tuile sur le mur du " + joueur.getNomJoueur() + ".");
    }

    /**
     * Affiche le centre de la table
     */
    public static void afficheCentre(CentreDeTable centreTable) {
        if (nombrePartie > 1)
            return;
        System.out.print("[");
        for (int i = 0; i < centreTable.getCentre().length; i++) {
            if (centreTable.getCentre()[i].getCouleur() != Couleurs.VIDE)
                System.out.print(AfficherElement.getCouleurTuile(centreTable.getCentre()[i].getCouleur()) + " ");
        }
        System.out.print("]");
    }

    /**
     * affichage du Couvercle
     */

    public static void affichageDuCouvercle(Couvercle coupole) {
        if (nombrePartie > 1)
            return;
        System.out.print("\nAFFICHAGE DU COUVERCLE :[");
        for (int i = 0; i < 100; i++) {
            if (coupole.getCouvercle()[i].getCouleur() != Couleurs.VIDE) {
                if (i != 99)
                    System.out.print(coupole.getCouvercle()[i].getCouleur() + ",");
                else
                    System.out.println(coupole.getCouvercle()[i].getCouleur() + "]\n");
            }
        }
    }

    public static void affichageRestoreFabrique(int afficher) {
        if (nombrePartie > 1)
            return;
        if (afficher == 1)
            System.out.println("=== Restauration de la fabrique à partir de la pioche ===");
    }

    /**
     * Affiche les couleurs des tuiles dans la fabrique
     */
    public static void affichageDesFabriques(ListeDesFabriques fabriques, int afficher) {
        if (nombrePartie > 1)
            return;
        int cpt = 1;
        LinkedList<Fabrique> list = fabriques.getListFabrique();
        if (afficher == 1) {
            System.out.print("Tuiles dans les fabriques : \n");
            for (Fabrique fabrique : list) {
                if (fabrique.getFabrique()[0].getCouleur() != Couleurs.VIDE) {
                    System.out.print("Fabrique n°" + cpt++ + " : [");
                    for (int i = 0; i < 4; i++) {
                        if (i != 3)
                            System.out.print(AfficherElement.getCouleurTuile(fabrique.getFabrique()[i].getCouleur()) + ", ");
                        else
                            System.out.print(AfficherElement.getCouleurTuile(fabrique.getFabrique()[i].getCouleur()));
                    }
                    System.out.println("] ");
                } else {
                    cpt++;
                }
            }
        }
    }

    /**
     * LancePartie après instaciation et création du centre
     *
     * @param nbJoueur
     * @param listJoueur
     */
    public static void affichageLancePartie(int nbJoueur, LinkedList<Joueur> listJoueur) {
        if (nombrePartie > 1)
            return;
        System.out.println(" =-=-=-=-=-=-=-=-=-=-=-=-=-= DÉBUT DE LA PARTIE =-=-=-=-=-=-=-=-=-=-=-=-=-=");
        for (Joueur joueur : listJoueur) {
            System.out.println("Le " + joueur.getNomJoueur() + " a le niveau de diffilculté " + joueur.getIa().getType());
        }
    }

    /**
     * lancePartie après le while(true)
     *
     * @param nbTour
     */
    public static void affichageNbTour(int nbTour) {
        if (nombrePartie > 1)
            return;
        System.out.println("\nTOUR " + nbTour);

    }

    /**
     * verificationSiLignePleine
     *
     * @param ligne
     * @param joueur
     */
    public static void affichageLignePleine(int ligne, Joueur joueur) {
        if (nombrePartie > 1)
            return;
        System.out.print("\n- La ligne " + ligne + " de " + joueur.getNomJoueur() + " est pleine : ");
    }

    /**
     * verificationSiLignePleine 2eme
     *
     * @param joueur
     */
    public static void affichageVerifLignePleine2(Joueur joueur) {
        if (nombrePartie > 1)
            return;
        System.out.println("\n\n- Mur de " + joueur.getNomJoueur() + " : ");
    }

    /**
     * verificationSiFabriqueEtCentreVide
     */
    public static void afficheFinDeManche() {
        if (nombrePartie > 1)
            return;
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-= FIN DE LA MANCHE =-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }


    /**
     * println()
     */
    public static void affichePrintln() {
        if (nombrePartie > 1)
            return;
        System.out.println();
    }

    /**
     * PointAvantMalus
     *
     * @param joueur
     */
    public static void affichePointAvantMalus(Joueur joueur) {
        if (nombrePartie > 1)
            return;
        System.out.println("Nombre de point avant malusPlancher de " + joueur.getNomJoueur() + " : " + joueur.getCompterPoint().getPoint());
    }

    /**
     * PointAprèsMalus
     *
     * @param joueur
     */
    public static void affichePointApresMalus(Joueur joueur) {
        if (nombrePartie > 1)
            return;
        System.out.println("Nombre de point après malusPlancher de " + joueur.getNomJoueur() + " : " + joueur.getCompterPoint().getPoint());
    }

    /**
     * affichage de la pioche
     */
    public static void affichageDelaPioche(Pioche sac, int affichage) {
        if (nombrePartie > 1)
            return;
        if (affichage == 1) {
            System.out.print("\nAFFICHAGE DE LA PIOCHE : [");
            for (int i = 0; i < 100; i++) {
                if (sac.getPioche()[i].getCouleur() != Couleurs.VIDE) {
                    if (i != 99)
                        System.out.print(AfficherElement.getCouleurTuile(sac.getPioche()[i].getCouleur()) + ", ");
                    else
                        System.out.println(AfficherElement.getCouleurTuile(sac.getPioche()[i].getCouleur()) + "]");
                }
            }
        }

    }

    /**
     * Afficher les points
     */
    public static void affichePoint(Joueur joueur) {
        System.out.print(joueur.getCompterPoint().getPoint());
    }

    /**
     * Affiche nbPoints classement
     *
     * @param joueur
     */
    public static void afficheClassement(Joueur joueur) {
        if (nombrePartie > 1)
            return;
        System.out.println("Le " + joueur.getNomJoueur() + " à " + joueur.getCompterPoint().getPoint() + " points");
    }

    /**
     * Affiche le vainqueur
     *
     * @param listjoueur
     * @param nbPoint
     */
    public static void afficheVainqueur(LinkedList<Joueur> listjoueur, int nbPoint) {
        if (nombrePartie > 1)
            return;
        System.out.print(" =-=-=-=   Félicitation à ");
        for (Joueur joueur : listjoueur)
            System.out.print(joueur.getNomJoueur() + ", ");
        System.out.println("qui remporte(nt) la partie avec " + nbPoint + " points   =-=-=-= ");
    }

    public static void affichePlacePlancher(int nbOccurence) {
        if (nombrePartie > 1)
            return;
        System.out.println(nbOccurence + " tuiles placées sur le plancher");
    }

    public static void afficheStatistiqueJoueur(LinkedList<Joueur> listJoueur, int nombreEgalite) {
        System.out.println("\n\n\nNombre de parties gagnées par les joueurs  : ");
        for (Joueur joueur : listJoueur) {
            System.out.println("- " + joueur.getNomJoueur() + " : " + joueur.getNombreDeVictoire() + " victoires");
        }
        if (nombreEgalite > 0)
            System.out.println(nombreEgalite + " Égalités");
    }

    public static void afficheMoyenneDeMalus(LinkedList<Joueur> listJoueur, int nombrePartie) {
        System.out.println("\n\n\nMoyenne de malus : ");
        nombrePartie = AfficherElement.nombrePartie;
        double nombrePartie2 = (double) nombrePartie;
        for (Joueur joueur : listJoueur)
            System.out.println("- " + joueur.getNomJoueur() + " : " + (joueur.getNbPointMalus() / nombrePartie2) / (joueur.getNombreDeManche() / nombrePartie2));
    }

    public static void afficheMoyenneVictoire(LinkedList<Joueur> listJoueur, int nombrePartie) {
        System.out.println("\n\nPourcentages de victoire des joueurs : ");
        nombrePartie = AfficherElement.nombrePartie;
        double nombrePartie2 = (double) nombrePartie;
        for (Joueur joueur : listJoueur) {
            System.out.println("- " + joueur.getNomJoueur() + " : " + joueur.getNombreDeVictoire() * 100 / nombrePartie2 + " %");
        }
    }
    public static void afficheMoyenneLigneRemplis(LinkedList<Joueur> listJoueur, int nombrePartie) {
        System.out.println("\n\n\nMoyenne de ligne : ");
        nombrePartie = AfficherElement.nombrePartie;
        double nombrePartie2 = (double) nombrePartie;
        for (Joueur joueur : listJoueur)
            System.out.println("- " + joueur.getNomJoueur() + " : " + (joueur.getNombreDeLigneRemplis() / nombrePartie2));
    }

    public static void afficheMoyenneDeTour(Joueur joueur, int nombrePartie) {
        nombrePartie = AfficherElement.nombrePartie;
        double nombrePartie2 = (double) nombrePartie;
        System.out.println("\n\n- Moyenne de Tours : " + joueur.getNombreDeTours() / nombrePartie2);
    }


    public static void afficheMoyenneDeManche(Joueur joueur, int nombrePartie) {
        nombrePartie = AfficherElement.nombrePartie;
        double nombrePartie2 = (double) nombrePartie;
        System.out.println("- Moyenne de Manche : " + joueur.getNombreDeManche() / nombrePartie2);
        System.out.println("- Ratio nombreDeTour par nombreDeManche : " + (joueur.getNombreDeTours() / nombrePartie2) / (joueur.getNombreDeManche() / nombrePartie2) + "\n\n\n");

    }

    public static void messageAttente(LinkedList<Joueur> listJoueur) {
        for (Joueur joueur : listJoueur) {
            System.out.println("\n- " + joueur.getNomJoueur() + " : " + joueur.getIa().getType());
        }
        System.out.println("\nVeuillez patienter exécution en cours...");
    }

    /**
     * Affiche début manche
     *
     * @param nombreManche
     */
    public static void affichageManche(int nombreManche) {
        if (nombrePartie > 1)
            return;
        System.out.println("=-=-=-=-=-=-= Début de la manche " + nombreManche + " =-=-=-=-=-=-=");
    }

    /**
     * début du tour
     *
     * @param joueur
     */
    public static void debutTourJoueur(Joueur joueur) {
        if (nombrePartie > 1)
            return;
        System.out.println("-*-*-*-*-*- Début du tour du " + joueur.getNomJoueur() + " -*-*-*-*-*-");
    }

    /**
     * Fin du tour
     *
     * @param joueur
     */
    public static void finTourJoueur(Joueur joueur) {
        if (nombrePartie > 1)
            return;
        System.out.println("-*-*-*-*-*- Fin  du tour du " + joueur.getNomJoueur() + " -*-*-*-*-*-");
    }

    public static void afficheLigneMotifPlancher(Joueur joueur) {
        if (nombrePartie > 1)
            return;
        LignesMotif lignesMotif = joueur.getLigneMotifJoueur();
        Plancher plancher = joueur.getPlancherJoueur();
        for (int ligne = 1; ligne < 6; ligne++) {
            int k = 0;
            while (k++ < 5 - ligne)
                System.out.print("      ");
            System.out.print("[");
            for (int i = 0; i < ligne; i++) {
                if (i != ligne - 1)
                    System.out.print(AfficherElement.getCouleurTuile(lignesMotif.getPlateauDeMotif()[ligne - 1][i].getCouleur()) + ", ");
                else {
                    System.out.print(AfficherElement.getCouleurTuile(lignesMotif.getPlateauDeMotif()[ligne - 1][i].getCouleur()));
                }
            }
            System.out.print("] ");
            System.out.println("");
        }
        System.out.print("\nPlancher de " + joueur.getNomJoueur() + " : [");
        for (int i = 0; i < 7; i++) {
            if (i != 6)
                System.out.print(AfficherElement.getCouleurTuile(plancher.getPlancher()[i].getCouleur()) + ", ");
            else
                System.out.println(AfficherElement.getCouleurTuile(plancher.getPlancher()[i].getCouleur()) + "]\n");
        }
    }

    public static final String COULEUR_ROUGE = "\u001B[3;31m";
    public static final String COULEUR_JAUNE = "\u001B[3;33m";

    public static final String COULEUR_BLEU = "\u001B[3;34m";
    public static final String COULEUR_VIOLET = "\u001B[3;35m";
    public static final String COULEUR_BLACK = "\u001B[3;90m";
    public static final String COULEUR_BLANC = "\u001B[3;97m";
    public static final String COULEURS_GRIS = "\u001B[3;90";
    public static final String COULEUR_JETON = "\u001B[0m";

    /**
     * methode qui permet d'afficher la couleur d'une tuile dans le terminal
     *
     * @param couleur
     * @return
     */
    public static String getCouleurTuile(Couleurs couleur) {
        if (couleur == null)
            return null;
        return switch (couleur) {
            case JAUNE -> COULEUR_JAUNE + "JAUNE\u001B[0m";
            case BLANC -> COULEUR_BLANC + "BLANC\u001B[0m";
            case NOIR -> COULEUR_BLACK + "NOIR\u001B[0m";
            case ROUGE -> COULEUR_ROUGE + "ROUGE\u001B[0m";
            case BLEU -> COULEUR_BLEU + "BLEU\u001B[0m";
            case VIDE -> COULEUR_VIOLET + "VIDE\u001B[0m";
            case GRIS -> COULEURS_GRIS + "GRIS\u001b[0m";
            case JETON -> COULEUR_JETON + "JETON\u001B[0m";

        };
    }

}
