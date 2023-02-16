package controller.manager;

import controller.joueur.Joueur;
import controller.joueur.TypeMur;
import ia.TypeIa;
import org.junit.jupiter.api.Test;
import controller.joueur.Plancher;
import tuile.Tuile;
import utilitaire.AfficherElement;
import utilitaire.Utilitaire;
import tuile.Couleurs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class CouvercleTest {
    Couvercle couvercle;
    Tuile[] tab;
    Plancher plancher;

    private final Joueur joueur = new Joueur(1, TypeIa.IA_RANDOM, TypeMur.MUR_COULEUR);

    public void setUp() {
        couvercle = new Couvercle();
        tab = new Tuile[10];
        plancher = new Plancher();
        couvercle = new Couvercle();
    }

    @Test
    public void testCoupole() {
        setUp();
        //AfficherElement.affichageDuCouvercle(coupole);
        assertEquals(100, couvercle.getCouvercle().length);
    }

    @Test
    public void testCouvercle() {
        setUp();
        Couleurs couleurs = Couleurs.VIDE;
        for (int i = 0; i < tab.length; i++) {
            tab[i] = Utilitaire.getRandom();
            /* Remplis le plancher avec tab */
        }
        couvercle.remplirCouvercle(tab);
        assertEquals(90, couvercle.nombreOccurenceCouleurCouvercle(couleurs));
    }

    @Test
    /**
     * Test si le couvercle se remplit bien de 3 tuiles noirs si l'ont pose 3 tuiles noir dans un plancher avec 7 tuiles
     */
    public void remplirCouvercleAvecTuilesVenantDePlancherExcedant1() {
        setUp();
        Tuile[] tuiles;
        plancher.remplirPlancher(Couleurs.JAUNE, 7);

        tuiles = plancher.remplirPlancher(Couleurs.NOIR, 3);
        couvercle.remplirCouvercle(tuiles);
        for (int i = 0; i < 2; i++) {
            assertEquals(Couleurs.NOIR, couvercle.getCouvercle()[i].getCouleur()); // 3 tuiles noir
        }
        //assertEquals(Couleurs.VIDE, couvercle.getCouvercle()[4].getCouleur()); //4eme tuile vide
    }

    @Test
    /**
     * Test si le couvercle se remplit bien de 3 tuiles noirs si l'ont pose 4 tuiles noir dans un plancher avec 6 tuiles
     */
    public void remplirCouvercleAvecTuilesVenantDePlancherExcedant2() {
        setUp();
        Tuile[] tuiles;
        plancher.remplirPlancher(Couleurs.JAUNE, 6);
        tuiles = plancher.remplirPlancher(Couleurs.NOIR, 4);
        couvercle.remplirCouvercle(tuiles);
        for (int i = 0; i < 3; i++) {
            assertEquals(Couleurs.NOIR, couvercle.getCouvercle()[i].getCouleur());  //3 tuiles noir
        }
        assertEquals(Couleurs.VIDE, couvercle.getCouvercle()[4].getCouleur()); //4eme tuile vide

    }

    @Test
    /**
     * Test si le couvercle est bien mélanger
     */
    public void remettrePlancherDansCouvercle() {
        Couvercle couvercle = new Couvercle();
        Tuile[] tuiles = joueur.getPlancherJoueur().remplirPlancher(Couleurs.BLEU, 3);
        couvercle.remplirCouvercle(tuiles);
        Tuile[] plancher = joueur.getPlancherJoueur().getPlancher();
        couvercle.remettrePlancherDansCouvercle(plancher);
        assertEquals(Couleurs.VIDE, plancher[0].getCouleur());
    }
/**
Test si le le couvercle remplit bien la pioche puis melange
 */
    @Test
    public void remplirPiocheAvecCouvercle() {
        Couvercle couvercle = new Couvercle();
        Pioche pioche1 = new Pioche();
        couvercle.remplirCouvercle(pioche1.getPioche());
        Pioche pioche = new Pioche();
        pioche.viderPioche();
        couvercle.remplirPiocheAvecCouvercle(pioche);

        //Ia prend des tuiles de la pioche
        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.BLEU));
        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.ROUGE));
        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.NOIR));
        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.JAUNE));
        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.BLANC));

    }
}