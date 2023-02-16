BILAN DE L'ITÉRATION

IMPORTANT : Voici les commandes maven pour lancer notre jeu
1 partie : mvn clean && mvn compiler:compile && mvn exec:java@1p
500 parties : mvn clean && mvn compiler:compile && mvn exec:java@500p

Le jeu se lance de base avec un seul thread et avec la variante mur de couleur.
Pour lancer une partie avec la variante du mur gris : il faut mofifier la variable "MUR_COULEUR" en "MUR GRIS"  du fichier MoteurDeJeu.Java ligne 14.
Pour lancer deux parties en parrallèle, il faut décommenter les lignes 13, 17 et 19 du MoteurDeJava.java. Mais l'affichage est donc pas clair car il y aura deux parties en même temps.
Nous sommes au courant que ce fonctionnement n'est pas optimal pour le client, mais nous n'avons pas eu le temps de faire autrement et nous allons améliorer celà lors de la prochaine livraison.

Nous sommes également au courant qu'une méthode n'est pas prise en compte lors du comptage des points de la variante mur gris. 


En terme de fonctionnalités livrées et issues faites :
Issues réalisées (se référer au git) : #95 #118 #122 #119 #116 #124 #96 #97 #117 #125 #121 #120 
Issues non réalisées : #96
Concernant les fonctionnalités :
• Des statistiques ont été ajoutées lorsque 500 parties sont lancées
• Il est possible de jouer une partie et 500 parties avec la variante du mur gris 
• Il est possible de lancer deux parties en parallèle
• Le jeton est placé au centre de la table au début d'une manche et le premier joueur qui pioche dans le centre ramasse le jeton en plus qui est placé sur le plancher



En terme de nouveau tests :
• test ajoutés sur IArandom et IAintermediaire
• test ajoutés sur Couvercle
• test ajoutés sur MurGris  


Refactoring :
• Les IAs ont maintenant leurs attributs en commun dans la classe IA abstraite
• Fabrique.java instencie maintenant une seule fabrique au lieu de plusieurs


En terme d'organisation du travail :
• Nicolas : Optimisation du fonctionnement des IAs "random" et « plus de couleur » , Prise en compte de l'état du mur dans les choix des IAs, Refactoring du code des IAs, Correction des bugs de l'IA "plus de couleur" .

• Gando : Gestion de la fin de partie, Adaptation de l'IA random et intermédiaire, L'IA random place les tuiles de gauche à droite,Classe ou méthode de gestion des manches,recupTuilePush() permettant de récupérer les tuiles perdus entre les lignes de motif et le mur lorsqu'une ligne est pleine
• Thomas : Affichage du classement et désignation du vainqueur, Finir la classe d'affichage,Réparer Maven

• Thibault : Tâches corrective, rajouts de tests,Réparer Maven,
• Ralph : Lancer des manches tant qu'une ligne horizontale n'est pas remplit dans le mur, Création de la méthode couleursDejaSurLigne() qui permet de vérifier si la couleur est déjà placée sur la ligne du mur

• Nicolas : correction de bug dans l'IA, tâches correctives, (classe plateau pas implémenté + ses test), jeton (developpé a 2), prises en compte de changement plus mineur
• Gando : ajouts des threads, refactoring pour fabrique
• Thomas : Mur gris et ses tests, refactoring pour l'implémenter et classe abstraite mur, reparation de vider plancher
• Thibault : statistique à la fin des 500 parties 
• Ralph : jeton (developpé a 2)   
• Matthéo : ajouts de tests pour IArandom et IAintermediaire