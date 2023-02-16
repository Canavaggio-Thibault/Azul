package controller.manager;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;

import static org.junit.jupiter.api.Assertions.*;

public class PiocheTest {
    @Test
    public void testPioche() {
        Pioche pioche = new Pioche();
        assertEquals(100, pioche.getPioche().length);
    }

    @Test

    public void testPioche2() {

        Pioche pioche = new Pioche();

        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.BLANC));
        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.ROUGE));
        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.BLEU));
        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.JAUNE));
        assertEquals(20, pioche.nombreOccurrenceCouleur(Couleurs.NOIR));

    }

    @Test
    public void testMelangePioche() {
        Pioche pioche = new Pioche();
        Pioche pioche1 = new Pioche();
        boolean estMelange = false;
        pioche.melangerSac();
        for (int i = 0; i < 30; i++) {
            if (pioche.getPioche()[i].getCouleur() != pioche1.getPioche()[i].getCouleur()) {
                estMelange = true;
            }
        }
        assertTrue(estMelange);
    }
}
