package controller.joueur;

import controller.joueur.typeMur.MurGris;
import ia.TypeIa;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import utilitaire.AfficherElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MurGrisTest {
    private Joueur joueur = new Joueur(2, TypeIa.IA_RANDOM, TypeMur.MUR_GRIS);

    @Test
    public void VerificationMurGris1() {
        Mur murGris = joueur.getMurJoueur();
        assertEquals(murGris.getMur()[3][0].getCouleur(), Couleurs.GRIS);
    }

    @Test
    public void testVerificationMurGris3() {
        Mur murGris = joueur.getMurJoueur();
        assertEquals(murGris.getMur()[4][4].getCouleur(), Couleurs.GRIS);
    }

    @Test
    public void testVerificationMurGris2() {
        Mur murGris = joueur.getMurJoueur();
        assertEquals(murGris.getMur()[0][3].getCouleur(), Couleurs.GRIS);
    }

    @Test
    public void testVerificationMurGris4() {
        Mur murGris = joueur.getMurJoueur();
        assertNotEquals(murGris.getMur()[2][1].getCouleur(), Couleurs.JAUNE);
    }

    @Test
    public void testPlaceTuileSurMurGris1() {
        int ligne = 3;
        joueur.getLigneMotifJoueur().placeTuileSurLigne(ligne, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(ligne, 2, joueur, 0);
        assertEquals(joueur.getMurJoueur().getMur()[ligne-1][2].getBool(), true);
    }

    @Test
    public void testPlaceTuileSurMurGris2() {
        int ligne = 3;
        joueur.getLigneMotifJoueur().placeTuileSurLigne(ligne, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(ligne, 4, joueur, 0);
        joueur.getLigneMotifJoueur().placeTuileSurLigne(ligne - 1, Couleurs.BLANC, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(ligne - 1, 4, joueur, 0);
        assertEquals(joueur.getMurJoueur().getMur()[ligne-1][4].getBool(), true);
        assertEquals(joueur.getMurJoueur().getMur()[ligne - 1][4].getBool(), true);
    }

    @Test
    public void testCouleurDejaPoserLigne1() {
        int ligne = 2;
        joueur.getLigneMotifJoueur().placeTuileSurLigne(ligne - 1, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(ligne - 1, 4, joueur, 0);
        assertEquals(joueur.getMurJoueur().couleurDejaPoserLigne(ligne-1, Couleurs.BLEU), true);
    }

    @Test
    public void testCouleurDejaPoserLigne2() {
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(2, 4, joueur, 0);
        assertEquals(joueur.getMurJoueur().couleurDejaPoserLigne(1, Couleurs.BLEU), false);
    }

    @Test
    public void testCouleurDejaPoserColonne1() {
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(2, 4, joueur, 0);
    }

    @Test
    public void testCouleurDejaPoserColonne2() {
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(2, 4, joueur, 0);
    }

    @Test
    public void testCouleurDejaPoser1() {
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(2, 4, joueur, 0);
        assertEquals(joueur.getMurJoueur().couleurDejaPoserLigne(2, Couleurs.BLEU), true);
    }

    @Test
    public void testCouleurDejaPoser2() {
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(2, 4, joueur, 0);
        assertEquals(joueur.getMurJoueur().couleurDejaPoserLigne(2, Couleurs.BLEU), true);
    }

    @Test
    public void testCouleurDejaPoser3() {
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(2, 4, joueur, 0);
        assertEquals(joueur.getMurJoueur().couleurDejaPoserLigne(2, Couleurs.BLEU), true);
    }

    @Test
    public void testSiLignePleine() {
        for (int i = 0; i < 5; i++) {
            joueur.getLigneMotifJoueur().placeTuileSurLigne(1, Couleurs.BLEU, 1, joueur);
            joueur.getMurJoueur().placeTuileSurMur(1, i, joueur, 0);
        }
        assertEquals(joueur.getMurJoueur().verificationSiUneLigneEstPleine(), true);
    }

    @Test
    public void testCouleurDejaPoserColonne(){
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2,Couleurs.BLEU,1,joueur);
        joueur.getMurJoueur().placeTuileSurMur(2,2,joueur,0);
        joueur.getLigneMotifJoueur().placeTuileSurLigne(3,Couleurs.BLEU,1,joueur);
        joueur.getMurJoueur().placeTuileSurMur(3,2,joueur,0);
        joueur.getLigneMotifJoueur().placeTuileSurLigne(5,Couleurs.BLEU,1,joueur);
        joueur.getMurJoueur().placeTuileSurMur(5,2,joueur,0);
        joueur.getLigneMotifJoueur().placeTuileSurLigne(4,Couleurs.BLEU,1,joueur);
        joueur.getMurJoueur().placeTuileSurMur(4,2,joueur,0);
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2,Couleurs.ROUGE,1,joueur);
        joueur.getMurJoueur().placeTuileSurMur(2,2,joueur,0);
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2,Couleurs.JAUNE,1,joueur);
        joueur.getMurJoueur().placeTuileSurMur(2,2,joueur,0);
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2,Couleurs.NOIR,1,joueur);
        joueur.getMurJoueur().placeTuileSurMur(2,2,joueur,0);
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2,Couleurs.BLANC,1,joueur);
        joueur.getMurJoueur().placeTuileSurMur(2,2,joueur,0);
        assertEquals(joueur.getMurJoueur().getMur()[2-1][2].getCouleur(), Couleurs.BLEU);
        assertEquals(joueur.getMurJoueur().getMur()[3-1][3].getCouleur(), Couleurs.BLEU);
        assertEquals(joueur.getMurJoueur().getMur()[5-1][4].getCouleur(), Couleurs.BLEU);
        assertEquals(joueur.getMurJoueur().getMur()[4-1][0].getCouleur(), Couleurs.BLEU);
        assertEquals(joueur.getMurJoueur().getMur()[2-1][3].getCouleur(), Couleurs.ROUGE);
        assertEquals(joueur.getMurJoueur().getMur()[2-1][4].getCouleur(), Couleurs.JAUNE);
        assertEquals(joueur.getMurJoueur().getMur()[2-1][0].getCouleur(), Couleurs.NOIR);
        assertEquals(joueur.getMurJoueur().getMur()[2-1][1].getCouleur(), Couleurs.BLANC);
    }

}
