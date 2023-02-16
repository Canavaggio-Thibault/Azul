package controller.manager;

import controller.manager.fabrique.Fabrique;
import controller.manager.fabrique.ListeDesFabriques;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import utilitaire.AfficherElement;

import static org.junit.jupiter.api.Assertions.*;

public class ListeDesFabriquesTest {
    Pioche pioche;
    ListeDesFabriques fabrique;

    public void setUp() {
        pioche = new Pioche();
        fabrique = new ListeDesFabriques(2, pioche);

    }

    @Test
    public void ajouterFabrique() {
        //creer une pioche
        setUp();
        fabrique.addFabrique(new Fabrique(pioche));
        assertEquals(6, fabrique.getListFabrique().size());
    }
    @Test
    public void listeFabriqueVide() {
        setUp();
    fabrique.viderFabriqueList(Couleurs.BLANC,0, new CentreDeTable());
    fabrique.viderFabriqueList(Couleurs.BLANC,1, new CentreDeTable());
    fabrique.viderFabriqueList(Couleurs.BLANC,2, new CentreDeTable());
    fabrique.viderFabriqueList(Couleurs.BLANC,3, new CentreDeTable());
    fabrique.viderFabriqueList(Couleurs.BLANC,4, new CentreDeTable());
    assertEquals(true, fabrique.verifieSiListeFabriqueVide());
    }
}