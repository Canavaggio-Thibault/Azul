BILAN:
En termes de fonctionnalités utilisateurs :
Le moteur du jeu est capable de lancer une manche, une manche est constituée d'une IA qui dispose de 7 fabriques dont chacune dispose de 4 tuiles générées de façon aléatoire.
L'IA séléctionne chaque tour une couleur de tuile (c-a-d qu'elle prend toutes les tuiles d'une même couleur dans une fabrique) dans une des fabriques. Quand un type de couleur est choisis par l'IA dans une des fabriques. 

En terme de test :
Nous avons continuer à remplir le package testUnitaire contenant les tests de nos classes.
  • PlancherTest testant la classe plancher qui permet d'essayer : 
  	- remplirPoubelleVide : Vérifie qu'il est possible de remplir le plancher s'il est vide
  	- remplirPoubelleNonVide : Vérifie s'il est possible d'ajouter des tuiles(et dans l'ordre)  à une poubelle qui en possède déjà
  	
  • MilieuDeTableTest permettant les tests de :
  	- remplirCentreVide : Vérifie qu'il est possible de remplir le centre de la table s'il est vide
  	- remplirCentreNonVide : Vérifie s'il est possible d'ajouter des tuiles(et dans l'ordre)  à un centre qui en possède déjà
  	- recupT : Vérifie si on peut récupérer des tuiles de même couleur et les retirer du centre
  	
  • FabriqueTest essaye : 
  	- testFabrique : On vérifie si la valeur de nombre de couleur jaune est bien enlevé de la fabrique
  	
En terme d'organisation du code : 
Il y a une un gros refactoring de l'organisation du programme, au lieu d'avoir deux packages, nous avons créé un package par classe et un package testUnitaireAzul contenant tous les tests.
 
 • Tuiles.java : permet de créer l'objet Tuiles, ayant comme attribut une couleur provenant de l'énumération Couleurs.java. La classe possède deux cosntructeurs, un par défaut créant une tuile de couleur "vide" et un autre constructeur qui permet de choisir sa couleur. Il y a également un accesseur getCouleur et un setCouleur. 

 • LignesMotif.java : permet de créer l'objet LignesMotif, qui est un tableau de tableau de motif. Le 1er tableau correspond au nombre de ligne de motif, et le second tableau correspond au nombre de cases disponible sur cette ligne. C'est à dire, un tableau en escalier : une ligne de une case, une ligne de deux ... une ligne de cinq. Il est possible de récupérer une tuile en particulier avec l'accesseur getCase, ou bien une ligne entière de tuile avec getLigne. La classe permet également de placer une tuile sur une des lignes (le plus à gauche possible) avec placeTuileSurLigne.

 • Mur.java : Classe permettant de créer le mur de 5x5 tuiles avec des tuiles de couleurs. Les couleurs sont disposé avec le quadrillage. Avec setMur nous pouvons déposer dans le mur une tuile. Dans getPos nous donne la couleur à la position demandé. Et enfin afficherMur qui permet d'afficher dans le terminal
 un rendu visuel du tableau avec la position des couleurs et si la tuile est posé (true or false)
 
 • MoteurDeJeu.java : Permet de lancer une manche.
 
 • Manche.java : Permet la gestion de manche avec une seule fabrique et pioche dedans selon la couleur la plus fréquente dans la fabrique et la place dans la ligne motif. La manche se termine quand une des lignes motif est pleine et place la tuile dans le mur.
 
 • Fabrique.java : Permet de créer un object fabrique qui est composé d'un tableau de 4 tuiles de couleurs ramdoms chacune. La partie getTuileDansFabrique permet de récupérer une ou des tuiles de couleurs demander dans la fabrique et le reste des tuiles on les déposes dans le milieu. La méthode pleine permet de vérifier si la fabrique est bien composer de 4 tuiles. 
 
 • milieuDeTable.java : Cette classe permet de créer le centre de ta table et de récupérer les tuiles de même couleurs présente à l'intérieur. La méthode remplir centre permet en recevant un tableau de Tuile de remplir le centre. getTuileMemeCouleur permet de récuperer toutes les tuiles de la même couleur dans le centre. 
 
 • Plancher.java : Cette classe permet de créer le plancher qui est un tableau de 7 tuiles. On rempli le plancher dans la méthode remplirPoubelle qui
rempli le tableau de tuile.

 • utilitaire.java : Cette classe génère un secureRamdom pour la sélection des tuiles
 
 • package testUnitaireAzul : l'ensemble des tests est expliqué dans les commentaires. 
 
 
 En terme d'organisation du travail :
 
  Thibault : Travail sur les itérations, création de la classe Plancher, gestion de fin de manche
  
  Thomas : création et gestion des tuiles dans la fabrique, méthode getTuileDansFabrique, classe utilitaire
  
  Nicolas : Création du centre de la table, méthode remplir, initialisation du milieu de la table
  
  Gando : Gestion du moteur de jeu, création de la gestion des manches et des tours, placement des tuiles en respectant l'unicité de la couleur,
	et coloration du mur
	   
  Ralph : Mise en place des tests, classe utilitaire, placement des tuiles en respectant l'unicité de la couleur
  	