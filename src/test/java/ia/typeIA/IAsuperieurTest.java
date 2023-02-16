package ia.typeIA;

import controller.joueur.Joueur;
import controller.joueur.Mur;
import controller.joueur.TypeMur;
import controller.manager.CentreDeTable;
import controller.manager.fabrique.Fabrique;
import controller.manager.fabrique.ListeDesFabriques;
import ia.TypeIa;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import utilitaire.AfficherElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IAsuperieurTest {
    ListeDesFabriques fabriques;
    Joueur joueur = new Joueur(1, TypeIa.IA_SUPERIEUR, TypeMur.MUR_COULEUR);
    CentreDeTable centreDeTable = new CentreDeTable();


    public void setUp () {
        /* Création des objets permettant le test */
        Fabrique fabrique1 = new Fabrique(Couleurs.NOIR, Couleurs.NOIR, Couleurs.BLANC, Couleurs.BLANC);
        Fabrique fabrique2 = new Fabrique(Couleurs.JAUNE, Couleurs.JAUNE, Couleurs.ROUGE, Couleurs.BLEU);

        fabriques = new ListeDesFabriques(2, fabrique1, fabrique2);

        centreDeTable = new CentreDeTable();
    }


    @Test
    public void testChoixCouleurDansCentre() {
        centreDeTable.getCentre()[0].setCouleur(Couleurs.ROUGE);
        centreDeTable.getCentre()[1].setCouleur(Couleurs.ROUGE);
        centreDeTable.getCentre()[2].setCouleur(Couleurs.BLEU);
        joueur.getIa().choixCouleurDansCentre(centreDeTable, joueur.getMurJoueur());
        assertEquals(Couleurs.BLEU, joueur.getIa().getChoixCouleur());

        centreDeTable.getCentre()[1].setCouleur(Couleurs.VIDE);
        joueur.getIa().choixCouleurDansCentre(centreDeTable, joueur.getMurJoueur());
        assertEquals(Couleurs.ROUGE, joueur.getIa().getChoixCouleur());


        centreDeTable.getCentre()[0].setCouleur(Couleurs.VIDE);
        joueur.getIa().choixCouleurDansCentre(centreDeTable, joueur.getMurJoueur());
        assertEquals(Couleurs.BLEU, joueur.getIa().getChoixCouleur());

        centreDeTable.getCentre()[3].setCouleur(Couleurs.JAUNE);
        centreDeTable.getCentre()[4].setCouleur(Couleurs.JAUNE);
        centreDeTable.getCentre()[5].setCouleur(Couleurs.JAUNE);
        joueur.getIa().choixCouleurDansCentre(centreDeTable, joueur.getMurJoueur());
        assertEquals(Couleurs.BLEU, joueur.getIa().getChoixCouleur());
    }


    @Test
    public void testChoixPlacementSurLigneDeMotif () {
        joueur.getIa().setNombreTuileAPoser(1);

        // aucune tuile n'est pose sur le mur ni sur les lignes, ligne a remplir : 1
        joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
        assertEquals(1, joueur.getIa().getLigne());

        // ligne 3 de motif a du rouge : ligne a remplir : 3
        joueur.getLigneMotifJoueur().placeTuileSurLigne(3, Couleurs.ROUGE, 1, joueur);
        joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
        assertEquals(3, joueur.getIa().getLigne());

        // ligne 3 de motif a du rouge et ligne 2 du bleu : ligne a remplir : 3
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.BLEU, 1, joueur);
        joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
        assertEquals(3, joueur.getIa().getLigne());

        // ligne 3 de motif a 3 rouges et ligne 2 du bleu : ligne a remplir : 1
        joueur.getLigneMotifJoueur().placeTuileSurLigne(3, Couleurs.ROUGE, 3, joueur);

        joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.ROUGE, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
        assertEquals(1, joueur.getIa().getLigne());

        // pareil mais on veut placer du bleu : ligne a remplir : 2
        joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.BLEU, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
        assertEquals(2, joueur.getIa().getLigne());

        joueur.getLigneMotifJoueur().placeTuileSurLigne(1, Couleurs.BLANC, 3, joueur);
        joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.BLANC, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
        assertEquals(4, joueur.getIa().getLigne());

        joueur.getLigneMotifJoueur().placeTuileSurLigne(4, Couleurs.BLANC, 5, joueur);
        joueur.getLigneMotifJoueur().placeTuileSurLigne(5, Couleurs.BLANC, 5, joueur);

        // tout est plein
        joueur.getIa().choixPlacementCouleurSurLigne(Couleurs.BLANC, joueur.getLigneMotifJoueur(), joueur.getMurJoueur());
        assertEquals(7, joueur.getIa().getLigne());
    }

    @Test
    public void testChoixCouleurListeFabriqueEtLigne () {
        setUp();

        joueur.getMurJoueur().placeTuileSurMur(1,3, joueur, 0);
        joueur.getIa().choixCouleurDansFabrique(fabriques, 1, joueur.getMurJoueur());
        assertEquals(Couleurs.NOIR, joueur.getIa().getChoixCouleur());

        joueur.getIa().choixIA(fabriques, joueur.getLigneMotifJoueur(), centreDeTable, joueur.getMurJoueur());
        assertEquals(Couleurs.NOIR, joueur.getIa().getChoixCouleur());
        assertEquals(2, joueur.getIa().getLigne());


        joueur.getMurJoueur().placeTuileSurMur(2, 4, joueur, 0);
        joueur.getIa().choixIA(fabriques, joueur.getLigneMotifJoueur(), centreDeTable, joueur.getMurJoueur());
        assertEquals(Couleurs.BLANC, joueur.getIa().getChoixCouleur());
        assertEquals(1, joueur.getIa().getLigne());

        joueur.getMurJoueur().placeTuileSurMur(2, 0, joueur, 0);
        joueur.getIa().choixIA(fabriques, joueur.getLigneMotifJoueur(), centreDeTable, joueur.getMurJoueur());
        assertEquals(3, joueur.getIa().getLigne());
    }
}
