package azul;

import controller.joueur.TypeMur;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.Executors;

/** Classe main permettant de lancer le jeu */
public class MoteurDeJeu {

	public static void main(String[] args) {

		TypeMur typeMur = TypeMur.MUR_COULEUR;
		if (Objects.equals(args[2], "gris"))
			 typeMur = TypeMur.MUR_GRIS;
		else if (Objects.equals(args[2], "variante")) {
			typeMur = TypeMur.MUR_VARIANTE;
		}
		var runnable = new RunnableClass(args, typeMur);
		int nb_thread = Integer.parseInt(args[3]);
		for (int i = 0; i < nb_thread; i++){
			var service1 = Executors.newSingleThreadExecutor();
			service1.execute(runnable);
			service1.shutdown();
		}
	}
}
