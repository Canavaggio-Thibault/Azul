package controller.manager;

import controller.joueur.TypeMur;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import tuile.Tuile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlateauTest {
    private Plateau plateau;
    private Manager manager;


    public void setUp() {
        manager = new Manager(3, TypeMur.MUR_COULEUR);
        plateau = new Plateau(manager);
    }

    @Test
    public void testRestaurationDesElements() {
        setUp();
        Tuile[] tuiles = new Tuile[20];

        // serie de modification du plateau
        for (int i = 0; i < 20; i++) {
            plateau.piochage();
            tuiles[i] = new Tuile();
        }
       plateau.remplirCouvercle(tuiles);
        assertEquals(Couleurs.VIDE, plateau.getCouvercle().getCouvercle()[0].getCouleur()); // verif que le couvercle est vide



        // reset le plateau
       plateau.restaurationDesElements(manager.getListJoueur(),0);



        // verifie que le plateau est bien reset
        for (int i = 0; i < 50; i++) {

            assertEquals(Couleurs.VIDE, plateau.getCouvercle().getCouvercle()[i].getCouleur());
        }


    }
}