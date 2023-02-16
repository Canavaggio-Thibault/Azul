package controller.manager;
/**
 * Test unitaires sur la fabrique et ses méthodes
 */

import controller.manager.fabrique.Fabrique;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FabriqueTest {

	@Test
	/**
	 * Vérifie que la fabrique se vide et que les tuiles non piochées sont placées au centre de la table
	 */
	public void testViderFabrique() {
		Fabrique fabrique = new Fabrique(new Pioche());
		CentreDeTable centre = new CentreDeTable();
		fabrique.viderFabrique(Couleurs.JAUNE, centre);
		/* Vérif que la fabrique est dévenue vide */
		for (int i = 0; i < 4; i++) {
			assertEquals(fabrique.getFabrique()[i].getCouleur(), Couleurs.VIDE);
		}
	}

	@Test
	public void testNombreTuileDansFabrique (){

		Fabrique fabrique = new Fabrique(new Pioche());
		assertEquals(4,fabrique.getFabrique().length);
	}
	@Test
	public void verifieSiFabriqueVideTest (){
		Pioche pioche = new Pioche();
		Fabrique fabriques = new Fabrique(pioche);
		assertFalse(fabriques.verifieSiFabriqueVide());
	}
}
