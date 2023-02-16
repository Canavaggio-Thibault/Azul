BILAN DE L'ITÉRATION

IMPORTANT : Voici les commandes maven pour lancer notre jeu 
1 partie : mvn clean && mvn compiler:compile && mvn exec:java@1 
500 parties : mvn clean && mvn compiler:compile && mvn exec:java@500p 
mur gris : mvn exec:java@1 -Dmur=gris 
nombre de joueurs : mvn exec:java@1 -DnombreDeJoueur=x (x est variable) 
nombre de partie : mvn exec:java@1 -DnombreDePartie=x (x est variable) 
thread : mvn exec:java@1 -Dthread=x (x est variable) 
on peut combiner tous ces arguments avec la commande suivante : mvn exec:java@1 -DnombreDePartie=x -DnombreDeJoueur=x -Dmur=gris -Dthread=x

Le jeu se lance de base avec un seul thread et avec la variante mur de couleur avec 3 joueurs. 

En terme de fonctionnalités livrées et issues faites : Issues réalisées (se référer au git) :#140,#141, #146, #150, #143, #142 #144, #151, #148, #149 
Issues non réalisées : #145, #152 
Concernant les fonctionnalités : • Le client peut désormais voir le déroulement des parties en thread dans des fichiers différents 
 • Le déroulement de la partie est plus clair 
 • Le robot difficultés 3 a été implémenté et le  joueur numéro 3 joue avec le robot de niveau 3 


En terme de nouveau tests : tests sur différentes classes

Refactoring : quelques petits refactoring 

En terme d'organisation du travail :

• Nicolas : #151, iteration08.md, #143 , #150
• Thomas : #144, #147, #148, #143, #150
• Thibault : #143, #146 , #150
• Ralph : #143, #150
• Matthéo : #149, #143
• Gando : #142