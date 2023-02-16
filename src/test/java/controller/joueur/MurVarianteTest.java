package controller.joueur;

import ia.TypeIa;
import org.junit.jupiter.api.Test;
import tuile.Couleurs;
import utilitaire.AfficherElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MurVarianteTest {
    Joueur joueur = new Joueur(2, TypeIa.IA_INTERMEDIARE, TypeMur.MUR_VARIANTE);

    @Test
    public void testCouleurLigneTuileFixe() {
        //On place une tuile blanche sur la ligne 3 colonne 4
        joueur.getLigneMotifJoueur().placeTuileSurLigne(3, Couleurs.BLANC, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(3, 4, joueur, 0);
        //La tuile est placée sur sa place prédéfini par le mur donc sur la ligne 3 colonne 2
        assertEquals(joueur.getMurJoueur().getMur()[2][2].getCouleur(), Couleurs.BLANC);

        //On place une tuile blanche sur la ligne 4 colonne 1
        joueur.getLigneMotifJoueur().placeTuileSurLigne(4, Couleurs.BLANC, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(4, 1, joueur, 0);
        /*On a envie de poser la tuile sur une place prédéfinie pour une tuile noir donc on la déplace,
         ensuite on veut la placer dans la colonne ou le blanc est déjà poser donc on déplace encore,
          on retombe sur une case prédéfie donc on redéplace, Du coup on la pose dans la colonne 4*/
        assertEquals(joueur.getMurJoueur().getMur()[3][4].getCouleur(), Couleurs.BLANC);

        //On place une tuile bleu sur la ligne 2 colonne 0
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(2, 0, joueur, 0);
        //La tuile est placée sur sa place prédéfini par le mur donc sur la ligne 2 colonne 1
        assertEquals(joueur.getMurJoueur().getMur()[1][1].getCouleur(), Couleurs.BLEU);

        //On place une tuile bleu sur la ligne 3 colonne 1
        joueur.getLigneMotifJoueur().placeTuileSurLigne(3, Couleurs.BLEU, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(3, 1, joueur, 0);
        /*On a envie de poser la tuile dans une colonne où la couleur est déjà poser donc on déplace,
         ensuite on veut la placer dans sur une tuile déjà placer donc on déplace encore,
          maintenant on peut la placer, Du coup on la pose dans la colonne 3*/
        assertEquals(joueur.getMurJoueur().getMur()[2][3].getCouleur(), Couleurs.BLEU);

        //On place une tuile rouge sur la ligne 2 colonne 0
        joueur.getLigneMotifJoueur().placeTuileSurLigne(2, Couleurs.ROUGE, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(2, 0, joueur, 0);
        //La tuile de cette couleur à un emplacement précis sur la ligne donc on la place sur cette emplacement
        assertEquals(joueur.getMurJoueur().getMur()[1][3].getCouleur(), Couleurs.ROUGE);

        //On place une tuile rouge sur la ligne 4 colonne 3
        joueur.getLigneMotifJoueur().placeTuileSurLigne(4, Couleurs.ROUGE, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(4, 3, joueur, 0);
        /*
        On place la tuile sur un emplacement prédéfit et où la couleur est dejà poser dans la colonne donc on la décale,
        Ensuite on veut la placer sur une tuile déjà poser donc en déplace,
        Du coup on peut poser la tuile sur la nouvelle colonne qui est la 0
         */
        assertEquals(joueur.getMurJoueur().getMur()[3][0].getCouleur(), Couleurs.ROUGE);

        //On place une tuile noire sur la ligne 4 colonne 0
        joueur.getLigneMotifJoueur().placeTuileSurLigne(4, Couleurs.NOIR, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(4, 0, joueur, 0);
        //La tuile à un emplacement prédéfinie dans la ligne donc on pose dessus
        assertEquals(joueur.getMurJoueur().getMur()[3][1].getCouleur(), Couleurs.NOIR);

        //On place une tuile noire sur la ligne 3 colonne 1
        joueur.getLigneMotifJoueur().placeTuileSurLigne(3, Couleurs.NOIR, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(3, 1, joueur, 0);
        /*
        On place la tuile dans une colonne où la couleur est déjà placer donc on décale,
        Une couleur est déjà placer dans la nouvelle colonne donc on décale,
        Une couleur est déjà placer dans la nouvelle colonne donc on décale,
        On peut enfin placer la tuile dans la colonne 4
         */
        assertEquals(joueur.getMurJoueur().getMur()[2][4].getCouleur(), Couleurs.NOIR);

        //On place une tuile jaune sur la ligne 4 colonne 0
        joueur.getLigneMotifJoueur().placeTuileSurLigne(4, Couleurs.JAUNE, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(4, 0, joueur, 0);
        //La tuile à un emplacement prédéfinie dans la ligne donc on pose dessus
        assertEquals(joueur.getMurJoueur().getMur()[3][3].getCouleur(), Couleurs.JAUNE);

        //On place une tuile jaune sur la ligne 3 colonne 1
        joueur.getLigneMotifJoueur().placeTuileSurLigne(3, Couleurs.JAUNE, 1, joueur);
        joueur.getMurJoueur().placeTuileSurMur(3, 1, joueur, 0);
        //On place la tuile sur l'emplacement voulu car aucune tuile est poser a cette emplacement et aucune tuile de même couleur dans la colonne
        assertEquals(joueur.getMurJoueur().getMur()[2][1].getCouleur(), Couleurs.JAUNE);

        /*On vérifie si le bonus de point gagner par en comptant la ligne 4 colonne 1,
        * Vu qu'il y a 3 tuiles autour de la tuile noir on ajoute 4 points mais vu que c'est sur un emplacement prédéfinie
        * On double le nombre de points gagner
        * */
        joueur.getCompterPoint().compterPointLigneMotif(joueur, 4, 1);

        //On voit si les bonus sont bien appliqué
        assertEquals(joueur.getCompterPoint().getPoint(), 8);
    }

}
