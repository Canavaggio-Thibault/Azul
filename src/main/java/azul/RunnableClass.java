package azul;

import controller.joueur.TypeMur;
import controller.manager.Manager;
import utilitaire.AfficherElement;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RunnableClass implements Runnable{
    /**
     * Lorsqu'un objet implémentant l'interface {@code Runnable} est utilisé
     * pour créer un thread, le démarrage du thread provoque l'appel de la méthode
     * {@code run} de l'objet dans ce fil d'exécution séparé.
     * Thread.
     * Le contrat général de la méthode {@code run} est qu'elle peut .
     * Prendre n'importe quelle action, quelle qu'elle soit.
     *
     * @see Thread#run()
     */

    private final String[] args;
    private final TypeMur typeMur;

    public RunnableClass(String[] args, TypeMur typeMur) {
        this.args = args;
        this.typeMur = typeMur;
    }

    public void execute(){
        int nombreJoueur = Integer.parseInt(args[1]);
        Manager manager = new Manager(nombreJoueur, typeMur);
        int compteur = Integer.parseInt(args[0]);
        AfficherElement.nombrePartie = compteur;
        if (compteur > 2)
            AfficherElement.messageAttente(manager.getListJoueur());
        while (compteur-- > 0) {
            manager.lancePartie();
        }
        if(AfficherElement.nombrePartie > 1) {
            AfficherElement.afficheStatistiqueJoueur(manager.getListJoueur(), manager.getNombreMatchNull());
            AfficherElement.afficheMoyenneVictoire(manager.getListJoueur(), manager.getNombreMatchNull());
            AfficherElement.afficheMoyenneDeMalus(manager.getListJoueur(), manager.getNombreMatchNull());
            AfficherElement.afficheMoyenneLigneRemplis(manager.getListJoueur(), manager.getNombreMatchNull());
            AfficherElement.afficheMoyenneDeTour(manager.getListJoueur().getFirst(), manager.getNombreMatchNull());
            AfficherElement.afficheMoyenneDeManche(manager.getListJoueur().getFirst(), manager.getNombreMatchNull());
        }
    }

    @Override
    public void run() {

        if (Integer.parseInt(args[3]) > 1) {
            try {
                // Créer un fichier texte où System.out.println()
                // enverra ses données pour ce thread.
                ThreadPrintStream.createDir();
                String repo = "Files/";
                String name = repo + Thread.currentThread().getName();
                FileOutputStream fos = new FileOutputStream(name + ".txt");

                // Créez un PrintStream qui écrira dans le nouveau fichier.
                PrintStream stream = new PrintStream(new BufferedOutputStream(fos));

                ThreadPrintStream.replaceSystemOut();

                // Installez le PrintStream qui sera utilisé comme System.out pour ce thread.
                ((ThreadPrintStream)System.out).setThreadOut(stream);

                this.execute();
                // Fermer System.out pour ce thread qui va
                // vider et fermer le fichier texte de ce thread.
                System.out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else
            this.execute();

    }
}
