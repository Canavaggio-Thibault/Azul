package controller.manager;

import controller.joueur.Joueur;
import controller.joueur.TypeMur;
import ia.TypeIa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerTest {

    /**
     * Vérifie si le nombre de joueur sélectionner correspond bien
     * au nombre de joueur présent dans la partie
     */
    @Test
    public void verifNombreDeJoueur(){
        Manager manager = new Manager(3, TypeMur.MUR_COULEUR);
        assertEquals(manager.getNombreJoueur(), 3);
    }


    /**
     * Vérifie si une partie est bien null
     */
    @Test
    public void VerifSiMatchNull(){
        Joueur joueur1 = new Joueur(1, TypeIa.IA_RANDOM, TypeMur.MUR_COULEUR);
        Joueur joueur2 = new Joueur(2, TypeIa.IA_INTERMEDIARE, TypeMur.MUR_COULEUR);
        joueur1.getCompterPoint().setNbPoint(18);
        joueur2.getCompterPoint().setNbPoint(18);
        assertEquals(joueur1.getCompterPoint().getPoint(), joueur2.getCompterPoint().getPoint());
    }


}
