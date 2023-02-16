BILAN DE L'ITÉRATION

IMPORTANT : Voici les commandes maven pour lancer notre jeu
1 partie : mvn clean && mvn compiler:compile && mvn exec:java@1
500 parties : mvn clean && mvn compiler:compile && mvn exec:java@500p
mur gris : mvn exec:java@1 -Dmur=gris
nombre de joueurs : mvn exec:java@1 -DnombreDeJoueur=x (x est variable)
nombre de partie : mvn exec:java@1 -DnombreDePartie=x (x est variable)
thread : mvn exec:java@1 -Dthread=x (x est variable)
on peut combiner tous ces arguments avec la commande suivante :
mvn exec:java@1 -DnombreDePartie=x -DnombreDeJoueur=x -Dmur=gris -Dthread=x

Le jeu se lance de base avec un seul thread et avec la variante mur de couleur.
L'IA niveau 3 n'est pas implémenté et n'a pas été push un jour entier car il y a encore des bugs non résolu qui empêche l'exécution du jeu.

En terme de fonctionnalités livrées et issues faites :
Issues réalisées (se référer au git) :#117 #129,#130,#135,#138,#139,#140,#141,
Issues non réalisées : 
Concernant les fonctionnalités :
• Le client peut lancer le jeu avec différent types de mur avec les commandes ci-dessus
• Le client a désormais la variante 2 du mur 


En terme de nouveau tests :
• test ajoutés sur IArandom
• test ajoutés sur Couvercle
• test ajoutés sur MurGris 
. test ajoutés sur Manager
. test ajoutés sur MoteurdeJeu
. test ajoutés sur IASupérieur
. test ajoutés sur ListedesFabriques
. test ajoutés sur Plateau


Refactoring :


En terme d'organisation du travail :

• Nicolas :Création d'un IA niveau 3,Classe plateau + ses test 
• Gando : Tâches correctives sur les threads, et modification du pom.xml
• Thomas : Modification du plancher pour la variante 2, Création du mur variante 2, Lancer partie variante 2, mise en place des points bonus pour la variante 2,plancher variante 2
• Thibault : Réécrire la méthode tuile5Couleurs pour les 10 points afin de prendre en compte le mur gris et la variante, Épuration de l'affichage, comptage des points
• Ralph : Itération7.md, JavaDoc, Ajout de test sur le couvercle, création de classe test pour liste des fabriques + tests
• Matthéo : Correction du bug jeton, ajout de test sur Manager et Moteur de Jeu
