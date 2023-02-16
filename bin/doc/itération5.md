iteration05.md groupe c azul

BILAN DE L'ITÉRATION

IMPORTANT : Voici les commandes maven pour lancer notre jeu
1 partie : mvn clean && mvn compiler:compile && mvn exec:java@1p
500 parties : mvn clean && mvn compiler:compile && mvn exec:java@500p

En terme de fonctionnalités livrées et issues faites :
Issues réalisées (se référer au git) : #63 #66 #72 #74 #75 #76 #78 #91 #92 #93 #94 #98 #99 #100 #109 #112 #111 #106 #115 #113 
Concernant les fonctionnalités ajoutées:
• Lors de la passation de la ligne de motif au mur les tuiles restantes sont désormais mis dans le couvercle. Le couvercle remplit ensuite la pioche
• On peut désormais jouer plusieurs manches d’affilés et finir la partie lorsqu'une ligne du mur est pleine
• Les IA prennent en compte les tuiles déjà posées sur le mur pour réaliser leurs choix (une tuile bleue ne sera pas posée sur une ligne de motif dont la tuile bleue du mur est déjà remplie)
• Il y a désormais un affichage du classement et désigne un vainqueur.
• Possibilité de lancer 1 ou 500 parties. Une partie montrera le déroulement de la partie et 500 parties n'affichera que le classement.

En terme de nouveau tests :
• testMelangePioche vérifie que le mélange de la pioche est fonctionnelle.
. RemplirPlancherUneCouleur remplit le plancher avec 3 tuiles et vérifie si les 3 tuiles sont bien placés.
. verifieSiFabriqueVideTest vérifie lors de la création d’une fabrique soit pleine

En terme d'organisation du code et de refactoring :
• Organisation du code :
- Package azul :
• MoteurDeJeu : permet de lancer une partie avec le nombre de joueur souhaité avec le constructeur Manager(nbDeJoueur)
//
- Package controller.joueur : contient tous les attributs que les joueurs possèdent
• Joueur : Classe qui définit les differents attribut d'un joueur comme son mur, ses lignes de motifs, son nom, son plancher et surtout son niveau d'IA
• LignesMotif : permet de créer des lignes de motif en escalier et de les remplir
• Mur : Permet de créer un mur de couleur pour un joueur. Un mur de couleur est créer et un mur de "true/false" est aussi créer pour permettre de savoir si une case a été remplie ou non.
• Plancher : Créer le plancher de 7 tuiles pour un joueur
//
- Package controler.manager :
• Manager : classe principal du projet, elle permet de faire en sorte qu'une manche se déroule. Elle instencie les joueurs, place des tuiles sur les lignes et sur le mur, vérifie si les fabriques et le centre et vide et termine la manche.
• CentreDeTable : Creer le centre de la table, est capable de se remplir avec des tuiles de couleur et d'y extraire des tuiles d'une même couleur
• Fabrique : Classe permettant de creer plusieurs fabriques qui seront contenue dans une liste. Le nombre de fabrique dépend du nombre de joueur. Les fabriques peuvent se vider et remplir le centre.
• Pioche : Créer une pioche contentant 20 tuiles de chaque couleur et mélange la pioche
• Couvercle : Classe permettant de stocker les tuiles "perdues" et remet les tuiles dans la pioche quand elle est vide
- Package plancher :
//
- Package ia : contient les différents types d'IA et leurs classes abstraite
• IA : classe abstraite permettant ensuite au joueur de récupérer les méthodes de son niveau d'IA et pas d'un autre niveau
• TypeIa : énumération des différents type d'IA
//
- Package ia.typeIA : ce package contient les differents niveaux d'IA et leurs décision dans le choix de placement dans les lignes de motif et de piochage
• IArandom : simule le comportement d'une IA qui pioche et pose des tuiles aléatoirement
• IAintermediaire : 
• IAsuperieur : ne sont pas encore codé
//
- Package utilitaire :
• CompterPoint : Permet de compter les points issues des lignes de motif (n'est pas encore implémenter dans le jeu)
• Utilitaire : Peut généré un nombre aléatoire et une couleur aléatoire
• AfficherElement : Permet la gestion de tout les affichages 
//
- Package tuile :
• Tuile : Créer une tuile de couleur ou une tuile vide
• Couleurs : énumération de couleur et permet de set la valeur d'une couleur a true/false, utile pour la classe Mur
• AfficherElement : permet de faire tous les affichages




• Refactoring : Nous avons procédé a plusieurs refactoring, notamment pour mettre en place la classe AfficherElement, pour optimiser le code des IAs et faire les 500 parties. Nous avons aussi optimiser certaines partie du code en changeant leurs fonctionnement. Nous avons réparé Maven et lest marchent dessus.

En terme d'organisation du travail :
• Nicolas : Optimisation du fonctionnement des IAs "random" et « plus de couleur » , Prise en compte de l'état du mur dans les choix des IAs, Refactoring du code des IAs, Correction des bugs de l'IA "plus de couleur", iteration05.md
• Gando : Gestion de la fin de partie, Adaptation de l'IA random et intermédiaire, le joueur place les tuiles de gauche à droite, de récupération des tuiles perdus entre les lignes de motif et le mur lorsqu'une ligne est pleine pour remplir le couvercle qui remplit ensuite la pioche, mise en place des 500 parties, réalisation d'une statistique lors des 500 parties, condition de fin de partie ligne du mur pleine
• Thomas : Affichage du classement et désignation du vainqueur, classe d'affichage + refactoring pour mettre en place, réparer Maven
• Thibault : Tâches corrective, rajouts de tests, réparer Maven, iteration05.md
• Ralph : Lancer des manches tant qu'une ligne horizontale n'est pas remplit dans le mur, création de la méthode couleursDejaSurLigne() qui permet de vérifier si la couleur est déjà placée sur la ligne du mur
• Matthéo : Tâches corrective, rajouts de tests
