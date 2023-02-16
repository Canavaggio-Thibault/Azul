BILAN : 
En terme de fonctionnalités utilisateurs :
Le MoteurDeJeu est capable de lancer une manche, une manche étant constistuée d'une IA posant des tuiles jaunes aléatoirement sur une des lignes de motif. L'IA répète l'opération 
jusqu'à qu'une des lignes de motif soit remplis de tuile jaune. Une fois une ligne complétée, la couleur de la 1ère tuile de la ligne complétée est récupérée et la case correspondante 
(jaune) est remplis dans le mur. 


En terme d'issue :



En terme de test :
Nous avons un package testUnitaireAzul contenant les tests concernant nos classes.
  • LigneMotifTest testant la classe motif, testant plusieurs point : 
	- testPlacementTuileLigne0Case0 : Placer une tuile sur la première case de la premère ligne et vérifier sa couleur (une tuile est placée jaune de base)
	- testPlacementSurCaseInnexistante : Vérifie qu'il n'est pas possible de placer une tuile sur une case qui n'existe pas
	- testOverLoadUneLigne : Place 10 tuiles sur une seule ligne de tuile (une ligne de tuile ne possède que 5 cases au maximum ) et vérifie et la 
					 la ligne est bien pleine de ligne jaune et qu'aucune tuile ne dépasse des cases
	- testLigneMotifVideALaCreation : vérifie que toutes les cases de la ligne de motif soient null à la création
 
 • MurTest testant la classe Mur testant : 
	- testTuilVide : vérifie si le mur a bien été initialisé avec des tuiles vides, au début et à la fin
	- testOutOfBounds : vérifie qu'aucune tuile  n'a été initialisé en out of bounds du mur 5x5


En terme d'organisation du code : 
Il y a deux packages, "azul" contenant les classes permettant de faire fonctionner le jeu, et "testUnitaireAzul" possédant les classes de tests. 
Les classes d'azul : 
 • Tuiles.java : permet de créer l'objet Tuiles, ayant comme attribut une couleur provenant de l'énumération Couleurs.java. La classe possède deux cosntructeurs, un par défaut créant une tuile jaune et un autre constructeur qui permet de choisir sa couleur. Il y a également un accesseur getCouleur et un setCouleur. 


 • LigneMotif.java : permet de créer l'objet LigneMotif, qui est un tableau de tableau de motif. Le 1er tableau correspond au nombre de ligne de motif, et le second tableau correspond au nombre de cases disponible sur cette ligne. C'est à dire, un tableau en escalier : une ligne de une case, une ligne de deux ... une ligne de cinq. Il est possible de récupérer une tuile en particulier avec l'accesseur getCase, ou bien une ligne entière de tuile avec getLigne. La classe permet également de placer une tuile sur une des lignes (le plus à gauche possible) avec placeTuileSurLigne.

 • Mur.java : Classe permettant de créer le mur de 5x5 tuiles avec des tuiles de couleurs "VIDE". Il est possible de modifier la couleur d'une tuile avec la méthode setMur et de récupérer la couleur d'une tuile avec getCaseMur. 

 • MoteurDeJeu.java : Permet de lancer une manche en remplissant aléatoirement une ligne de tuile avec une tuile de couleur jaune. L'opération se répète jusqu'à qu'une ligne soit pleine et récupère la couleur de la 1ère tuile de la ligne pour la déplacer dans la ligne correspondante du mur. 

 package testUnitaireAzul : l'ensemble des tests est expliqué dans les commentaires.





En terme d'organisation du travail :

Thibault : Travail sur les itérations, déroulement des tours et fin de manche

Thomas : La manche se termine quand une des lignes est pleine et une case du mur correspondant est remplis : Mise en place de la fin de la manche, remplissage de gauche à droite, déroulement des tours, stockage et placement des tuiles, placer une tuile aléatoirement sur une des lignes de motif,

Nicolas : Récupération de la couleur,  Création des lignes de motifs, des tests de ligne de motif, remplissage de gauche à droite, énumération de couleur, stockage et placement de la tuile, générer une tuile, création de la classe tuile 

Gando : coloration du mur, récupération des couleurs, stockage et placement de la tuile, Enumération couleur, création de la ligne de motif, placement de la ligne de motif, génération de la classe tuile, générer une tuileénumération de couleur

Ralph : coloration du mur, en tant que systeme je veux pouvoir creer un plateau complet, stockage et placement de la tuile, test unitaire murTEST, classe Mur travail dessus sur la condition et la creation du mur 5*5