package tuile;

import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import tuile.Tuile;
import utilitaire.Utilitaire;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//La classe TuileTest permet de verifier si les couleurs des  tuiles genérée est bien random
public class TuileTest {
    @Test
    public void CouleurTuileNonVide() {
        /* Genere une tuile de couleur aleatoire 20 fois et verifie qu'elle n'a pas ete creer en VIDE */ 
    	for (int i = 0; i < 20; i++) {
    		Tuile tuile = Utilitaire.getRandom();
    		assertNotEquals(Couleurs.VIDE,tuile.getCouleur());
    	}
    }

    @Test
    public void TuileVide () {
    	/* Créer une tuile vide et vérifie si elle est bien vide */ 
            Tuile tuile = new Tuile();
            assertEquals(Couleurs.VIDE,tuile.getCouleur());
    }

}

